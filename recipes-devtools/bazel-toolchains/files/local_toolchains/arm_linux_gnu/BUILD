package(default_visibility = ["//visibility:public"])

# The following line is only here to make this project import into IDEs that embed
# a Bazel toolchain.
licenses(["notice"])

load(":cc_config.bzl", "cc_toolchain_config")

cc_toolchain_suite(
    name = "toolchain",
    toolchains = {
        "armeabi|compiler": ":cc-compiler-armeabi",
        "local|compiler": ":cc-compiler-local",
        "armeabi": ":cc-compiler-armeabi",
        "aarch64|gcc": ":cc-compiler-aarch64",
        "aarch64": ":cc-compiler-aarch64",
        "k8|gcc": ":cc-compiler-local",
        "k8": ":cc-compiler-local",
        "piii": ":cc-compiler-local",
        "arm": ":cc-compiler-local",
        "s390x": ":cc-compiler-local",
    },
)

filegroup(
    name = "empty",
    srcs = [],
)

cc_toolchain_config(
    name = "local_config",
    cpu = "local",
)

cc_toolchain(
    name = "cc-compiler-local",
    all_files = ":empty",
    compiler_files = ":empty",
    dwp_files = ":empty",
    linker_files = ":empty",
    objcopy_files = ":empty",
    strip_files = ":empty",
    supports_param_files = 1,
    toolchain_config = ":local_config",
    toolchain_identifier = "local_linux",
)

cc_toolchain_config(
    name = "armeabi_config",
    cpu = "armeabi",
)

cc_toolchain(
    name = "cc-compiler-armeabi",
    all_files = ":empty",
    compiler_files = ":empty",
    dwp_files = ":empty",
    linker_files = ":empty",
    objcopy_files = "empty",
    strip_files = "empty",
    supports_param_files = 1,
    toolchain_config = ":armeabi_config",
    toolchain_identifier = "arm-linux-gnueabihf",
)

cc_toolchain_config(
    name = "aarch64_config",
    cpu = "aarch64",
)

cc_toolchain(
    name = "cc-compiler-aarch64",
    all_files = ":empty",
    compiler_files = ":empty",
    dwp_files = ":empty",
    linker_files = ":empty",
    objcopy_files = "empty",
    strip_files = "empty",
    supports_param_files = 1,
    toolchain_config = ":aarch64_config",
    toolchain_identifier = "aarch64-linux-gnu",
)