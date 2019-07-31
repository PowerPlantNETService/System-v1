package com.powerplantnet.master.bootstrap;

import com.powerplantnet.library.CloudLib;
import com.powerplantnet.master.PowerMaster;
import lombok.Getter;
import lombok.var;

@Getter
public class PowerBootstrap {

    public static void main(String[] args) {
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
            System.out.println("Shutdown PowerPlantNET-Master");
        } ) );

        final var master = new PowerMaster();
        master.start();
    }

}
