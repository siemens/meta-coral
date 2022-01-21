#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#
# Files below ./files are licensed under the BSD-3-Clause license

inherit dpkg-gbp

SRC_URI = " \
    git://salsa.debian.org/python-team/packages/astunparse.git;protocol=https;branch=master \
    file://0001-add-support-to-cross-compile-the-package.patch"
SRCREV = "430d69e0712f0bea418862efdaa01df35c321c1d"

PROVIDES += "python3-${PN}"

do_dpkg_build[cleandirs] += "${S}/.eggs"