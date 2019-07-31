package com.powerplantnet.library.command;

public abstract class ICommand {
    String name,description;

    public ICommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    public ICommand(String name){
        this.name = name;
    }

    boolean execute(String[] args){
        return false;
    }
}
