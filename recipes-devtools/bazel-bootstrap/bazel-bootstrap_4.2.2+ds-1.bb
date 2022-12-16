#
# Copyright (c) Siemens AG, 2022
# Copyright (c) 2021, Mentor Graphics, a Siemens business
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.

inherit dpkg-gbp

GBP_DEPENDS_remove = "pristine-tar"
GBP_EXTRA_OPTIONS = "--git-no-pristine-tar --git-upstream-branch=master"

# build this package for the buildchroot-host
PACKAGE_ARCH = "${HOST_ARCH}"
# we do not really cross-compile, but have to work in the buildchroot-host
ISAR_CROSS_COMPILE = "1"

# note, this is not the official debian bazel-bootstrap (it still only provides 3.5.x)
SRC_URI = "git://git@salsa.debian.org/bazel-team/bazel-bootstrap.git;protocol=https;branch=master"
SRCREV = "300661f84ab8682f09896ffab2651a0d3740711f"
