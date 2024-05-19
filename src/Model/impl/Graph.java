package Model.impl;

import Model.utilz.ErrorCodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private List<Integer> vertexes;
    private Integer[][] edges;
    private List<Integer[]> edgesParams;
    private Route min = null;

    public Graph() {
        this.vertexes = new ArrayList<>();
        this.edges = null;
        this.edgesParams = new ArrayList<> ();
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

    public void addEdge(int vertex1, int vertex2, int weight) {
        Integer[] params = new Integer[] {vertex1, vertex2, weight};
        for (Integer[] item : edgesParams) {
            if (item[0] == vertex1 && item[1] == vertex2 || item[0] == vertex2 && item[1] == vertex1) {
                item[3] = weight;
            }
            else {
                edgesParams.add(params);
            }
        }


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

        bruteforce(0, new Route(List.of(this.vertexes.get(0)), 0), seen);

        return this.min.getWeight();
    }

    private void bruteforce(int i, Route current, Set<Integer> seen) {
        seen.add(i);

        if (seen.size() == vertexes.size()) {
            int weight = 0;
            if (this.edges[i][0]!=null) {
                weight = this.edges[i][0];
            }
            if (weight != 0) {
                Route route = current.add(this.vertexes.get(0), weight);
                min = route;
            }
        }
        else
        {
            for (int j = 0; j < vertexes.size(); j++) {
                int weight = 0;
                if (this.edges == null) break;
                if (this.edges[i][j] != null) {
                    weight = this.edges[i][j];
                }
                if (weight != 0 && !seen.contains(j)) {
                    Route route = current.add(this.vertexes.get(j), weight);
                    if (route.getWeight() < min.getWeight()) {
                        bruteforce(j, route, seen);
                    }

                }
            }
        }
        seen.remove(i);
    }
}
