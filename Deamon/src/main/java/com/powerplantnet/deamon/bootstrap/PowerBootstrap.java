package com.powerplantnet.deamon.bootstrap;

import com.powerplantnet.deamon.PowerDeamon;
import com.powerplantnet.library.CloudLib;
import lombok.var;

public class PowerBootstrap {
    public static void main(String[] args){
        CloudLib.clearScreen();
        CloudLib.sendHeader();

        Runtime.getRuntime().addShutdownHook( new Thread( () -> {
            System.out.println( "Stopping in 3 Seconds..." );
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CloudLib.clearScreen();
            System.out.println("Shutdown PowerPlantNET-Deamon");
        }));

        final var deamon = new PowerDeamon();
        deamon.start();
    }
}
