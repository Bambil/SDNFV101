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

### Connectivity test with containrs on OVS + OpenFlow
based on [Open Virtual Networking With Docker](http://docs.openvswitch.org/en/latest/howto/docker/).

1. Create bridge interface

```sh
sudo ovs-vsctl add-br ovs-br1
```

2. Provide ip address to host ovs-br1 bridge

```sh
sudo ifconfig ovs-br1 173.16.1.1 netmask 255.255.255.0 up
```

3. Create docker instances

```sh
sudo docker run -t -i --name container1 1995parham/ubuntu-network /bin/bash
sudo docker run -t -i --name container2 1995parham/ubuntu-network /bin/bash
```

4. Connect them together and VSwitch

```sh
sudo ovs-docker add-port ovs-br1 eth1 container1 --ipaddress=173.16.1.2/24
sudo ovs-docker add-port ovs-br1 eth1 container2 --ipaddress=173.16.1.3/24
```

5. Turn the OpenFlow on

```sh
sudo ovs-vsctl set-controller br0 tcp:`docker-ip onos-1`:6653
```

### Let's build tunnels between OVSs
based on [Configuring VXLAN and GRE Tunnels on OpenvSwitch](http://networkstatic.net/configuring-vxlan-and-gre-tunnels-on-openvswitch/)

1. Create bridge interface

```sh
sudo ovs-vsctl add-br ovs-br1
```

2. Create tunnels :joy:

```sh
sudo ovs-vsctl add-port ovs-br1 vx1 -- set interface vx1 type=vxlan options:remote_ip=?
```

### ONOS cluster
based on [Running the published Docker ONOS images](https://wiki.onosproject.org/display/ONOS/Running+the+published+Docker+ONOS+images).

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
# Forms ONOS cluster using REST API of each separate instance.
wget https://raw.githubusercontent.com/opennetworkinglab/onos/master/tools/package/bin/onos-form-cluster

chmod u+x onos-form-cluster

./onos-form-cluster -u karaf -p karaf `docker-ip onos1` `docker-ip onos2` `docker-ip onos3`
```

3. Connect to your ONOS !

```sh
ssh -p 8101 karaf@`docekr-ip onso3`
```

| Protocol | Port |
|:--------:| ----:|
| SSH      | 8101 |
| HTTP     | 8181 |

### Docker Chaining

We want to create 3 containers that thier network chains together.

1. Create docker network if necessary

```sh
sudo docker netowrk create --driver=bridge bridge-name
```

2. Create a normal container in each subnet

```sh
sudo docker run -ti --rm --name=c1 ubuntu
sudo docker run -ti --rm --name=c3 --network=bridge-2 ubuntu
```

3. Create middlebox container

```sh
sudo docker build --no-cache -t middlebox containers/middlebox
sudo docker run -ti --rm --name=mc middlebox eth0
```
