package Model.interfaces;

import View.interfaces.IObserver;

public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers(String result);
}
