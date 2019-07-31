package com.powerplantnet.library.module;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

public abstract class IModule {

    String name;
    String description;

    public IModule(String name,String description){

        this.name = name;
        this.description = description;

    }

    abstract void onEnable();
    abstract void onDisable();

}
