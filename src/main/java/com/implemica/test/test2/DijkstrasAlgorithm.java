package com.implemica.test.test2;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the shortest path from one city to another, using the Dijkstra’s algorithm.
 * Class fields fills {@link DijkstrasAlgorithmConsoleDataHandler}
 */
public class DijkstrasAlgorithm {
    private int numberOfCities;
    private List<City> cities;
    private int numberOfPathsToFind;
    private List<NodeOfTarget> targets;
    private String currentToCityName;

    public DijkstrasAlgorithm() {
        cities = new ArrayList<>();
        targets = new ArrayList<>();
    }

    /**
     * Calculates the shortest path from one city to another.
     *
     * @return list of the shortest paths to all the distance specified in {@link this.targets}
     */
    public List<String> search() {
        List<String> minimumCostBetweenCities = new ArrayList<>();
        for (NodeOfTarget target : targets) {
            // definition variable for future recursive search in runDijkstrasAlgorithm();
            currentToCityName = target.getTo();
            City fromCity = cities.stream()
                    .filter(city -> target.getFrom().equals(city.getName()))
                    .findAny()
                    .get();
            runDijkstrasAlgorithm(fromCity);
            City toCity = cities.stream()
                    .filter(city -> currentToCityName.equals(city.getName()))
                    .findAny()
                    .get();
            minimumCostBetweenCities.add(String.valueOf(toCity.getShortestCost()));
            // reset data before the next iteration
            cities.forEach(city -> {
                city.setShortestCost(0);
                city.setKnown(false);
            });
        }
        return minimumCostBetweenCities;
    }

    /**
     * Alternately, the city takes its neighbors, adds its own shortest cost and cost to the neighbor.
     * If the result obtained is less than the previously installed the shortest cost,
     * this value becomes its shortest cost. After the city has checked all the neighbors,
     * it becomes known and no one else will try to check the cost to it.
     * Similarly, this is doing recursively with each of its neighbors that have not yet marked as known.
     * See Dijkstra's Algorithm.
     *
     * @param mainCity city from which cost will be checked for all its neighbors
     */
    private void runDijkstrasAlgorithm(City mainCity) {
        for (NodeOfNeighbour nodeOfNeighbour : mainCity.getNeighbours()) {
            City city = cities.get(nodeOfNeighbour.getIndex() - 1);
            if (city.isKnown()) {
                continue;
            }
            checkCommunicationBetweenCities(mainCity, nodeOfNeighbour, city);
            int smallerCostToCity = city.getShortestCost();
            int costAcrossMainCity = nodeOfNeighbour.getCost() + mainCity.getShortestCost();
            if (costAcrossMainCity < smallerCostToCity || smallerCostToCity == 0) {
                city.setShortestCost(costAcrossMainCity);
            } else {
                city.setShortestCost(smallerCostToCity);
            }
        }
        mainCity.setKnown(true);
        // recursive calculation for the neighbors that are not marked as known.
        for (NodeOfNeighbour nodeOfNeighbour : mainCity.getNeighbours()) {
            City newMainCity = cities.get(nodeOfNeighbour.getIndex() - 1);
            if (newMainCity.isKnown()) {
                continue;
            }
            runDijkstrasAlgorithm(newMainCity);
        }
    }

    /**
     * Checks correct installed cost between the cities.
     *
     * @param mainCity        city which verified correct cost with a neighbor
     * @param nodeOfNeighbour node of neighbor which verified correct cost with {@param mainCity}
     * @param city            neighbor which verified correct cost with {@param mainCity}
     */
    private void checkCommunicationBetweenCities(City mainCity, NodeOfNeighbour nodeOfNeighbour, City city) {
        if (!city.getNeighbours().stream().
                anyMatch(node -> mainCity.getIndex() == node.getIndex()
                        && nodeOfNeighbour.getCost() == node.getCost())) {
            throw new IllegalArgumentException("Entered incorrect communication between the cities: "
                    + mainCity.getName() + " and "
                    + city.getName());
        }
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getNumberOfPathsToFind() {
        return numberOfPathsToFind;
    }

    public void setNumberOfPathsToFind(int numberOfPathsToFind) {
        this.numberOfPathsToFind = numberOfPathsToFind;
    }

    public List<NodeOfTarget> getTargets() {
        return targets;
    }

    public void setTargets(List<NodeOfTarget> targets) {
        this.targets = targets;
    }
}
