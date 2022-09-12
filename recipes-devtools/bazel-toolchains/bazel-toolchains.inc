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

# This file provides local bazel toolchains to cross-compile
# with system / host compilers

inherit dpkg-bazel

FILESEXTRAPATHS_prepend := "${FILE_DIRNAME}/files"
SRC_URI += "file://local_toolchains"

do_prepare_build[cleandirs] += "${S}/local_toolchains"
do_prepare_build_append() {
    cp -r ${WORKDIR}/local_toolchains ${S}/
}
