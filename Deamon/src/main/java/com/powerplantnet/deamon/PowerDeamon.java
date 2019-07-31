package com.powerplantnet.deamon;

import com.powerplantnet.library.command.CommandService;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.network.PowerClient;
import com.powerplantnet.library.network.PowerServer;
import lombok.Getter;

@Getter
public class PowerDeamon {

    private PowerDeamon instance;
    private boolean running;
    private CommandService commandService;
    private LoggerService loggerService;
    private PowerClient powerClient;

    public PowerDeamon(){

        this.instance = this;

    }

    public void start(){

        this.running = true;
        this.loggerService = new LoggerService();
        this.commandService = new CommandService(this.loggerService);
        this.powerClient = new PowerClient("127.0.0.1",28085,28087,loggerService);

        this.loggerService.start();
        this.powerClient.start();
        this.commandService.start();

    }

}
