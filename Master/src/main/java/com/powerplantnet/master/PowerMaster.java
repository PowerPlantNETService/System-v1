package com.powerplantnet.master;

import com.powerplantnet.library.command.CommandService;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.module.ModuleService;
import com.powerplantnet.library.network.PowerServer;
import lombok.Getter;

@Getter
public class PowerMaster {

    private PowerMaster instance;
    private boolean running;
    private CommandService commandService;
    private LoggerService loggerService;
    private PowerServer powerServer;
    private ModuleService moduleService;

    public PowerMaster(){

        this.instance = this;

    }

    public void start(){

        this.running = true;
        this.loggerService = new LoggerService();
        this.commandService = new CommandService(this.loggerService);
        this.powerServer = new PowerServer("127.0.0.1",28085,28087,this.loggerService);

        this.loggerService.start();
        this.powerServer.start();
        this.commandService.start();

    }
}
