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

DEB_BUILD_PROFILES += "nocheck"
DEB_BUILD_OPTIONS += "nocheck"

SRC_URI = "git://salsa.debian.org/python-team/packages/python-gast.git;protocol=https;branch=debian/master"
SRCREV = "61a41edb3a692024b3dc7d2e70ca92ce40ba0860"

PROVIDES += "python3-gast"

do_dpkg_build[cleandirs] += "${S}/gast.egg-info"