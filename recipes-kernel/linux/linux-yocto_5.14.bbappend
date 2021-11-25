FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://mtd.cfg file://isa.cfg"

# Base our kernel configuration on qemuriscv64
KMACHINE:blackparrot ?= "qemuriscv64"

# Kernel sources for BP
KBRANCH:blackparrot  ?= "v5.14/standard/base"
SRCREV_machine:blackparrot ?= "35888b3a9a222963b856c960e8f0c72c2de18d4a"

# Patch this recipe so it advertises as compatible with blackparrot machine
COMPATIBLE_MACHINE:blackparrot = "blackparrot"