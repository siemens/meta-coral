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

SRC_URI += "git://github.com/google/gasket-driver.git;protocol=https;branch=main"

S = "${WORKDIR}/git/src"

# At least for bullseye arm64 the kernel headers depend on cpp-10:arm64.
# The cpp-10:arm64 package cannot be co-installed with cpp-10.
# Without sbuilder support, we must not remove packages from the build tree,
# hence have to compile in the target buildchroot
def is_distro_kernel(d):
    kernel_name = d.getVar('KERNEL_NAME', True)
    return kernel_name in d.getVar('DISTRO_KERNELS', True)

ISAR_CROSS_COMPILE = "${@'0' if is_distro_kernel(d) else '1'}"
