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

inherit dpkg-bazel
require recipes-devtools/bazel-toolchains/bazel-toolchains.inc

PR  = "3"

DEPENDS = " \
    libedgetpu \
    python3-tflite-runtime \
"

# apply patches with git to not leave any traces
PATCHTOOL = "git"

S = "${WORKDIR}/git"
SRCREV = "9972f8ec6dbb8b2f46321e8c0d2513e0b6b152ce"
SRC_URI += "gitsm://git@github.com/google-coral/${PN};protocol=https \
           file://git/debian                                        \
           file://0001-feat-add-support-to-use-system-cross-compiler.patch \
           file://0002-add-CUSTOM_BAZEL_FLAGS-var-for-pybind.patch"

PROVIDES = "python3-pycoral pycoral-examples"

do_unpack[cleandirs] += "${S}/debian"

do_prepare_build() {
    # remove outdated compat file from git repo
    rm -rf ${S}/debian/compat
}
