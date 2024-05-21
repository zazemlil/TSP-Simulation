package Model.impl;

import Model.utilz.ErrorCodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private List<Integer> vertexes;
    private Integer[][] edges;
    private Route min = null;

    public Graph() {
        this.vertexes = new ArrayList<>();
        this.edges = null;
    }

    public void addVertex(int vertex) {
        vertexes.add(vertex);
        Integer[][] newEdges = new Integer[vertexes.size()][vertexes.size()];

        if (edges != null)
            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges[i].length; j++) {
                    newEdges[i][j] = edges[i][j];
                }
            }
        edges = newEdges;
    }

    public void setRay(int vertex1, int vertex2, int weight) {
        if (edges == null) {
            this.edges = new Integer[vertexes.size()][vertexes.size()];
        }
        int row = this.vertexes.indexOf(vertex1);
        int column = this.vertexes.indexOf(vertex2);
        if (row != -1 && column != -1) {
            this.edges[row][column] = weight;
            this.edges[column][row] = weight;
        }
    }

    public int tsp() {
        if (edges == null) {
            return ErrorCodes.NO_TOWNS.getValue();
        }
        for (int i = 0; i < edges.length; i++) {
            int count = 0;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == null) {
                    count++;
                }
            }
            if (count == edges[i].length) return ErrorCodes.NOT_ALL_TOWNS_CONNECTED.getValue();
        }

        Set<Integer> seen = new HashSet<>();

        min = new Route(List.of(this.vertexes.get(0)), Integer.MAX_VALUE);

        bruteForce(0, new Route(List.of(this.vertexes.get(0)), 0), seen);

        if (min.getWeight() != Integer.MAX_VALUE)
            min.printVertexes();
        return this.min.getWeight();
    }

    private void bruteForce(int i, Route current, Set<Integer> seen) {
        seen.add(i);

        if (seen.size() == vertexes.size()) {
            int weight = 0;
            if (this.edges[i][0]!=null) {
                weight = this.edges[i][0];
            }
            if (weight != 0) {
                Route route = current.add(this.vertexes.get(0), weight);
                if (route.getWeight() < min.getWeight()) {
                    min = route;
                }
            }
        }
        else
        {
            for (int j = 0; j < vertexes.size(); j++) {
                int weight = 0;
                if (this.edges[i][j] != null) {
                    weight = this.edges[i][j];
                }
                if (weight != 0 && !seen.contains(j)) {
                    Route route = current.add(this.vertexes.get(j), weight);
                    bruteForce(j, route, seen);
                }
            }
        }
        seen.remove(i);
    }
}
