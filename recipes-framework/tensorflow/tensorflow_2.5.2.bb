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
TENSORFLOW_LITEONLY ?= "1"

PR = "1"

S = "${WORKDIR}/${PN}-${TF_SRCREV}"
SRC_URI += "https://github.com/tensorflow/tensorflow/archive/${TF_SRCREV}.tar.gz;name=tf \
           file://${PN}-${TF_SRCREV}/debian"
SRC_URI[tf.sha256sum] = "8ebcbb9046074dca0309fddd53d58be5d76613cd2d69469a4779265f8482d2fb"

DEPENDS += " \
    python3-keras-preprocessing \
    python3-absl \
    ${@ 'python3-absl-host' if d.getVar('ISAR_CROSS_COMPILE') == '1' else '' } \
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
