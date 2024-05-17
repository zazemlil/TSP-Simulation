package Model.impl;

import java.util.ArrayList;
import java.util.List;

class Route {
    private List<Integer> vertices;
    private int weight;

    Route(List<Integer> vertices, int weight) {
        this.vertices = vertices;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Route add(Integer vertex, int incWeight) {
        List<Integer> nextVertices = new ArrayList<>(this.vertices);
        nextVertices.add(vertex);
        int nextWeight = this.weight + incWeight;
        return new Route(nextVertices, nextWeight);
    }
}
