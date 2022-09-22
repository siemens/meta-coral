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

# this is an architecture=all only package, hence build with host-toolchain
PACKAGE_ARCH="${HOST_ARCH}"

SRC_URI = "git://salsa.debian.org/python-team/packages/astunparse.git;protocol=https;branch=debian/master"
SRCREV = "16908188060d98fd94251334fba7d4f740f1d3ad"

PROVIDES += "python3-${PN}"

do_dpkg_build[cleandirs] += "${S}/.eggs"
