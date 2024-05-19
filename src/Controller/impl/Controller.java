package Controller.impl;

import Controller.interfaces.IObservable;
import Model.interfaces.IModel;
import Model.utilz.ErrorCodes;
import View.interfaces.IObserver;
import View.interfaces.IView;
import Model.impl.Model;

import java.awt.event.*;

import View.utilz.*;

public class Controller implements ActionListener, MouseListener, MouseMotionListener {
    private static IView view;
    private static IModel tspModel;
    private static IObservable observable;
    private static IObserver observer;

    public Controller() {
        tspModel = new Model();
        view = ViewSelector.getInstance().getView(ViewType.GraphicView);

        observable = (IObservable) tspModel;
        observer = (IObserver) view;

        observable.addObserver(observer);

        view.setActionListener(this);
        view.setMouseListener(this);
        view.setMouseMotionListener(this);
    }

    public void start() {
        tspModel.addVertex(0);
        //tspModel.addVertex(1);
        tspModel.addVertex(2);
        tspModel.addVertex(3);
        //tspModel.deleteVertex(1);

        //tspModel.setRay(0, 1, 15);

        tspModel.setRay(0, 2, 40);

        //tspModel.setRay(1, 2, 30);

        tspModel.setRay(2, 3, 30);


        //tspModel.setStartPoint(2);
        //tspModel.setEndPoint(0);

        //tspModel.deleteRay(0, 2);


        tspModel.setRay(0, 3, 2);
        //tspModel.setStartPoint(0);
        //tspModel.setEndPoint(0);

        System.out.println(tspModel.tsp());

        view.start();
        update();
    }

    private void update() {

    }

    public static void main(String[] args) {
        (new Controller()).start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int action = view.actionPerformed(actionEvent);
        if (action == Actions.TSP_COMPUTE.getValue()) {
            int res = tspModel.tsp();
            if (res >= 0)
                observable.notifyObservers(Integer.toString(res));
            else {
                observable.notifyObservers(ErrorCodes.getInfo(res));
            }
        } else if (action == Actions.CLEAR.getValue()) {
            tspModel.clear();
        }
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int res = view.mouseClicked(mouseEvent);
        if (res == Actions.ADD_TOWN.getValue()) {
            // -------------
            System.out.println("add town");
        } else if (res == Actions.DELETE_TOWN.getValue()) {
            // -------------
            System.out.println("delete town");
        } else if (res == Actions.ADD_RAY.getValue()) {
            // -------------
            System.out.println("add ray");
        }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        view.mousePressed(mouseEvent);
    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        view.mouseReleased(mouseEvent);
    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        view.mouseDragged(mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
