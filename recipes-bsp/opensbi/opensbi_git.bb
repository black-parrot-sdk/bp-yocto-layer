SUMMARY = "RISC-V Open Source Supervisor Binary Interface (OpenSBI)"
DESCRIPTION = "OpenSBI aims to provide an open-source and extensible implementation of the RISC-V SBI specification for a platform specific firmware (M-mode) and a general purpose OS, hypervisor or bootloader (S-mode or HS-mode). OpenSBI implementation can be easily extended by RISC-V platform or System-on-Chip vendors to fit a particular hadware configuration."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=42dd9555eb177f35150cf9aa240b61e5"

require opensbi-payloads.inc

inherit autotools-brokensep deploy

PV = "1.0+git${SRCPV}"

DEPENDS = "linux-stable"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

BRANCH = "master"
SRCREV = "c3c21fa13567a806bbe26de8b1a7796d4d93f9a8"
SRC_URI = "git://github.com/black-parrot-sdk/opensbi.git;protocol=https;branch=${BRANCH} \
           file://0001-Comment-out-device-tree-object-file.patch \
	   file://0002-Add-flags-to-.fdt-section-to-make-sure-it-always-end.patch \
          "

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "PLATFORM=${RISCV_SBI_PLAT} I=${D} INSTALL_LIB_PATH=lib FW_PIC=n"
EXTRA_OEMAKE += "PLATFORM_RISCV_ISA=rv64imafd"
EXTRA_OEMAKE += "PLATFORM_HART_COUNT=1"
# If RISCV_SBI_PAYLOAD is set then include it as a payload
EXTRA_OEMAKE:append = " ${@riscv_get_extra_oemake_image(d)}"
EXTRA_OEMAKE:append = " ${@riscv_get_extra_oemake_fdt(d)}"

do_install:append() {
	# In the future these might be required as a dependency for other packages.
	# At the moment just delete them to avoid warnings
	find ${D}
	rm -r ${D}/include
	rm -r ${D}/lib
	rm -r ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/payloads
}

do_deploy () {
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.* ${DEPLOYDIR}/
	#install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.* ${DEPLOYDIR}/
	#install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.* ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install

FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.*"

COMPATIBLE_HOST = "(riscv64|riscv32).*"
INHIBIT_PACKAGE_STRIP = "1"

SECURITY_CFLAGS = ""
