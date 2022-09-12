#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.

# This implements a workaround to build the python-absl package also
# for the host, as we need it as a native build dependency as well.

require python-absl_${PV}.bb

# build this package for the buildchroot-host
PACKAGE_ARCH = "${HOST_ARCH}"
PROVIDES = "python3-absl-host"