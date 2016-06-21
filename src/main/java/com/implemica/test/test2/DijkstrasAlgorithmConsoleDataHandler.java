package com.implemica.test.test2;

import com.implemica.test.ConsoleDataHandler;

import java.util.ArrayList;
import java.util.List;

public class DijkstrasAlgorithmConsoleDataHandler implements ConsoleDataHandler {
    private static final int NUMBER_OF_TESTS = 1;
    private static final int NUMBER_OF_CITIES = 2;
    private static final int CITY_NAME = 3;
    private static final int NUMBER_OF_NEIGHBOURS = 4;
    private static final int INDEX_AND_TRANSPORTATION_COST = 5;
    private static final int NUMBER_OF_PATHS_TO_FIND = 6;
    private static final int SOURCE_NAME_AND_DISTINATION_NAME = 7;
    private static final int FINISH_ALL_PHASES = 8;
    /**
     * indicates the current phase.
     */
    private int currentParameterPhase = 1;
    private int numberOfTests = 0;
    private int indexCityCount = 1;
    /**
     * Created and filled for each test.
     */
    private DijkstrasAlgorithm dijkstrasAlgorithm;
    /**
     * Size depends on the number of tests.
     */
    private List<DijkstrasAlgorithm> dijkstrasAlgorithmList;
    private City city;

    public DijkstrasAlgorithmConsoleDataHandler() {
        dijkstrasAlgorithmList = new ArrayList<>();
    }

    @Override
    public boolean executeConsoleData(String readLine) {
        try {
            if ("-1".equals(readLine)) {
                return false;
            }
            if (!"".equals(readLine)) {
                setParameter(readLine);
                return true;
            } else {
                if (currentParameterPhase != FINISH_ALL_PHASES) {
                    System.out.println("Insufficient parameters. Please continue to enter the parameters.");
                    return true;
                }
                for (DijkstrasAlgorithm dijkstrasAlgorithm : dijkstrasAlgorithmList) {
                    List<String> results = dijkstrasAlgorithm.search();
                    results.forEach(result -> System.out.println(result));
                    System.out.println();
                }
                dijkstrasAlgorithmList.clear();
                currentParameterPhase = NUMBER_OF_TESTS;
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Handles and validates input data {@param inputParameter} depending on the current phase.
     * Then parameter fits in {@link this.dijkstrasAlgorithm}.
     *
     * @param inputParameter input parameter which is validated and processed depending on the current phase.
     */
    private void setParameter(String inputParameter) {
        switch (currentParameterPhase) {
            case NUMBER_OF_TESTS:
                numberOfTests = checkInteger(inputParameter, "number of tests");
                if (numberOfTests > 10) {
                    throw new IllegalArgumentException("Number of tests should be less than 10. Please try again.");
                }
                currentParameterPhase = NUMBER_OF_CITIES;
                break;
            case NUMBER_OF_CITIES:
                dijkstrasAlgorithm = new DijkstrasAlgorithm();
                int numberOfCities = checkInteger(inputParameter, "number of cities");
                if (numberOfCities > 10000) {
                    throw new IllegalArgumentException("Number of cities should be less than 10000. Please try again.");
                }
                dijkstrasAlgorithm.setNumberOfCities(numberOfCities);
                currentParameterPhase = CITY_NAME;
                break;
            case CITY_NAME:
                checkNewCityName(inputParameter);
                city = new City();
                city.setName(inputParameter);
                city.setIndex(indexCityCount++);
                dijkstrasAlgorithm.getCities().add(city);
                currentParameterPhase = NUMBER_OF_NEIGHBOURS;
                break;
            case NUMBER_OF_NEIGHBOURS:
                int numberOfNeighbours = checkInteger(inputParameter, "numner of neighbours");
                if (dijkstrasAlgorithm.getNumberOfCities() - 1 < numberOfNeighbours) {
                    throw new IllegalArgumentException("Number of neighbors more than the number of not specified cities. " +
                            "Please try again.");
                }
                city.setNumberOfNeighbours(numberOfNeighbours);
                currentParameterPhase = INDEX_AND_TRANSPORTATION_COST;
                break;
            case INDEX_AND_TRANSPORTATION_COST:
                String[] neighboursParameters = inputParameter.split(" ");
                city.getNeighbours().add(checkIndexAndCostOfNeighbour(neighboursParameters));
                // if all the neighbors entered
                if (city.getNeighbours().size() == city.getNumberOfNeighbours()) {
                    // if all cities entered
                    if (dijkstrasAlgorithm.getCities().size() == dijkstrasAlgorithm.getNumberOfCities()) {
                        currentParameterPhase = NUMBER_OF_PATHS_TO_FIND;
                        break;
                    }
                    currentParameterPhase = CITY_NAME;
                }
                break;
            case NUMBER_OF_PATHS_TO_FIND:
                int numberOfPathsToFind = checkInteger(inputParameter, "number of path to find");
                if (numberOfPathsToFind > 100) {
                    throw new IllegalArgumentException("Number of of path to find should be less than 10000. " +
                            "Please try again.");
                }
                dijkstrasAlgorithm.setNumberOfPathsToFind(numberOfPathsToFind);
                currentParameterPhase = SOURCE_NAME_AND_DISTINATION_NAME;
                break;
            case SOURCE_NAME_AND_DISTINATION_NAME:
                String[] namesOfCities = inputParameter.split(" ");
                List<NodeOfTarget> targetsList = dijkstrasAlgorithm.getTargets();
                targetsList.add(checkSourceNameAndDestinationName(namesOfCities));
                if (targetsList.size() == dijkstrasAlgorithm.getNumberOfPathsToFind()) {
                    dijkstrasAlgorithmList.add(dijkstrasAlgorithm);
                    if (dijkstrasAlgorithmList.size() != numberOfTests) {
                        currentParameterPhase = NUMBER_OF_CITIES;
                        indexCityCount = 1;
                        break;
                    }
                    currentParameterPhase = FINISH_ALL_PHASES;
                    break;
                }
                break;
        }
    }

    /**
     * Validates parameters of neighbour and collects them to {@link NodeOfNeighbour}.
     *
     * @param neighbourParameters pair of parameters of neighbour separated by a space.
     * @return collected pair of parameters of neighbour to {@link NodeOfNeighbour}
     * @throws IllegalArgumentException 1. {@param neighbourParameters} contains more then 2 parameters;
     *                                  2. parameters are not positive integer;
     *                                  3. neighbor index is more than numer of cities;
     *                                  4. neighbor index is equal to the index of the current city;
     *                                  5. has already been specified this neighbor index for current city;
     *                                  6. city at the specified neighbor index has been created
     *                                  and the current city is not specified as a neighbor or their cost is different.
     */
    private NodeOfNeighbour checkIndexAndCostOfNeighbour(String[] neighbourParameters) {
        if (neighbourParameters.length != 2) {
            throw new IllegalArgumentException("You must enter 2 parameters: index of a city and the transportation cost" +
                    " separated by a space. Please try again.");
        }
        int index = checkInteger(neighbourParameters[0], "index of neighbours");
        int cost = checkInteger(neighbourParameters[1], "cost to neighbours");
        if (index > dijkstrasAlgorithm.getNumberOfCities()) {
            throw new IllegalArgumentException("Index greater than the specified number of cities. Please try again.");
        }
        if (index == city.getIndex()) {
            throw new IllegalArgumentException("Index is the index of the current city (" + city.getName() +
                    "). Please try again.");
        }
        boolean isIndexDuplicate = city.getNeighbours().stream().anyMatch(nodeOfNeighbour -> nodeOfNeighbour.getIndex() == index);
        if (isIndexDuplicate) {
            throw new IllegalArgumentException("Already specified the neighbor with such index. Please try again.");
        }
        List<City> cities = dijkstrasAlgorithm.getCities();
        if (cities.size() >= index) {
            boolean isCorrectCost = cities.get(index - 1).getNeighbours().stream().
                    anyMatch(node -> node.getIndex() == city.getIndex() && node.getCost() == cost);
            if (!isCorrectCost) {
                throw new IllegalArgumentException("Wrong index or cost. Please try again.");
            }
        }
        return new NodeOfNeighbour(index, cost);
    }

    /**
     * Converts to int and check parameter for a positive integer.
     *
     * @param value         parameter to check.
     * @param parameterName parameter name to display it with exeption message.
     * @return {@param value} converted to int.
     * @throws IllegalArgumentException 1. {@param value} failed convert to integer;
     *                                  2. {@param value} is a negative integer or 0.
     */
    private int checkInteger(String value, String parameterName) {
        value = value.trim();
        try {
            int parseInt = Integer.parseInt(value);
            if (parseInt <= 0) {
                throw new NumberFormatException();
            }
            return parseInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("You must enter parameter: " + parameterName +
                    ". It should be a positive integer. Please try again.", e);
        }
    }

    /**
     * Checks {@param name} for a valid name and the absence of duplicates.
     *
     * @param name city name for checks.
     * @throws IllegalArgumentException 1. {@param name} is not valid (s should be in the range of a-z
     *                                  and no more than 10 characters);
     *                                  2. city with {@param name} already exists.
     */
    private void checkNewCityName(String name) {
        if (!name.matches("[a-z]{1,10}")) {
            throw new IllegalArgumentException("The name of the city should be in the range of a-z " +
                    "and no more than 10 characters. Please try again.");
        }
        dijkstrasAlgorithm.getCities().forEach(city -> {
            if (city.getName().equals(name)) {
                throw new IllegalArgumentException("City with the such name already exists. Please try again.");
            }
        });
    }

    /**
     * Check source name and destination name and collect them to {@link NodeOfNeighbour}.
     *
     * @param names pair of city name (source and destination).
     * @return collected pair of city name (source and destination) to {@link NodeOfTarget}.
     * @throws IllegalArgumentException 1. {@param names} contains more then 2 parameters;
     *                                  2. city with one of the names does not exist.
     */
    private NodeOfTarget checkSourceNameAndDestinationName(String[] names) {
        if (names.length != 2) {
            throw new IllegalArgumentException("You must enter a valid name of two cities " +
                    "separated by a space. Please try again.");
        }
        for (String cityName : names) {
            boolean validName = dijkstrasAlgorithm.getCities().stream().anyMatch(city -> city.getName().equals(cityName));
            if (!validName) {
                throw new IllegalArgumentException("City with the name \"" + cityName + "\" does not exist. Please try again.");
            }
        }
        return new NodeOfTarget(names[0], names[1]);
    }

    public List<DijkstrasAlgorithm> getDijkstrasAlgorithmList() {
        return dijkstrasAlgorithmList;
    }
}
