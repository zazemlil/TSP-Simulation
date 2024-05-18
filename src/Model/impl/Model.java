package Model.impl;

import Model.interfaces.IModel;
import java.util.List;

import Model.utilz.*;

public class Model implements IModel {
    private Graph graph;

    public Model() {
        graph = new Graph();
    }

    public Model(List<Integer> vertexes, int startPoint, int endPoint) {
        graph = new Graph(vertexes, startPoint, endPoint);
    }

    @Override
    public void addVertex(int vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void deleteVertex(int vertex) {
        graph.deleteVertex(vertex);
    }

    @Override
    public void setRay(int vertex1, int vertex2, int weight) {
        graph.addEdge(vertex1, vertex2, weight);
        graph.addEdge(vertex2, vertex1, weight);
    }

    @Override
    public void deleteRay(int vertex1, int vertex2) {
        graph.deleteEdge(vertex1, vertex2);
        graph.deleteEdge(vertex2, vertex1);
    }

    @Override
    public void setStartPoint(int vertex) {
        graph.setStartPoint(vertex);
    }

    @Override
    public void setEndPoint(int vertex) {
        graph.setEndPoint(vertex);
    }

    @Override
    public int tsp() {
        int res = graph.tsp();
        if (res == Integer.MAX_VALUE)
        {
            res = ErrorCodes.PATH_NOT_FOUND.getValue();
        }
        return res;
    }

    public int tspPointToPoint() {
        int res = ErrorCodes.PATH_NOT_FOUND.getValue();
        if (graph.getEndPoint() != graph.getStartPoint())
        {
            res = graph.tsp();
            if (res == Integer.MAX_VALUE)
            {
                res = ErrorCodes.START_END_POINTS_NOT_CORRECT.getValue();
            }
        }
        return res;
    }
}




