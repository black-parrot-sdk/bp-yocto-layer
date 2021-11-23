# no patch to remove -march, please
SRCREV = "234ed8e427f4d92903123199f6590d144e0d9351"
SRC_URI = "git://github.com/riscv/opensbi.git;branch=master;protocol=https \
          "
EXTRA_OEMAKE += "PLATFORM_RISCV_ISA=rv64imafd"
