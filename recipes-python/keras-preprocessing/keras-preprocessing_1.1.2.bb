#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.

inherit dpkg-gbp

# this is an architecture=all only package, hence build with host-toolchain
PACKAGE_ARCH="${HOST_ARCH}"

SRC_URI = "git://salsa.debian.org/science-team/${PN}.git;branch=master;protocol=https \
    file://patches/0001-New-upstream-version-1.1.2.patch \
    file://patches/0002-use-native-package-format.patch \
    file://patches/0003-Update-changelog.patch"
SRCREV = "2316fc93c37ac2cf8b485ed577e525d4955fcb93"

PROVIDES += "python3-${PN}"
