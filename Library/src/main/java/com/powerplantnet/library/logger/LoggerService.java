package com.powerplantnet.library.logger;

import jline.console.ConsoleReader;
import lombok.var;
import org.apache.log4j.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService {
    private Logger logger;
    private File file;
    private File directory = new File("./log");

    public LoggerService(){
        var simpleDate = new SimpleDateFormat("yyyy-MM-dd");

        for(int id=1; id < Integer.MAX_VALUE; id++){
            file = new File(directory, simpleDate.format(new Date()) + "#" + id + ".log");
            if(file.exists()){
                continue;
            }
            break;
        }

        if(!directory.exists()){
            directory.mkdir();
        }
    }

    public void Log(LogLevel level,String message){
        logger.info("[" + level.name() + "]: " + message);
    }

    public void start() {

        Logger.getRootLogger().setLevel(Level.OFF);
        this.logger = Logger.getLogger("console");

        PatternLayout Layout = new PatternLayout("[%d{yyyy/MM/dd HH:mm:ss}] %m%n");

        ConsoleAppender appender = new ConsoleAppender(Layout);
        logger.addAppender(appender);

        try {
            FileAppender
                    fileAppender = new FileAppender(Layout, file.getAbsolutePath(), false),
                    logAppender = new FileAppender(Layout, directory.getAbsolutePath() + "/latest.log", false);

            logger.addAppender(fileAppender);
            logger.addAppender(logAppender);

            logger.setLevel(Level.INFO);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
