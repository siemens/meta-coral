#
# Copyright (c) Siemens AG, 2022
#
# Authors:
#  Felix Moessbauer <felix.moessbauer@siemens.com>
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.
# See COPYING.MIT file in the top-level directory.
#

inherit dpkg

DEPENDS += "bazel-bootstrap"
BAZEL_FETCH_DEPENDS += "bazel-bootstrap bazel-bootstrap-data git symlinks"

# ccache + bazel is broken
USE_CCACHE="0"

# fetch all bazel deps and add to package sources
bazel_fetch() {
    E="${@ isar_export_proxies(d)}"
    echo "SCHROOT: ${SBUILD_CHROOT}"
    schroot -d / -c ${SBUILD_CHROOT} -u root -- sh <<EOF
        echo ${ISAR_APT_REPO} > /etc/apt/sources.list.d/isar_apt.list;
        apt-get -y -q update
        apt-get -y -q -o Debug::pkgProblemResolver=yes \
                    --no-install-recommends --allow-downgrades \
                    install ${BAZEL_FETCH_DEPENDS}
        cd ${PP}/${PPS} && ./debian/rules local
EOF

    # generate orig archive, compress in parallel
    tar -c -I 'xz -1 -T0' -f ${WORKDIR}/${PN}_${PV}.orig.tar.xz -C ${S} --exclude=.git --exclude=.env .
}

do_fetch_bazel_deps[cleandirs] += "${S}/local"
do_fetch_bazel_deps[depends] += "${SCHROOT_DEP} bazel-bootstrap:do_deploy_deb"
python do_fetch_bazel_deps() {
    from shutil import copyfile
    from subprocess import check_call

    # hash the content of debian/rules in ${S}
    debian_rules_path = d.getVar('S') + "/debian/rules"
    filelist = debian_rules_path + ":True"
    checksum_list = bb.fetch2.get_file_checksums(filelist, d.getVar('PN'), [])
    _, debian_rules_chksum = checksum_list[0]

    # build the hash value
    fetch_hash = d.getVar('SRC_URI', False) + " " + (d.getVar('SRCREV', True) or "") + "\n" + \
        d.getVar('BAZEL_FETCH_DEPENDS', True) + '\n' + \
        debian_rules_chksum + "\n"

    orig_tar_xz = d.getVar('PN') + "_" + d.getVar('PV') + ".orig.tar.xz"
    orig_tar_xz_dl = d.getVar('DL_DIR') + "/" + orig_tar_xz
    orig_tar_xz_dl_hash = orig_tar_xz_dl + ".hash"
    orig_tar_xz_wd = d.getVar('WORKDIR') + "/" + orig_tar_xz

    # use downloaded file if it exists and hashes match
    if os.path.exists(orig_tar_xz_dl) and os.path.exists(orig_tar_xz_dl_hash):
        with open(orig_tar_xz_dl_hash) as hash_file:
            hash = hash_file.read()
        if hash == fetch_hash:
            copyfile(orig_tar_xz_dl, orig_tar_xz_wd)
            check_call(["tar", "xJf", orig_tar_xz_wd, "-C", d.getVar('S')])
            return

    bb.build.exec_func('schroot_create_configs', d)
    try:
        bb.build.exec_func("bazel_fetch", d)
    finally:
        bb.build.exec_func('schroot_delete_configs', d)

    # cache the fetch archive in DL_DIR along with its hash file
    copyfile(orig_tar_xz_wd, orig_tar_xz_dl)
    with open(orig_tar_xz_dl_hash, 'w') as hash_file:
        hash_file.write(fetch_hash)
}

addtask fetch_bazel_deps after do_prepare_build before do_dpkg_build

python clean_bazel_fetch() {
    orig_tar_xz = d.getVar('PN') + "_" + d.getVar('PV') + ".orig.tar.xz"
    orig_tar_xz_dl = d.getVar('DL_DIR') + "/" + orig_tar_xz
    orig_tar_xz_dl_hash = orig_tar_xz_dl + ".hash"

    if os.path.exists(orig_tar_xz_dl):
        os.remove(orig_tar_xz_dl)

    if os.path.exists(orig_tar_xz_dl_hash):
        os.remove(orig_tar_xz_dl_hash)
}
do_cleanall[postfuncs] += "clean_bazel_fetch"
