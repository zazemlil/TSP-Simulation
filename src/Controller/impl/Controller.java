package Controller.impl;

import Model.interfaces.IModel;
import View.interfaces.IView;
import Model.impl.Model;

import java.awt.event.*;

public class Controller implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    private static IView view;
    private static IModel tspModel;

    public Controller() {
        tspModel = new Model();
        view = ViewSelector.getInstance().getView(ViewType.GraphicView);
        view.setKeyListener(this);
        view.setActionListener(this);
        view.setMouseListener(this);
        view.setMouseMotionListener(this);
    }

    public void start() {
        /*tspModel.addVertex(0);
        tspModel.addVertex(1);
        tspModel.addVertex(2);
        tspModel.addVertex(3);

        tspModel.setRay(0, 1, 15);

        tspModel.setRay(0, 2, 40);

        tspModel.setRay(1, 2, 30);

        tspModel.setRay(2, 3, 30);


        tspModel.setStartPoint(2);
        tspModel.setEndPoint(0);

        tspModel.deleteRay(0, 2);

        tspModel.setRay(0, 3, 1);
        tspModel.setStartPoint(3);
        tspModel.setEndPoint(1);

        System.out.println(tspModel.tsp());*/

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
        view.actionPerformed(actionEvent);
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        view.mouseClicked(mouseEvent);
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
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        view.keyPressed(keyEvent);
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        view.keyReleased(keyEvent);
    }
}
