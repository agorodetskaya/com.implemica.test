package com.implemica.test.test1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class ConversionAlgorithmTest {
    private ConversionAlgorithm conversionAlgorithm = new ConversionAlgorithm();

    @Test
    public void shouldCalculateCorrectCountOfCombinations() throws Exception {
        assertEquals(BigInteger.valueOf(1), conversionAlgorithm.getNumberOfCombinations(BigInteger.valueOf(1)));
        assertEquals(BigInteger.valueOf(2), conversionAlgorithm.getNumberOfCombinations(BigInteger.valueOf(2)));
        assertEquals(BigInteger.valueOf(5), conversionAlgorithm.getNumberOfCombinations(BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(13), conversionAlgorithm.getNumberOfCombinations(BigInteger.valueOf(4)));
        assertEquals(BigInteger.valueOf(34), conversionAlgorithm.getNumberOfCombinations(BigInteger.valueOf(5)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForZeroBracketsNumber() throws Exception {
        conversionAlgorithm.executeConsoleData("0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegativeBracketsNumber() throws Exception {
        conversionAlgorithm.executeConsoleData("-4");
    }

}