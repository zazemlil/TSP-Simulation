package View.impl;

import View.utilz.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class GraphPanel extends JPanel {
    private ArrayList<Town> towns;
    private ArrayList<Ray> rays;
    private int last_x, last_y;
    private Town locked;
    private boolean isTownSelected = false;
    private Town prevTownForRayConnect = null;
    public GraphPanel() {
        this.setSize(100, 100);
        this.setBackground(Color.BLACK);

        towns = new ArrayList<>();
        rays = new ArrayList<>();

        this.setFocusable(true);
    }

    public void clearGraphPanel() {
        for (Town town : towns) {
            this.remove(town.getIdLabel());
            for (Ray ray : town.getFirstSideRays()) {
                this.remove(ray.getWeightTextArea());
            }
            for (Ray ray : town.getSecondSideRays()) {
                this.remove(ray.getWeightTextArea());
            }
        }
        towns.clear();
        rays.clear();
        Town.resetCurrentTownId();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (Ray item : rays) {
            item.paintRay(g);
        }

        for (Town item : towns) {
            item.paintTown(g);
        }
    }

    public void setMouseListener(MouseListener actionListener) {
        this.addMouseListener(actionListener);
    }

    public void setMouseMotionListener(MouseMotionListener actionListener) {
        this.addMouseMotionListener(actionListener);
    }

    public Town getCollisionElement(int x, int y)
    {
        ListIterator<Town> iter = towns.listIterator(towns.size());
        while(iter.hasPrevious())
        {
            Town town = iter.previous();
            if(town.getX() <= x && town.getY() <= y && town.getX()+town.getWidth() >= x && town.getY()+town.getHeight() >= y)
                return town;
        }
        return null;
    }

    public void mouseReleased(MouseEvent e) {locked = null;}

    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Town town = getCollisionElement(e.getX(), e.getY());
            if (town != null) {
                last_x = e.getX();
                last_y = e.getY();
                locked = town;

                towns.remove(locked);
                towns.add(locked);
            }
        }
    }

    public int mouseDragged(MouseEvent e)
    {
        if(locked != null)
        {
            int x = e.getX();
            int y = e.getY();
            locked.moveTown(x - last_x, y - last_y);
            last_x = x;
            last_y = y;
            Actions.ADD_RAY.resetParams();
            Actions.ADD_RAY.setParams(locked.getNewRaysWeight());
            return Actions.ADD_RAY.getValue();
        }
        return -1;
    }

    public int mouseClicked(MouseEvent e) {
        // add town
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (getCollisionElement(e.getX(), e.getY()) == null) {
                if (isTownSelected == false) {
                    Town town = new Town(e.getX(), e.getY());
                    if (towns.size() == 0) {
                        town.setColorFill(Color.CYAN);
                    }
                    this.add(town.getIdLabel());
                    town.getIdLabel().repaint();
                    towns.add(town);
                    this.repaint();

                    Integer[][] params = new Integer[100][3];
                    params[0][0] = town.getId();
                    Actions.ADD_TOWN.setParams(params);
                    return Actions.ADD_TOWN.getValue();
                }
                else {
                    prevTownForRayConnect.setColorAround(Color.GREEN);
                    isTownSelected = false;
                    prevTownForRayConnect = null;
                }
            }
        }

        // add ray
        if (e.getButton() == MouseEvent.BUTTON1) {
            Town town = getCollisionElement(e.getX(), e.getY());
            boolean flag = true;
            if (town != null) {
                if (isTownSelected == true) {
                    if (prevTownForRayConnect != town) {
                        Ray newRay = new Ray(prevTownForRayConnect.getCentreX(), prevTownForRayConnect.getCentreY(), town.getCentreX(), town.getCentreY(), prevTownForRayConnect, town);
                        this.add(newRay.getWeightTextArea());
                        newRay.getWeightTextArea().repaint();
                        rays.add(newRay);
                        this.repaint();
                        prevTownForRayConnect.addFirstSideRays(newRay);
                        town.addSecondSideRays(newRay);

                        prevTownForRayConnect.setColorAround(Color.GREEN);
                        isTownSelected = false;
                        prevTownForRayConnect = null;
                        flag = false;

                        Integer[][] res = new Integer[100][3];
                        res[0] = newRay.getRay();
                        Actions.ADD_RAY.setParams(res);
                        return Actions.ADD_RAY.getValue();
                    }

                    prevTownForRayConnect.setColorAround(Color.GREEN);
                    isTownSelected = false;
                    prevTownForRayConnect = null;
                    flag = false;
                }
                if (isTownSelected == false && flag) {
                    town.setColorAround(Color.RED);
                    prevTownForRayConnect = town;
                    isTownSelected = true;
                }
            }
        }
        return -1;
    }
}
