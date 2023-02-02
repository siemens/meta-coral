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

DEB_BUILD_PROFILES += "nocheck"
DEB_BUILD_OPTIONS += "nocheck"

PR = "1"
S  = "${WORKDIR}/opt_einsum-${PV}"

GH_URI  = "https://github.com/dgasmith/opt_einsum/archive/refs/tags/v${PV}.tar.gz"
SRC_URI = "${GH_URI};unpack=false;downloadfilename=${PN}_${PV}.orig.tar.gz \
           file://opt_einsum-${PV}/debian"
SRC_URI[sha256sum] = "a748fdbccfce5af420ea56f8e2c51a6dc9e8774afd9179cb8addfab159b7b33c"

PROVIDES += "python3-${PN}"

python do_unpack:append() {
    from subprocess import check_call
    check_call(["tar", "xzf", d.getVar('PN') + "_" + d.getVar('PV') + ".orig.tar.gz"])
}
