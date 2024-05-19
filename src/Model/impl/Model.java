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

    @Override
    public void addVertex(int vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void setRay(int vertex1, int vertex2, int weight) {
        graph.setRay(vertex1, vertex2, weight);
        graph.setRay(vertex2, vertex1, weight);
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




