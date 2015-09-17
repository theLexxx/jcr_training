package com.training.ui.console;

import com.training.bundles.calculate.Calculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class consoleUI {
    static final String EXIT_COMMAND = "q";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            boolean needExit = false;
            String command;
            do {
                System.out.println("For exit enter 'q'");
                command = reader.readLine();

                if (EXIT_COMMAND.equals(command)) {
                    needExit = true;
                } else {
                    Calculate calculate = new Calculate();
                    System.out.println(command + " " + calculate.execute(command));
                }

            } while (!needExit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
