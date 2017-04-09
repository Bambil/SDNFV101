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
based on [Running the published Docker ONOS images](https://wiki.onosproject.org/display/ONOS/Running+the+published+Docker+ONOS+images)

1. Run your docker image

```
$ sudo docker run  -t -d --name onos1 onosproject/onos
<cid>
$ sudo docker run  -t -d --name onos2 onosproject/onos
<cid>
$ sudo docker run  -t -d --name onos3 onosproject/onos
<cid>
```

2. Cluster-ize your instances

```sh
wget https://raw.githubusercontent.com/opennetworkinglab/onos/master/tools/package/bin/onos-form-cluster
chmod u+x onos-form-cluster
./onos-form-cluster -u karaf -p karaf `docker-ip onos1` `docker-ip onos2` `docker-ip onos3`
```
