package View.impl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Town {
    private static int currentTownId = 0;
    private int id;
    private int x, y;
    private int width, height;
    private Color colorAround, colorFill;
    private ArrayList<Ray> firstSideRays;
    private ArrayList<Ray> secondSideRays;
    private JLabel idLabel;

    public Town(int x, int y) {
        this.id = currentTownId;
        currentTownId++;
        this.width = 50;
        this.height = 50;
        this.x = x - width/2;
        this.y = y - height/2;
        colorAround = Color.GREEN;
        colorFill = Color.ORANGE;
        idLabel = new JLabel(Integer.toString(id));
        idLabel.setLocation(this.getCentreX(), this.getCentreY());
        firstSideRays = new ArrayList<>();
        secondSideRays = new ArrayList<>();
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public Integer[][] getNewRaysWeight() {
        Integer[][] res = new Integer[100][3];
        int i = 0;
        for (Ray ray : firstSideRays) {
            res[i] = ray.getRay();
            i++;
        }
        for (Ray ray : secondSideRays) {
            res[i] = ray.getRay();
            i++;
        }
        return res;
    }

    public static void resetCurrentTownId() {
        currentTownId = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setColorAround(Color color) {
        this.colorAround = color;
    }

    public void setColorFill(Color color) {
        this.colorFill = color;
    }

    public ArrayList<Ray> getFirstSideRays() {
        return firstSideRays;
    }

    public ArrayList<Ray> getSecondSideRays() {
        return secondSideRays;
    }

    public void addFirstSideRays(Ray ray) {
        firstSideRays.add(ray);
    }

    public void addSecondSideRays(Ray ray) {
        secondSideRays.add(ray);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void paintTown(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(colorAround);
        g2d.drawOval(x, y, width, height);
        g2d.setColor(colorFill);
        g2d.fillOval(x, y, width, height);

        idLabel.setLocation(this.getCentreX()-idLabel.getWidth()/2, this.getCentreY()-idLabel.getHeight()/2);
    }

    public void moveTown(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
        for (Ray ray : firstSideRays) {
            ray.moveX1(deltaX);
            ray.moveY1(deltaY);
        }
        for (Ray ray : secondSideRays) {
            ray.moveX2(deltaX);
            ray.moveY2(deltaY);
        }
    }

    public int getCentreX() {
        return width/2 + x;
    }

    public int getCentreY() {
        return height/2 + y;
    }
}
