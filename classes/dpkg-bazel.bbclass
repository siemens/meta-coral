#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#

inherit dpkg

DEPENDS += "bazel-bootstrap"

# packages to install into the shared buildchroot
BAZEL_INSTALL = "bazel-bootstrap git symlinks"

# install bazel into the buildchroot so we can fetch external sources using bazel
bazel_install() {
    buildchroot_do_mounts
    sudo -E chroot ${BUILDCHROOT_DIR} apt-get install -y ${BAZEL_INSTALL}
}
bazel_install[depends] = "${BUILDCHROOT_DEP}"
bazel_install[lockfiles] = "${REPO_ISAR_DIR}/isar.lock"

# fetch all bazel deps and add to package sources
bazel_fetch() {
    dpkg_do_mounts
    E="${@ isar_export_proxies(d)}"
    sudo chroot --userspec=$( id -u ):$( id -g ) ${BUILDCHROOT_DIR} /bin/bash -c 'cd ${PP}/${PPS} && rm -rf local && ./debian/rules local'
    dpkg_undo_mounts

    # generate orig archive, compress in parallel
    tar -c -I 'xz -1 -T0' -f ${WORKDIR}/${PN}_${PV}.orig.tar.xz -C ${S} --exclude=.git --exclude='./debian' .
    # fix permissions of bazel cache
    sudo chown -R $( id -u ):$( id -g ) ${WORKDIR}/.cache/bazel
}

python do_fetch_bazel_deps() {
    from shutil import copyfile
    from subprocess import check_call

    # hash the content of debian/rules in ${S}
    debian_rules_path = d.getVar('S') + "/debian/rules"
    filelist = debian_rules_path + ":True"
    checksum_list = bb.fetch2.get_file_checksums(filelist, d.getVar('PN'), [])
    _, debian_rules_chksum = checksum_list[0]

    # build the hash value
    fetch_hash = d.getVar('SRC_URI') + " " + (d.getVar('SRCREV') or "") + "\n" + \
        debian_rules_chksum + "\n"

    orig_tar_xz = d.getVar('PN') + "_" + d.getVar('PV') + ".orig.tar.xz"
    orig_tar_xz_dl = d.getVar('DL_DIR') + "/" + orig_tar_xz
    orig_tar_xz_dl_hash = orig_tar_xz_dl + ".hash"
    orig_tar_xz_wd = d.getVar('WORKDIR') + "/" + orig_tar_xz

    # use downloaded file if it exists and hashes match
    if os.path.exists(orig_tar_xz_dl) and os.path.exists(orig_tar_xz_dl_hash):
        with open(orig_tar_xz_dl_hash) as hash_file:
            hash = hash_file.read()
        if hash == fetch_hash:
            copyfile(orig_tar_xz_dl, orig_tar_xz_wd)
            check_call(["tar", "xJf", orig_tar_xz_wd, "-C", d.getVar('S')])
            return

    bb.build.exec_func("bazel_install", d)
    bb.build.exec_func("bazel_fetch", d)

    # cache the fetch archive in DL_DIR along with its hash file
    copyfile(orig_tar_xz_wd, orig_tar_xz_dl)
    with open(orig_tar_xz_dl_hash, 'w') as hash_file:
        hash_file.write(fetch_hash)
}

do_fetch_bazel_deps[depends] += "${BUILDCHROOT_DEP}"
addtask fetch_bazel_deps after do_prepare_build before do_dpkg_build

python clean_bazel_fetch() {
    orig_tar_xz = d.getVar('PN') + "_" + d.getVar('PV') + ".orig.tar.xz"
    orig_tar_xz_dl = d.getVar('DL_DIR') + "/" + orig_tar_xz
    orig_tar_xz_dl_hash = orig_tar_xz_dl + ".hash"

    if os.path.exists(orig_tar_xz_dl):
        os.remove(orig_tar_xz_dl)

    if os.path.exists(orig_tar_xz_dl_hash):
        os.remove(orig_tar_xz_dl_hash)
}
do_cleanall[postfuncs] += "clean_bazel_fetch"
