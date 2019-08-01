package com.powerplantnet.master.file.internal;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

import com.esotericsoftware.kryonet.Server;
import com.powerplantnet.library.logger.LoggerService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetworkConf {

    public int TCP_PORT,UDP_PORT,KEY;
    public String ADDRESS;

}
