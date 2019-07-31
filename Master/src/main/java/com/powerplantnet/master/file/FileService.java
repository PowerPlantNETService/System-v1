package com.powerplantnet.master.file;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

import java.io.File;

public class FileService {

    public FileService(){

        for(File dir : new File[]{

            new File("master"),
            new File("master/modules"),
            new File("master/deamons")

        })dir.mkdirs();



    }
}
