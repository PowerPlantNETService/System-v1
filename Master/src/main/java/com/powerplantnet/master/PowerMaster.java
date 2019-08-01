package com.powerplantnet.master;

import com.powerplantnet.library.command.CommandService;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.module.ModuleService;
import com.powerplantnet.library.network.PowerServer;
import com.powerplantnet.master.file.FileService;
import com.powerplantnet.master.file.internal.LanguageConf;
import com.powerplantnet.master.file.internal.ServiceConf;
import lombok.Getter;

@Getter
public class PowerMaster {

    private PowerMaster instance;
    private boolean running;
    private CommandService commandService;
    private LoggerService loggerService;
    private PowerServer powerServer;
    private ModuleService moduleService;
    private ServiceConf service;
    private LanguageConf language;
    private FileService fileService;

    public PowerMaster(){

        this.instance = this;

    }

    public void start(){

        this.running = true;
        this.loggerService = new LoggerService();
        this.commandService = new CommandService(this.loggerService);
        this.powerServer = new PowerServer("127.0.0.1",28085,28087,this.loggerService);
        this.fileService = new FileService(this.loggerService);


        this.fileService.setup();
        this.language = fileService.getLanguage();
        this.service = fileService.getService();
        this.loggerService.start();
        this.powerServer.start();
        this.commandService.start(language.CommandNotFound);

    }
}
