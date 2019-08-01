package com.powerplantnet.master.file.internal;

import lombok.Getter;
import lombok.Setter;

import javax.xml.ws.Service;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */
@Getter
@Setter
public class ServiceConf {

    public NetworkConf network;

    public ServiceConf(String address,int key,int tcp,int udp){

        this.network.setADDRESS(address);
        this.network.setKEY(key);
        this.network.setTCP_PORT(tcp);
        this.network.setUDP_PORT(udp);

    }

    public ServiceConf(){}
}
