#!/bin/bash
# In The Name Of God
# ========================================
# [] File Name : ovn-docker.sh
#
# [] Creation Date : 09-04-2017
#
# [] Created By : Parham Alvani (parham.alvani@gmail.com)
# =======================================
HOST_IP=

docker daemon --cluster-store=consul://127.0.0.1:8500 \
	--cluster-advertise=$HOST_IP:0
