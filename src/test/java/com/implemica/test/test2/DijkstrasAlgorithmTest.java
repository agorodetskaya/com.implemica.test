package com.implemica.test.test2;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstrasAlgorithmTest {
    private DijkstrasAlgorithmConsoleDataHandler dijkstrasAlgorithmConsoleDataHandler;

    @Before
    public void setUp() throws Exception {
        dijkstrasAlgorithmConsoleDataHandler = new DijkstrasAlgorithmConsoleDataHandler();
    }

    @Test
    public void shouldReturnCurrentResult() throws Exception {
        String[] parameters = {"1", "4",
                "gdansk", "2", "2 1", "3 3",
                "bydgoszcz", "3", "1 1", "3 1", "4 4",
                "torun", "3", "1 3", "2 1", "4 1",
                "warszawa", "2", "2 4", "3 1",
                "2", "gdansk warszawa", "bydgoszcz warszawa"};
        for (String parameter : parameters) {
            dijkstrasAlgorithmConsoleDataHandler.executeConsoleData(parameter);
        }
        List<String> search = dijkstrasAlgorithmConsoleDataHandler.getDijkstrasAlgorithmList().get(0).search();
        assertEquals(3, Integer.parseInt(search.get(0)));
        assertEquals(2, Integer.parseInt(search.get(1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionToIncorrectCommunicationBetweenCities() throws Exception {
        String[] parameters = {"1", "4",
                "gdansk", "2", "2 4", "3 3",
                "bydgoszcz", "3", "1 1", "3 1", "4 4",
                "torun", "3", "1 3", "2 1", "4 1",
                "warszawa", "2", "2 4", "3 1",
                "2", "gdansk warszawa", "bydgoszcz warszawa"};
        for (String parameter : parameters) {
            dijkstrasAlgorithmConsoleDataHandler.executeConsoleData(parameter);
        }
         dijkstrasAlgorithmConsoleDataHandler.getDijkstrasAlgorithmList().get(0).search();
    }
}