package com.powerplantnet.master.file;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

import com.google.gson.Gson;
import com.powerplantnet.library.logger.LogLevel;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.logger.Reader;
import com.powerplantnet.master.file.internal.LanguageConf;
import com.powerplantnet.master.file.internal.ServiceConf;
import lombok.var;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Logger;

public class FileService {

    private LoggerService logger;
    private Gson gson;

    public FileService(LoggerService logger){

        this.logger = logger;
        this.gson = new Gson();

        for(File dir : new File[]{

            new File("master"),
            new File("master/modules"),
            new File("master/deamons")

        })dir.mkdirs();

    }

    public void setup(){

        setupLanguage();
        setupService();

    }

    public void setupService(){

        var file = new File("master/service.json");
        var reader = new Reader(this.logger);


        if(!file.exists()){

            try (var writer = new FileWriter(file, true)) {

                String address;
                int udp,tcp;

                logger.Log(LogLevel.INFO,"Enter the PowerMaster IP (string)");
                address = reader.readString(s -> s.split("\\.").length == 4);

                logger.Log(LogLevel.INFO,"Enter the PowerServer UDP Port (int)");
                udp = Integer.parseInt(reader.readString(s -> !s.equals("0")));

                logger.Log(LogLevel.INFO,"Enter the PowerServer TCP Port (int)");
                tcp = Integer.parseInt(reader.readString(s -> !s.equals("0") || s.equalsIgnoreCase(Integer.toString(udp))));

                Random random = new Random(34);

                var config = new ServiceConf(address,random.nextInt(),tcp,udp);

                writer.write(gson.toJson(config));
                writer.flush();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public void setupLanguage() {

        var file = new File("master/language.json");

        if (!file.exists()) {

            try (var writer = new FileWriter(file, true)) {

                var language = new LanguageConf();
                writer.write(gson.toJson(language));
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public ServiceConf getService(){

        try(var filereader = new FileReader(new File("master/service.json"))) {

            var bufferedReader = new BufferedReader(filereader);
            return gson.fromJson(bufferedReader.readLine(),ServiceConf.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public LanguageConf getLanguage(){

        try(var filereader = new FileReader(new File("master/language.json"))) {

            var bufferedReader = new BufferedReader(filereader);
            return gson.fromJson(bufferedReader.readLine(),LanguageConf.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
