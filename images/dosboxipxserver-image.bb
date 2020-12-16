SUMMARY = "An image for running dosboxipx servers on a raspberrypi"
HOMEPAGE = "https://github.com/bvarner/meta-dosboxipx"
LICENSE = "MIT"

include images/varnerized-raspberrypi.bb

IMAGE_INSTALL += " \
    ipxbox \
"

export IMAGE_BASENAME = "dosboxipxserver-image"

