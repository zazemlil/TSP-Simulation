package Model.impl;

import java.util.ArrayList;
import java.util.List;

class Route {
    private List<Integer> vertexes;
    private int weight;

    Route(List<Integer> vertexes, int weight) {
        this.vertexes = vertexes;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Route add(Integer vertex, int incWeight) {
        List<Integer> nextVertices = new ArrayList<>(this.vertexes);
        nextVertices.add(vertex);
        int nextWeight = this.weight + incWeight;
        return new Route(nextVertices, nextWeight);
    }
}
