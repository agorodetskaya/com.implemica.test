package com.implemica.test.test1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;


public class RecursiveSearchCombinationsTest {
    private RecursiveSearchCombinations recursiveSearchCombinations = new RecursiveSearchCombinations();

    @Test
    public void shouldCalculateCorrectCountOfCombinations() throws Exception {
        assertEquals(BigInteger.valueOf(1), recursiveSearchCombinations.getNumberOfCombinations(BigInteger.valueOf(1)));
        assertEquals(BigInteger.valueOf(2), recursiveSearchCombinations.getNumberOfCombinations(BigInteger.valueOf(2)));
        assertEquals(BigInteger.valueOf(13), recursiveSearchCombinations.getNumberOfCombinations(BigInteger.valueOf(4)));
        assertEquals(BigInteger.valueOf(34), recursiveSearchCombinations.getNumberOfCombinations(BigInteger.valueOf(5)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForZeroBracketsNumber() throws Exception {
        recursiveSearchCombinations.executeConsoleData("0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegativeBracketsNumber() throws Exception {
        recursiveSearchCombinations.executeConsoleData("-3");
    }
}