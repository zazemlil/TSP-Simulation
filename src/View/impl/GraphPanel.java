package View.impl;

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
    private boolean shiftPressed = false;
    private int currentSelectTownNumber = 0, selectTownX = 0, selectTownY = 0;
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
            for (Ray ray : town.getFirstSideRays()) {
                this.remove(ray.getWeightTextField());
            }
            for (Ray ray : town.getSecondSideRays()) {
                this.remove(ray.getWeightTextField());
            }
        }
        towns.clear();
        rays.clear();
    }

    public GraphPanel(int width, int height) {

    }

    public ArrayList<Town> getTowns() {
        return towns;
    }

    public ArrayList<Ray> getRays() {
        return rays;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Ray item : rays) {
            item.paintRay(g);
        }

        for (Town item : towns) {
            item.paintTown(g);
        }
    }

    public void setKeyListener(KeyListener keyListener) {
        this.addKeyListener(keyListener);
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

    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
            currentSelectTownNumber = 0;
            selectTownX = 0;
            selectTownY = 0;
        }
    }

    public void mouseReleased(MouseEvent e) {locked = null;}

    public void mousePressed(MouseEvent e)
    {
        Town town = getCollisionElement(e.getX(), e.getY());
        if(town != null)
        {
            last_x = e.getX();
            last_y = e.getY();
            locked = town;

            towns.remove(locked);
            towns.add(locked);
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        if(locked != null)
        {
            int x = e.getX();
            int y = e.getY();
            locked.moveTown(x - last_x, y - last_y);
            last_x = x;
            last_y = y;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && shiftPressed == false) {
            Town town = new Town(e.getX(), e.getY());
            if (towns.size() == 0) {
                town.setColorFill(Color.GREEN);
            }
            towns.add(town);
        }


        if (e.getButton() == MouseEvent.BUTTON1 && shiftPressed == true) {
            Town town = getCollisionElement(e.getX(), e.getY());
            boolean flag = true;
            if (town != null) {
                if (currentSelectTownNumber == 1) {
                    if (prevTownForRayConnect != town) {
                        Ray newRay = new Ray(selectTownX, selectTownY, e.getX(), e.getY());
                        this.add(newRay.getWeightTextField());
                        newRay.getWeightTextField().repaint();
                        rays.add(newRay);
                        this.repaint();
                        prevTownForRayConnect.addFirstSideRays(newRay);
                        town.addSecondSideRays(newRay);
                    }

                    prevTownForRayConnect.setColorAround(Color.GREEN);
                    currentSelectTownNumber = 0;
                    selectTownX = 0;
                    selectTownY = 0;
                    prevTownForRayConnect = null;
                    flag = false;
                }
                if (currentSelectTownNumber == 0 && flag) {
                    town.setColorAround(Color.RED);
                    prevTownForRayConnect = town;
                    currentSelectTownNumber = 1;
                    selectTownX = e.getX();
                    selectTownY = e.getY();
                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            Town town = getCollisionElement(e.getX(), e.getY());
            if (town != null) {
                for (Ray ray : town.getFirstSideRays()) {
                    rays.remove(ray);
                    this.remove(ray.getWeightTextField());
                }
                for (Ray ray : town.getSecondSideRays()) {
                    rays.remove(ray);
                    this.remove(ray.getWeightTextField());
                }
                towns.remove(town);
            }
        }
    }
}
