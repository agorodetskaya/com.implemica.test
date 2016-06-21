package com.implemica.test.test3;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class SumOfDigitCalculatorTest {

    private SumOfDigitCalculator sumOfDigitCalculator;

    @Before
    public void setUp() throws Exception {
        sumOfDigitCalculator = new SumOfDigitCalculator();
    }

    @Test
    public void shouldReturnCurrentSumOfDigitsFromFactorial() throws Exception {
        assertEquals(BigInteger.valueOf(1), sumOfDigitCalculator.findSumOfDigitsFromFactorial(0));
        assertEquals(BigInteger.valueOf(1), sumOfDigitCalculator.findSumOfDigitsFromFactorial(1));
        assertEquals(BigInteger.valueOf(3), sumOfDigitCalculator.findSumOfDigitsFromFactorial(5));
        assertEquals(BigInteger.valueOf(45), sumOfDigitCalculator.findSumOfDigitsFromFactorial(15));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExeptionForNegativeInteger() throws Exception {
        sumOfDigitCalculator.executeConsoleData("-5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExeptionForNotNumberParameter() throws Exception {
        sumOfDigitCalculator.executeConsoleData("adwfaw443");
    }

}