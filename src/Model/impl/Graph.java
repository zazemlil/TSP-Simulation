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
    private int endPoint;
    private int startPoint;

    public Graph(List<Integer> vertices, int startPoint, int endPoint) {
        this.endPoint = endPoint;
        this.startPoint = startPoint;
        this.vertexes = vertices;
        this.edges = new Integer[vertexes.size()][vertexes.size()];
    }

    public Graph() {
        this.endPoint = 0;
        this.startPoint = 0;
        this.vertexes = new ArrayList<>();
        this.edges = null;
        this.edgesParams = new ArrayList<> ();
    }

    public void addVertex(int vertex) {
        vertexes.add(vertex);
    }

    public void deleteVertex(int vertex) {
        vertexes.remove(vertexes.indexOf(vertex));

        for (Integer[] item : edgesParams) {
            if (item[0] == vertex || item[1] == vertex) {
                edgesParams.remove(item);
                break;
            }
        }

        this.edges = new Integer[vertexes.size()][vertexes.size()];
        for (Integer[] item : edgesParams) {
            int row = this.vertexes.indexOf(item[0]);
            int column = this.vertexes.indexOf(item[1]);

            this.edges[row][column] = item[2];
            this.edges[column][row] = item[2];
        }
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        Integer[] params = new Integer[] {vertex1, vertex2, weight};
        edgesParams.add(params);

        if (edges == null) {
            this.edges = new Integer[vertexes.size()][vertexes.size()];
        }

        int row = this.vertexes.indexOf(vertex1);
        int column = this.vertexes.indexOf(vertex2);

        this.edges[row][column] = weight;
        this.edges[column][row] = weight;
    }

    public void deleteEdge(int vertex1, int vertex2) {
        for (Integer[] item : edgesParams) {
            if (item[0] == vertex1 && item[1] == vertex2) {
                edgesParams.remove(item);
                break;
            }
        }

        this.edges = new Integer[vertexes.size()][vertexes.size()];
        for (Integer[] item : edgesParams) {
            int row = this.vertexes.indexOf(item[0]);
            int column = this.vertexes.indexOf(item[1]);

            this.edges[row][column] = item[2];
            this.edges[column][row] = item[2];
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

        min = new Route(List.of(this.vertexes.get(0)), Integer.MAX_VALUE); // startPointIndex

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
                        /*min = route;
                        System.out.println(min.getWeight());*/
                        bruteforce(j, route, seen);
                    }

                }
            }
        }
        seen.remove(i);
    }

    private void bruteforcePointToPoint(int i, Route current, Set<Integer> seen) {
        seen.add(i);

        if (seen.size() != vertexes.size()) {
            for (int j = 0; j < vertexes.size(); j++) {
                int weight = 0;
                if (this.edges[i][j] != null) {
                    weight = this.edges[i][j];
                }
                if (weight != 0 && !seen.contains(j)) {
                    Route route = current.add(this.vertexes.get(j), weight);
                    if (this.vertexes.get(j) == endPoint && route.getWeight() < min.getWeight()) {
                        min = route;
                    }
                    bruteforcePointToPoint(j, route, seen);
                }
            }
        }
        seen.remove(i);
    }

    public int shortestWayFromPointToPoint() {
        Set<Integer> seen = new HashSet<>();

        int startPointIndex = 0;
        int i = 0;
        for (int vertex : vertexes) {
            if (vertex == startPoint) {
                startPointIndex = i;
            }
            i++;
        }

        min = new Route(List.of(this.vertexes.get(startPointIndex)), Integer.MAX_VALUE);

        bruteforcePointToPoint(0, new Route(List.of(this.vertexes.get(startPointIndex)), 0), seen);

        return this.min.getWeight();
    }

    public void setStartPoint(int vertex) {
        this.startPoint = vertex;
        // реализовать https://ru.hexlet.io/courses/algorithms-graphs/lessons/traveling-salesman-problem/theory_unit?ysclid=lw8dknkhji704333134

        int current_first_edge = this.vertexes.indexOf(0);

        // поменяли названия городов местами
        int vertexIndex = vertexes.indexOf(vertex);
        this.vertexes.set(0, vertex);
        this.vertexes.set(vertexIndex, current_first_edge);

        // пересобрали таблицу
        this.edges = new Integer[vertexes.size()][vertexes.size()];
        for (Integer[] item : edgesParams) {

            int row = this.vertexes.indexOf(item[0]);
            int column = this.vertexes.indexOf(item[1]);

            this.edges[row][column] = item[2];
            this.edges[column][row] = item[2];
        }
    }

    public void setEndPoint(int vertex) {
        this.endPoint = vertex;
    }

    public int getStartPoint() {
        return this.startPoint;
    }

    public int getEndPoint() {
        return this.endPoint;
    }
}
