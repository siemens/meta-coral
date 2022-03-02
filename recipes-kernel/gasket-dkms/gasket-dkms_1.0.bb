#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#
# Files below ./files are licensed under the GPL-2.0-or-later license

inherit dpkg
require recipes-kernel/gasket-module/gasket-module-version.inc

PR = "18"
SRC_URI = "git://github.com/google/gasket-driver.git;protocol=https;branch=main;destsuffix=${P} \
           file://0001-add-section-and-priority-information.patch"
