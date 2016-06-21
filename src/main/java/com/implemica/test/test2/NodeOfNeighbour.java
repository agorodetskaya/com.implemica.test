package com.implemica.test.test2;

public class NodeOfNeighbour {
    private int index;
    private int cost;

    public NodeOfNeighbour(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    public NodeOfNeighbour() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
