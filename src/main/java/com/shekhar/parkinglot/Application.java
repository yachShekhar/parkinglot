package com.shekhar.parkinglot;

import com.shekhar.parkinglot.service.CommandType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        if (args.length > 0) {
            try(Stream<String> stream = Files.lines(Paths.get(args[0]))){
                stream.forEach(Application::execute);
            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try (Scanner mScanner = new Scanner(System.in)) {
                while (mScanner.hasNext()) {
                        String fullCommand = mScanner.nextLine();
                        execute(fullCommand);
                }
            }
        }
    }

    private static void execute(String fullCommand){
        try {
            String[] command = fullCommand.split(" ");
            CommandType.valueOf(command[0]).apply(Arrays.asList(command).subList(1, command.length));
        }catch (Exception e) {
            System.err.println("Wrong command");
            e.printStackTrace();
        }
    }
}
