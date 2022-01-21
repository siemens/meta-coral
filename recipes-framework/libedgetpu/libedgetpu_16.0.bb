#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#

inherit dpkg
require ../tensorflow/tensorflow-src.inc

REL = "grouper"
PR  = "1"
S   = "${WORKDIR}/${PN}-release-${REL}"

SRC_URI = "https://github.com/google-coral/${PN}/archive/refs/tags/release-${REL}.tar.gz;name=lib \
           git://github.com/tensorflow/tensorflow.git;branch=master;protocol=https;name=tf;rev=${TF_SRCREV};nobranch=1;destsuffix=${S}/tensorflow \
           file://${PN}-release-${REL}/debian \
           file://0001-make-compilers-configurable-to-support-cross-compili.patch \
           file://0002-fix-add-missing-include-for-gcc-11.patch"
SRC_URI[lib.sha256sum] = "86a6e654e093c204b4fb579a60773bfa080f095c9cbb3a2c114ca4a13e0b15eb"

PROVIDES += "libedgetpu1-std libedgetpu1-max libedgetpu-dev"

dpkg_runbuild_prepend(){
    export TFROOT="tensorflow"
}
