#
# Copyright (c) Siemens AG, 2022
# Copyright (c) 2021, Mentor Graphics, a Siemens business
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#
# Files below ./files are licensed under the Apache-2.0 license

inherit dpkg

# build this package for the buildchroot-host
PACKAGE_ARCH = "${HOST_ARCH}"
# we do not really cross-compile, but have to work in the buildchroot-host
ISAR_CROSS_COMPILE = "1"

PR = "1"
SRC_URI = "https://github.com/bazelbuild/bazel/releases/download/${PV}/bazel-${PV}-dist.zip;subdir=bazel-${PV} \
           file://bazel-${PV}/debian                                                                           "
SRC_URI[sha256sum] = "9981d0d53a356c4e87962847750a97c9e8054e460854748006c80f0d7e2b2d33"

S = "${WORKDIR}/bazel-${PV}"
