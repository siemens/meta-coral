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

SRC_URI = "git://github.com/google/gasket-driver.git;protocol=https;branch=main;destsuffix=${P}"
SRC_URI += "file://0001-add-section-and-priority-information.patch"
# no releases yet
SRCREV = "f047773516dd65435becf09d8d03e5ef2a9f4165"
