package com.implemica.test.test3;

import com.implemica.test.ConsoleDataHandler;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * Calculates the sum of the digits of the factorial entered number.
 */
public class SumOfDigitCalculator implements ConsoleDataHandler {

    @Override
    public boolean executeConsoleData(String readLine) {
        if ("-1".equals(readLine)) {
            return false;
        }
        try {
            long numberForFactorial = checkNumberForFactorial(readLine);
            System.out.println(findSumOfDigitsFromFactorial(numberForFactorial));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Number for factorial should be a positive integer. Please try again.");
            throw e;
        }
    }

    /**
     * Displays the sum of the digits of the factorial {@param numberForFactorial}.
     * @param numberForFactorial non-negative integer of which will be calculated factorial.
     * @return sum of the digits of the factorial {@param numberForFactorial}.
     * @throws IllegalArgumentException {@param numberForFactorial} < 0
     */
    public BigInteger findSumOfDigitsFromFactorial(long numberForFactorial) {
        BigInteger factorial = findFactorial(numberForFactorial);
        return findSumOfDigits(factorial);
    }

    /**
     * Find factorial of {@param numberForFactorial}.
     * Factorial for 0 is 1.
     * @param numberForFactorial given number for factorial.
     * @return factorial of {@param numberForFactorial};
     *         BigInteger.ONE if {@param numberForFactorial} is 0.
     */
    private BigInteger findFactorial(long numberForFactorial) {
        if (numberForFactorial == 0) {
            return BigInteger.ONE;
        }
        return LongStream.rangeClosed(1, numberForFactorial)
                .parallel()
                .mapToObj(value -> BigInteger.valueOf(value))
                .reduce((bigInteger, bigInteger2) -> bigInteger.multiply(bigInteger2))
                .get();
    }

    /**
     * Find sum of digits of {@param value}.
     * Example:
     * {@param value} = 423;
     * sum = 0;
     * 1.   temp = 423 % 10; (3)
     *      sum += temp; (0 + 3 = 3)
     *      value = 423 / 10; (42)
     * 2.   temp = 42 % 10; (2)
     *      sum += temp; (3 + 2 = 5)
     *      value = 42 / 10; (4)
     * 3.   temp = 4 % 10; (4)
     *      sum += temp; (5 + 4 = 9)
     *      value = 4 / 10; (0)
     * @param value number of which will be calculated the sum of digits.
     * @return sum of digits of {@param value}
     */
    private BigInteger findSumOfDigits(BigInteger value) {
        BigInteger sum = BigInteger.ZERO;
        while (value.signum() == 1) {
            sum = sum.add(value.remainder(BigInteger.TEN));
            value = value.divide(BigInteger.TEN);
        }
        return sum;
    }

    private long checkNumberForFactorial(String readLine) {
        long numberForFactorial = Long.parseLong(readLine.trim());
        if (numberForFactorial < 0) {
            throw new IllegalArgumentException();
        }
        return numberForFactorial;
    }
}
