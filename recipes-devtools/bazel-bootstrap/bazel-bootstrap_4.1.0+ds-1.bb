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
GBP_EXTRA_OPTIONS = "--git-no-pristine-tar --git-upstream-branch=jc-4.1.0 --git-upstream-tree=BRANCH"

# build this package for the buildchroot-host
PACKAGE_ARCH = "${HOST_ARCH}"
# we do not really cross-compile, but have to work in the buildchroot-host
ISAR_CROSS_COMPILE = "1"

# note, this is not the official debian bazel-bootstrap (it still only provides 3.5.x)
SRC_URI = "git://git@salsa.debian.org/bazel-team/bazel-bootstrap.git;protocol=https;branch=jc-4.1.0"
SRCREV = "abc7db5dae3293a1905418bcbc733ed1effcd14d"
