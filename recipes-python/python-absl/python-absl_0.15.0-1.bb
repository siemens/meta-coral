#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#
# Files below ./files are licensed under the Apache-2.0 license

inherit dpkg-gbp

SRC_URI = " \
    git://salsa.debian.org/python-team/packages/${PN}.git;protocol=https;branch=debian/latest \
    file://0001-add-support-to-cross-compile-the-package.patch"
SRCREV = "debian/${PV}"

PROVIDES += "python3-absl"