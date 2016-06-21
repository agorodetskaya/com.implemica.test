package com.implemica.test;

import com.implemica.test.test1.ConversionAlgorithm;
import com.implemica.test.test1.RecursiveSearchCombinations;
import com.implemica.test.test2.DijkstrasAlgorithmConsoleDataHandler;
import com.implemica.test.test3.SumOfDigitCalculator;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ConsoleReader consoleReader = new ConsoleReader();
        //TEST 1
        //consoleReader.run(new RecursiveSearchCombinations());
        // or try with ConversionAlgoritm
        //consoleReader.run(new ConversionAlgorithm());


        //TEST 2
        //consoleReader.run(new DijkstrasAlgorithmConsoleDataHandler());


        //TEST 3
        //consoleReader.run(new SumOfDigitCalculator());
    }
}
