package com.implemica.test.test1;

import java.math.BigInteger;

/**
 * Calculates all possible combinations of brackets using recursion.
 */
public class RecursiveSearchCombinations implements SearchCombinations {
    private BigInteger numberOfPairsOfBrackets;
    // Value of the current number of pairs for which recursively calculates the number of combinations.
    private BigInteger count;
    private static final BigInteger THREE = BigInteger.valueOf(3);

    /**
     * Calculates all possible combinations of brackets.
     *
     * @param numberOfPairsOfBrackets number of pairs of brackets. Must be positive integer.
     * @return number of combinations of brackets.
     */
    @Override
    public BigInteger getNumberOfCombinations(BigInteger numberOfPairsOfBrackets) {
        if (numberOfPairsOfBrackets.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }
        count = BigInteger.ONE;
        this.numberOfPairsOfBrackets = numberOfPairsOfBrackets;
        return next(BigInteger.ONE, BigInteger.ONE);
    }

    /**
     * Recursively calculates all possible of combinations starting from 2 pairs.
     * Using formula (currentCombinationCount * 3 - previousCombinationCount).
     * Example:
     * for 2 pairs of brackets - 2 pairs of combinations;
     * for 3 pairs of brackets - 5 pairs of combinations;
     * for 4 pairs of brackets - 16 pairs of combinations;
     * Formula to calculate for 5 pairs - (16 * 3) - 5; (where 3 is constant)
     *
     * @param currentCombinationCount  count of conbinations which have been calculated for {@link this.count}
     * @param previousCombinationCount count of conbinations which have been calculated for previous value of {@link this.count}
     * @return count of conbinations for {@param currentCombinationCount} + 1.
     */
    private BigInteger next(BigInteger currentCombinationCount, BigInteger previousCombinationCount) {
        BigInteger nextCurrentCombinationCount = (currentCombinationCount.multiply(THREE).subtract(previousCombinationCount));
        count = count.add(BigInteger.ONE);
        if (count.compareTo(numberOfPairsOfBrackets) == -1) {
            nextCurrentCombinationCount = next(nextCurrentCombinationCount, currentCombinationCount);
        }
        return nextCurrentCombinationCount;
    }
}
