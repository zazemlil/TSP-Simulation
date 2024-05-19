package Model.impl;

import Controller.interfaces.IObservable;
import Model.interfaces.IModel;

import java.util.ArrayList;
import java.util.List;

import Model.utilz.*;
import View.interfaces.IObserver;

public class Model implements IModel, IObservable {
    private List<IObserver> observers = new ArrayList<>();
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

    @Override
    public void clear() {
        graph = new Graph();
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

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String result) {
        for (IObserver observer : observers) {
            observer.update(result);
        }
    }
}




