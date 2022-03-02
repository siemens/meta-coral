#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#

# This is a meta recipe to test the building of all packages

inherit dpkg-raw

DEPENDS = "                      \
    libedgetpu                   \
    astunparse                   \
    keras-preprocessing          \
    opt-einsum                   \
    pasta                        \
    python-absl                  \
    python-gast                  \
    bazel-bootstrap              \
    gasket-dkms                  \
    gasket-module-${KERNEL_NAME} \
    tensorflow                   \
    pycoral                      \
"
