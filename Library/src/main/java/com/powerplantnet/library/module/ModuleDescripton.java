package com.powerplantnet.library.module;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

import java.util.LinkedHashMap;
import java.util.List;

public class ModuleDescripton {

    private LinkedHashMap<String, String> description;

    public ModuleDescripton(List<String> list){

        this.description = new LinkedHashMap<>();

        for(String s : list){

            description.put(s.split("=")[0], s.split("=")[1]);

        }

    }

    public String getName(){

        return this.description.get("name");

    }

    public String getMain(){

        return this.description.get("main");

    }

    public String getVersion(){

        return this.description.get("version");

    }

    public String getAuthor(){

        return this.description.get("author");

    }

}
