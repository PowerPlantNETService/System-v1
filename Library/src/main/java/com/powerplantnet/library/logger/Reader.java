package com.powerplantnet.library.logger;

/*
 Copyright © 2019 Justus-Claas Panitz | All rights reserved
 */

import jline.console.ConsoleReader;
import lombok.var;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Predicate;

public class Reader {

    private LoggerService logger;

    public Reader(LoggerService logger){

        this.logger = logger;

    }

    public String readString(Predicate<String> predicate) throws IOException {

        final var scanner = new Scanner(System.in);
        String input;

        while ((input = scanner.nextLine()) == null || !predicate.test(input)) {

            logger.Log(LogLevel.WARNING,"Invalid input, try again");
            input = scanner.nextLine();

        }

        return input;

    }

}
