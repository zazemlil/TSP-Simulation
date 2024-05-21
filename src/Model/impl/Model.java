package Model.impl;

import Model.interfaces.IObservable;
import Model.interfaces.IModel;
import Model.utilz.*;
import View.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public void tsp() {
        Route route = graph.tsp();
        int res = route.getWeight();
        if (res == Integer.MAX_VALUE)
        {
            res = ErrorCodes.PATH_NOT_FOUND.getValue();
        }

        if (res >= 0) {
            boolean isFirstIter = true;
            String path = "";
            for (Integer item : route.getVertexes()) {
                if (isFirstIter) {
                    path += "       Path: " + item;
                    isFirstIter = false;
                }
                else {
                    path += " => " + item;
                }
            }
            this.notifyObservers((res) + path);
        }
        else {
            this.notifyObservers(ErrorCodes.getInfo(res));
        }
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




