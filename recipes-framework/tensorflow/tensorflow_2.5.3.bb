#
# Copyright (c) Siemens AG, 2022
# Copyright (c) 2021, Mentor Graphics, a Siemens business
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.

inherit dpkg-bazel
require recipes-devtools/bazel-toolchains/bazel-toolchains.inc
require tensorflow-src.inc

# only build tflite python bindings instead of full TF
# Note, that full-tf builds currently fail due to
# a bug in bazel 4.1 (missing files in embedded_tools / bazel_tools).
TENSORFLOW_LITEONLY ?= "1"

PR = "2"

S = "${WORKDIR}/${PN}-${TF_SRCREV}"
SRC_URI += "https://github.com/tensorflow/tensorflow/archive/${TF_SRCREV}.tar.gz;name=tf \
           file://${PN}-${TF_SRCREV}/debian"
SRC_URI[tf.sha256sum] = "e801615335eeb1f5bdc1427e692053cdf3a083749ca2b3ad4ad40f86495a0f40"

DEPENDS += " \
    python3-keras-preprocessing \
    python3-absl \
    python3-astunparse \
    python3-gast \
    python3-opt-einsum \
    python3-pasta \
"

PROVIDES_FULL = " \
    libtensorflow2 \
    libtensorflow-cc2 \
    libtensorflow-dev \
    libtensorflow-framework2 \
    python3-tensorflow \
    tensorboard"

PROVIDES += " \
    python3-tflite-runtime \
    ${@ d.getVar('PROVIDES_FULL') if not d.getVar('TENSORFLOW_LITEONLY') == '1' else ''}"

DEB_BUILD_PROFILES += "${@ 'nofull' if d.getVar('TENSORFLOW_LITEONLY') == '1' else ''}"

do_unpack[cleandirs] += "${S}/debian"
python do_unpack:append() {
    os.remove(d.getVar('S') + "/.bazelversion")
}
