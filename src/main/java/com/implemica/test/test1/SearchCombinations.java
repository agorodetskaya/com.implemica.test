package com.implemica.test.test1;

import com.implemica.test.ConsoleDataHandler;

import java.math.BigInteger;

public interface SearchCombinations extends ConsoleDataHandler {
    BigInteger getNumberOfCombinations(BigInteger numberOfPairsOfBrackets);

    @Override
    default boolean executeConsoleData(String readLine) {
        try {
            if ("-1".equals(readLine)) {
                return false;
            }
            BigInteger parseNumber = new BigInteger(readLine);
            if (parseNumber.signum() <= 0) {
                throw new IllegalArgumentException("It must be a positive integer. Try again.");
            }
            System.out.println(getNumberOfCombinations(parseNumber));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return true;
    }
}
