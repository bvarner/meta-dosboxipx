# meta-dosboxipx
A Bitbake Layer for building a dedicated dosbox IPX server.

Build it with [kas](https://kas.readthedocs.io/en/latest/).

For example, targeting a raspberrypi0-wifi with my favorite settings and an x86-64 build machine, you can use the included YAML file.

```
git clone https://github.com/bvarner/meta-dosboxipx
kas build meta-dosboxipx/dosboxipx-pi0w-kas.yml
```

When that's done, use `bmaptool` to write the image out to a block device.

```
cd build/tmp/deploy/images/raspberrypi0-wifi
sudo bmaptool copy --bmap dosboxipxserver-image-raspberrypi0-wifi.wic.bmap dosboxipxserver-image-raspberrypi0-wifi.wic.bz2 /dev/mmcblk0
```

