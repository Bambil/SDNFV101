# SDNFV101
## Introduction

## Steps

### Initiation
1. Install docker on each VM
2. Install OVS on each VM

```sh
sudo apt install openvswitch-common
sudo apt install ovn-common
```

3. Install [ONOS](http://onosproject.org/) on each VM

```sh
sudo docker pull onosproject/onos
```

### Connectivity test with containrs on OVS

### ONOS cluster
1. Run your docker image

```
$ sudo docker run  -t -d --name onos1 onosproject/onos
<cid>
$ sudo docker run  -t -d --name onos2 onosproject/onos
<cid>
$ sudo docker run  -t -d --name onos3 onosproject/onos
<cid>
```
