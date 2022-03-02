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
require recipes-kernel/gasket-module/gasket-module-version.inc

SRC_URI = "git://github.com/google/gasket-driver.git;protocol=https;branch=main"

S = "${WORKDIR}/git/src"