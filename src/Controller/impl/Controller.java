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
        view.start();
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
            Integer[][] params = Actions.getParams(Actions.ADD_TOWN.getValue());
            tspModel.addVertex(params[0][0]);
        }
        else if (res == Actions.ADD_RAY.getValue()) {
            Integer[][] params = Actions.getParams(Actions.ADD_RAY.getValue());
            tspModel.setRay(params[0][0], params[0][1], params[0][2]);
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
        int res = view.mouseDragged(mouseEvent);
        if (res == Actions.ADD_RAY.getValue()) {
            Integer[][] params = Actions.getParams(Actions.ADD_RAY.getValue());
            for (int i = 0; i < params.length; i++) {
                if (params[i][0] == null) break;

                tspModel.setRay(params[i][0], params[i][1], params[i][2]);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
