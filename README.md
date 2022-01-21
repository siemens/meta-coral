# ISAR Layer for Coral TPU

## Packaging status

**Legend:**

- blank: not started yet
- P: in progress
- T: in testing
- OK: works properly
- N: will not be packaged

### Coral Packages

Official list of packages from [coral.ai](https://coral.ai/software).

|package               |amd64|arm64|notes        |
|----------------------|-----|-----|-------------|
|edgetpu-compiler      | N   | N   | binary-only |
|libedgetpu1-max       | T   | T   |             |
|libedgetpu1-std       | T   | T   |             |
|libedgetpu-dev        | T   | T   |             |
|gasket-dkms           | T   | T   |             |
|gasket-module         | T   | T   |             |
|python3-pycoral       | T   | T   |             |
|python3-edgetpu       | N   | N   | deprecated  |
|edgetpu-examples      | N   | N   | deprecated  |
|python3-coral-cloudiot| N   | N   | binary-only |
|python3-coral-enviro  | N   | N   | binary-only |

### Infrastructure

|package            |amd64|arm64|source   |debian      |notes|
|-------------------|-----|-----|---------|------------|-----|
|bazel-bootstrap    | T   | N   | project | ISAR       | TF build dep |
|tensorflow         | T   | T   | project | ISAR       | no upstream deb packaging |
|astunparse         | T   | T   | salsa   | salsa+ISAR | TF dep |
|keras-preprocessing| T   | T   | salsa   | salsa+ISAR | TF dep |
|opt-einsum         | T   | T   | project | ISAR       | TF dep |
|pasta              | T   | T   | project | ISAR       | TF dep |
|python-absl        | T   | T   | salsa   | salsa      | TF dep |
|python-gast        | T   | T   | salsa   | salsa      | TF dep |

## Licensing

This project is licensed under the MIT license if not stated otherwise. For details, see [COPYING.MIT](COPYING.MIT).
The debian control files and patches are licensed analogously to their original sources.

## Compatibility

This layer is only compatible with debian bullseye.
Implementing support for debian bookworm is currently blocked incompatibilities with gcc-11, e.g. in [bazel-bootstrap](https://github.com/bazelbuild/bazel/issues/12756).
