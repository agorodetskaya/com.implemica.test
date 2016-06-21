package com.implemica.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Open InputStream from console.
 * Calculates all possible variants of combinations of brackets whitch count was entered in the console
 */
public class ConsoleReader {

    /**
     * Reads the entered data and sends them to the {@link ConsoleDataHandler}.
     * If enter in console -1, the stream will be closed.
     *
     * @param consoleDataHandler handler which handles input data.
     */
    public void run(ConsoleDataHandler consoleDataHandler) throws IllegalAccessException, InstantiationException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean continueWhile = true;
            while (continueWhile) {
                continueWhile = consoleDataHandler.executeConsoleData(bufferedReader.readLine());
            }
        } catch (Exception e) {
            System.out.println("Oops! We can not continue. Please restart the application.");
            throw new RuntimeException(e);
        }
    }
}
