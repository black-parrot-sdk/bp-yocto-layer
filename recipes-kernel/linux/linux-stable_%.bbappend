FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append:blackparrot = " \
			   file://mtd.cfg \
			   file://isa.cfg \
			   file://0001-add-device-trees-for-BlackParrot.patch \
			   file://0002-Add-SBI-earlycon-to-boot-args-for-BlackParrot-device.patch \
			   file://0003-Add-mtd-ram-device-to-bp-1hart-device-tree.patch \
			   file://defconfig"

# Base our kernel configuration on qemuriscv64
KMACHINE:blackparrot ?= "qemuriscv64"

# Patch this recipe so it advertises as compatible with blackparrot machine
COMPATIBLE_MACHINE:blackparrot = "blackparrot"