package com.powerplantnet.library.thread;

import lombok.Getter;

public class ThreadBuilder {
    @Getter
    private Thread thread;

    public ThreadBuilder(String name,Runnable runnable){
        this.thread = new Thread(runnable,name);
    }

    public ThreadBuilder setDeamon(boolean bool){
        this.thread.setDaemon(bool);
        return this;
    }

    public void start(){
        this.thread.start();
    }

    public void stop(){
        this.thread.stop();
    }
}
