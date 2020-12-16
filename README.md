# meta-dosboxipx
A Bitbake Layer to add a dedicated dosbox IPX server / bridge.


# Quick Start
The easiest way to build is with [kas](https://kas.readthedocs.io/en/latest/).
The process goes something like:
1. Get the tool
2. Make a place to work and clone this repo.
3. Update some preferences (wifi settings for your image, if you want wireless)
4. Run the build.
5. Write the image to an SD card.

## Step by Step
1. Install kas.
    * Using apt-get (Ubuntu 20.10)
      ```
      sudo apt-get install kas
      ```
    * or from PiPy
      ```
      sudo pip3 install kas
      ```

2. Make a 'work' directory and clone this repo.
   ```
   mkdir kas-builds 
   cd kas-builds
   git clone https://github.com/bvarner/meta-dosboxipx
   ```

3. Edit the local config with your favorite editor to specify wifi settings, build preferences, etc.
   ```
   gedit meta-dosboxipx/kas/local-env.yml
   ```

4. Run `kas` to build for your target machine.
   * Raspberry Pi Model B
     ```
     kas build meta-dosboxipx/kas/doxboxipx-raspberrypi.yml       
     ```
   * Raspberry Pi Zero W
     ```
     kas build  meta-dosboxipx/kas/doxboxipx-raspberrypi0-wifi.yml 
     ```

5. Use `bmaptool` to write the image out to a block device.
   
   **Adjust the target device name, `/dev/mmcblk0` appropriately.**
   
   * Raspberry Pi Model B
     ```
     cd build/tmp/deploy/images/raspberrypi
     sudo bmaptool copy --bmap dosboxipxserver-image-raspberrypi.wic.bmap dosboxipxserver-image-raspberrypi.wic.bz2 /dev/mmcblk0
     ```
   * Raspberry Pi Zero W
     ```
     cd build/tmp/deploy/images/raspberrypi0-wifi
     sudo bmaptool copy --bmap dosboxipxserver-image-raspberrypi0-wifi.wic.bmap dosboxipxserver-image-raspberrypi0-wifi.wic.bz2 /dev/mmcblk0
     ```
