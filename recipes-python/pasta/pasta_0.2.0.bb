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

PR = "2"

GH_URI  = "https://github.com/google/${PN}/archive/refs/tags/v${PV}.tar.gz"
SRC_URI = "${GH_URI};unpack=false;downloadfilename=${PN}_${PV}.orig.tar.gz \
           file://${PN}-${PV}/debian                                       "
SRC_URI[sha256sum] = "b9e3bcf5ab79986e245c8a2f3a872d14c610ce66904c4f16818342ce81cf97d2"

PROVIDES += "python3-${PN}"

do_dpkg_build[cleandirs] += "${S}/google_pasta.egg-info"

python do_unpack:append() {
    from subprocess import check_call
    check_call(["tar", "xzf", d.getVar('PN') + "_" + d.getVar('PV') + ".orig.tar.gz"])
}
