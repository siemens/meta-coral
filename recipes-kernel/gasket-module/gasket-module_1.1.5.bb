#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#

require recipes-kernel/linux-module/module.inc

SRC_URI += "git://github.com/google/gasket-driver.git;protocol=https;branch=main"
SRCREV = "f047773516dd65435becf09d8d03e5ef2a9f4165"

S = "${WORKDIR}/git/src"