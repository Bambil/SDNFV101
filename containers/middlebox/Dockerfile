FROM ubuntu
MAINTAINER Parham Alvani <parham.alvani@gmail.com>


# Install packages
RUN apt-get update
RUN apt-get -y install git
RUN apt-get -y install net-tools
RUN apt-get -y install gcc build-essential

# Let's Go Home
WORKDIR /home/root

# Middlebox function
RUN git clone https://github.com/1995parham/Kernel-Training.git
WORKDIR /home/root/Kernel-Training/Eth0/src
RUN make


# Entrypoint Script
ENTRYPOINT ["/home/root/Kernel-Training/Eth0/src/Eth0"]
