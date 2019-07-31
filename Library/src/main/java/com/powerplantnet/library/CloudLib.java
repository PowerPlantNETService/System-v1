package com.powerplantnet.library;

import java.io.IOException;

public class CloudLib {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendHeader(){
        System.out.println("Try to start PowerPlantNet-Master...");
        System.out.println("");
        System.out.println(" _____                                _____  __                _    _     _  ______  _______ ");
        System.out.println("(_____)                   ____  _    (_____)(__)        _     (_)_ (_)   (_)(______)(__ _ __)");
        System.out.println("(_)__(_)___   _   _   _  (____)(_)__ (_)__(_)(_)  ____ (_)__  (___)(__)_ (_)(_)__      (_)   ");
        System.out.println("(_____)(___) (_) ( ) (_)(_)_(_)(____)(_____) (_) (____)(____) (_)  (_)(_)(_)(____)     (_)   ");
        System.out.println("(_)   (_)_(_)(_)_(_)_(_)(__)__ (_)   (_)     (_)( )_( )(_) (_)(_)_ (_)  (__)(_)____    (_)   ");
        System.out.println("(_)    (___)  (__) (__)  (____)(_)   (_)    (___)(__)_)(_) (_) (__)(_)   (_)(______)   (_)   ");
        System.out.println("                        PowerPlant Network Environment Technology");
        System.out.println("");
        System.out.println("PowerPlantNET version " + CloudLib.class.getPackage().getImplementationVersion() + " developed by Justus-Claas Panitz");
        System.out.println("");
        System.out.println("");
        System.out.println("PowerPlantNET is starting now...");
    }
}
