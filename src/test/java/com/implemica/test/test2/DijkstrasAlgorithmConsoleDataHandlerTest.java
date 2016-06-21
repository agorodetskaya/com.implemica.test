package com.implemica.test.test2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DijkstrasAlgorithmConsoleDataHandlerTest {

    private DijkstrasAlgorithmConsoleDataHandler dijkstrasAlgorithmConsoleDataHandler;

    @Before
    public void setUp() throws Exception {
        dijkstrasAlgorithmConsoleDataHandler = new DijkstrasAlgorithmConsoleDataHandler();
    }

    @Test
    public void shouldHandleCurrentParameters() throws Exception {
        String[] parameters = {String.valueOf(1), String.valueOf(11),
                "a", String.valueOf(3), "2 2", "3 1", "4 1",
                "b", String.valueOf(3), "8 2", "7 6", "1 2",
                "c", String.valueOf(4), "1 1", "4 2", "5 2", "6 3",
                "d", String.valueOf(3), "1 1", "3 2", "5 3",
                "m", String.valueOf(3), "4 3", "3 2", "6 1",
                "n", String.valueOf(4), "5 1", "3 3", "7 2", "9 2",
                "k", String.valueOf(4), "6 2", "2 6", "8 1", "9 1",
                "z", String.valueOf(4), "2 2", "7 1", "9 1", "10 1",
                "g", String.valueOf(5), "6 2", "7 1", "8 1", "10 1", "11 1",
                "h", String.valueOf(2), "9 1", "8 1",
                "l", String.valueOf(1), "9 1",
                String.valueOf(3), "a g", "a h", "h d"};
        for (String parameter : parameters) {
            dijkstrasAlgorithmConsoleDataHandler.executeConsoleData(parameter);
        }
        DijkstrasAlgorithm dijkstrasAlgorithm = dijkstrasAlgorithmConsoleDataHandler.getDijkstrasAlgorithmList().get(0);
        assertEquals(11, dijkstrasAlgorithm.getNumberOfCities());
        assertEquals("a", dijkstrasAlgorithm.getCities().get(0).getName());
        assertEquals("l", dijkstrasAlgorithm.getCities().get(10).getName());
        assertEquals(7, dijkstrasAlgorithm.getCities().get(5).getNeighbours().get(2).getIndex());
        assertEquals(3, dijkstrasAlgorithm.getNumberOfPathsToFind());
        assertEquals("h", dijkstrasAlgorithm.getTargets().get(1).getTo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionToIncorrectNameOfNewCityWithNumber() throws Exception {
        String[] parameters = {String.valueOf(1), String.valueOf(3),
                "a2", String.valueOf(1), "2 1"};
        for (String parameter : parameters) {
            dijkstrasAlgorithmConsoleDataHandler.executeConsoleData(parameter);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionToIncorrectNameOfNewCityMoreTenChar() throws Exception {
        String[] parameters = {String.valueOf(1), String.valueOf(3),
                "awwwwwwwwwwwwwww", String.valueOf(1), "2 1"};
        for (String parameter : parameters) {
            dijkstrasAlgorithmConsoleDataHandler.executeConsoleData(parameter);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionToIncorrectNameOfNewCityUpperCase() throws Exception {
        String[] parameters = {String.valueOf(1), String.valueOf(3),
                "WDDWW", String.valueOf(1), "2 1"};
        for (String parameter : parameters) {
            dijkstrasAlgorithmConsoleDataHandler.executeConsoleData(parameter);
        }
    }

}