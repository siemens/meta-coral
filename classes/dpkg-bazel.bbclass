#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#

inherit dpkg

DEPENDS += "bazel-bootstrap"

# fetch all bazel deps and add to package sources
do_fetch_bazel_deps() {
    dpkg_do_mounts
    E="${@ isar_export_proxies(d)}"
    sudo chroot --userspec=$( id -u ):$( id -g ) ${BUILDCHROOT_DIR} /bin/bash -c 'cd ${PP}/${PPS} && rm -rf local && ./debian/rules local'
    dpkg_undo_mounts

    # generate orig archive, compress in parallel
    tar -c -I 'xz -1 -T0' -f ${WORKDIR}/${PN}_${PV}.orig.tar.xz -C ${S} --exclude=.git --exclude='./debian' .
    # fix permissions of bazel cache
    sudo chown -R $( id -u ):$( id -g ) ${WORKDIR}/.cache/bazel
}

addtask fetch_bazel_deps after do_install_builddeps before do_dpkg_build
