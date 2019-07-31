package com.powerplantnet.library.module;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class ModuleService {

    private LinkedHashMap<String,IModule> modules;

    public ModuleService(){

        this.modules = new LinkedHashMap<>();

    }

    public void start(){

    }

    public void loadModule(@NotNull String name) {

        File dir = new File("master/modules" + File.separator);
        File mdir = new File(dir + File.separator + name + ".jar");
        File mddir = new File(mdir + File.separator + "Module-Info.properties");

        if(!mdir.exists()){

            System.out.println("Could'n find module '" + name + "'. Directory: " + mdir.toPath().toString());

        }else{

            URL url = null;

            try {

                url = mdir.toURL();

            } catch (MalformedURLException e){

                e.printStackTrace();

            }

            URL[] urls = new URL[]{url};
            ClassLoader classes = new URLClassLoader(urls);

            InputStream stream = classes.getResourceAsStream("module.properties");

            Scanner scan = new Scanner(stream);

            List<String> settings = new ArrayList<>();

            while(scan.hasNext()){

                settings.add(scan.nextLine());

            }

            scan.close();

            ModuleDescripton description = new ModuleDescripton(settings);

            try {

                IModule main = (IModule) classes.loadClass(description.getMain()).newInstance();

                main.onEnable();

                this.modules.put(description.getName(), main);

                System.out.println("Module: " + description.getName() + " has been loaded!");

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {

                e.printStackTrace();

            }
        }
    }

}
