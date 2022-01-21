#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.

inherit dpkg-gbp

DEB_BUILD_PROFILES += "nocheck"
DEB_BUILD_OPTIONS += "nocheck"

SRC_URI = "git://salsa.debian.org/science-team/${PN}.git;branch=master;protocol=https \
    file://patches/0001-New-upstream-version-1.1.2.patch \
    file://patches/0002-use-native-package-format.patch \
    file://patches/0003-Update-changelog.patch \
    file://patches/0004-add-support-to-crossbuild-this-package.patch \
    file://patches/0005-update-changelog.patch"
SRCREV = "2316fc93c37ac2cf8b485ed577e525d4955fcb93"

PROVIDES += "python3-${PN}"
