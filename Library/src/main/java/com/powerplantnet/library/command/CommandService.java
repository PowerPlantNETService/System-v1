package com.powerplantnet.library.command;

import com.powerplantnet.library.logger.LogLevel;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.thread.ThreadBuilder;
import jline.console.ConsoleReader;
import lombok.Getter;
import lombok.var;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;

@Getter
public class CommandService {

    private LinkedHashMap<String, ICommand> commands = new LinkedHashMap<String, ICommand>();
    private boolean running;
    private LoggerService logger;

    public CommandService(LoggerService logger){

        this.logger = logger;

    }

    public CommandService addCommand(ICommand command){

        commands.put(command.name,command);
        return this;

    }

    public CommandService addCommand(String name, ICommand command){

        commands.put(name,command);
        return this;

    }

    public CommandService removeCommand(ICommand command){

        commands.remove(command.name);
        return this;

    }

    public CommandService removeCommand(String name){

        commands.remove(name);
        return this;

    }

    public void start(String commandnotfound){

        this.running = true;
        final var scanner = new Scanner(System.in);

        new ThreadBuilder("Command-Service",() -> {

            String input;

            while ((input = scanner.nextLine()) != null)
                dispatchCommand(input,commandnotfound);

        }).start();

    }

    private void dispatchCommand(String message,String commandnotfound){

        var args = message.split(" ");
        var command = this.commands.get(args[0]);

        if(command != null){

            command.execute(args);

        }else{

            logger.Log(LogLevel.WARNING,commandnotfound);

        }

    }
}
