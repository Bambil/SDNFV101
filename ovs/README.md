# OpenVSwitch Guide

## Steps

### Installation

```sh
sudo apt install openvswitch-common
sudo apt install ovn-common
```

### Basic Concepts

In OVS you are always working with network cards and plugging what is inside
each network card to each other. This network card may be virtual or physical.
If network card that you're trying to plug to another doens't exist, new one
will be created. If you didn't understand follow this example:

```sh
# Create a virtual switch named ovs-br1
sudo ovs-vsctl add-br ovs-br1
```
