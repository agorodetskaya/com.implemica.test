package com.implemica.test.test1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Calculates all possible combinations of brackets.
 * Will only work with small values (depending on amount of RAM).
 * To calculate large values use {@link RecursiveSearchCombinations}
 */
public class ConversionAlgorithm implements SearchCombinations {
    private List<String> globalList;
    /**
     * functions that will change the previous options.
     */
    private List<Function<String, String>> functionList;

    public ConversionAlgorithm() {
        functionList = new ArrayList<>();
        functionList.add(s -> "()" + s);
        functionList.add(s -> s + "()");
        functionList.add(s -> '(' + s + ')');
        globalList = new ArrayList<>();
    }

    /**
     * Calculates all possible combinations of brackets.
     *
     * @param numberOfPairsOfBrackets number of pairs of brackets. Must be positive integer.
     * @return number of combinations of pairs of brackets.
     */
    @Override
    public BigInteger getNumberOfCombinations(BigInteger numberOfPairsOfBrackets) {
        globalList.clear();
        globalList.add("");
        /*
        It calculates the number of combinations, with each iteration adding one pair of brackets,
        until it reaches the number of bracket pairs entered from the console.
        Calculates the number of combinations for 1 pair , then for 2, 3, etc.
         */
        for (BigInteger i = BigInteger.ONE; i.compareTo(numberOfPairsOfBrackets) < 1; i = i.add(BigInteger.ONE)) {
            globalList = getListOfCombinations();
        }
        return BigInteger.valueOf(globalList.size());
    }

    /**
     * Alternately apply each function to the {@link this.globalList}
     *
     * @return the number of combinations of brackets after (n+1) pairs of brackets to current number of pairs.
     */
    private List<String> getListOfCombinations() {
        //List size depends on the number of functions applied on it
        List<String> temp = new ArrayList<>(globalList.size() * functionList.size());
        //Alternately apply each function to each value of {@link this.globalList}
        for (Function<String, String> function : functionList) {
            for (String value : globalList) {
                temp.add(function.apply(value));
            }
        }
        return temp.stream().distinct().collect(Collectors.toList());
    }
}