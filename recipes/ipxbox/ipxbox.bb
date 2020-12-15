DESCRIPTION = "A standalone DOSBox IPX server."
SECTION = "misc"
HOMEPAGE = "https://github.com/fragglet/ipxbox"

LICENSE = "COPYING.md"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/src/github.com/fragglet/ipxbox/COPYING.md;md5=7502bc74f7995b73aa090f83ed5e02f7"
NO_GENERIC_LICENSE[ipxbox] = "COPYING.md"


SRCNAME = "ipxbox"
PKG_NAME = "github.com/fragglet/${SRCNAME}"

SRC_URI = "\
    git://${GO_IMPORT};branch=master \
    file://systemd-units/ipxbox.service \
    file://avahi/ipxbox.service \
"

SRCREV = "${AUTOREV}"

GO_LINKSHARED = ""
GO_IMPORT = "${PKG_NAME}"
GO_INSTALL = "${GO_IMPORT}"

SYSTEMD_PACKAGES += "${PN}"
SYSTEMD_SERVICE_${PN} = "${SRCNAME}.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

inherit go systemd

DEPENDS = "\
    libpcap \
    avahi \
"

RDEPENDS_${PN}_append = "\
    avahi-daemon \
    avahi-autoipd \
"

RDEPENDS_${PN}-dev_append = "\
    bash \
"

RDEPENDS_${PN}-staticdev_append = "\
    perl \
    bash \
"

# Modern go builds that use the go modules, or need go dependencies to be gotten before compilation...
do_compile_prepend() {
    ( cd ${WORKDIR}/build/src/${GO_IMPORT} && ${GO} get -d ./... )
}


do_install_append() {
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/systemd-units/${SRCNAME}.service ${D}${systemd_unitdir}/system

        install -d ${D}${sysconfdir}/avahi/services
        install -m 0644 ${WORKDIR}/avahi/${SRCNAME}.service ${D}${sysconfdir}/avahi/services

        install -d ${D}${sysconfdir}/ssl/certs
}
