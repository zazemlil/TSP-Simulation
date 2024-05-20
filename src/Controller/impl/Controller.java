package Controller.impl;

import Model.interfaces.IObservable;
import Model.interfaces.IModel;
import View.interfaces.IObserver;
import View.interfaces.IView;
import Model.impl.Model;

import java.awt.event.*;

import View.utilz.*;

public class Controller implements ActionListener, MouseListener, MouseMotionListener, ItemListener, Runnable {
    private Thread thread;
    private static IView view;
    private static IModel tspModel;
    private static IObservable observable;
    private static IObserver observer;
    private static boolean realTimeUpdating = false;
    private final int UPS_SET = 10;

    public Controller() {
        tspModel = new Model();
        view = ViewSelector.getInstance().getView(ViewType.GraphicView);

        observable = (IObservable) tspModel;
        observer = (IObserver) view;

        observable.addObserver(observer);

        view.setActionListener(this);
        view.setMouseListener(this);
        view.setMouseMotionListener(this);
        view.setItemStateListener(this);

        thread = new Thread(this);
    }

    public void start() {
        view.start();
        thread.start();
    }

    private void update() {
        if (realTimeUpdating) {
            tspModel.tsp();
        }
    }
    public static void main(String[] args) {
        (new Controller()).start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int action = view.actionPerformed(actionEvent);
        if (action == Actions.TSP_COMPUTE.getValue()) {
            tspModel.tsp();
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

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        int res = view.itemStateChanged(itemEvent);
        if (res == Actions.REAL_TIME_UPDATING.getValue()) {
            Integer[][] params = Actions.getParams(Actions.REAL_TIME_UPDATING.getValue());

            if (params[0][0] == 1)
                realTimeUpdating = true;
            else if (params[0][0] == 0) {
                realTimeUpdating = false;
            }
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        double deltaF = 0;

        long lastCheck = System.currentTimeMillis();
        while (true) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaF >= 1) {
                update();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();

                frames = 0;
            }
        }

    }
}
