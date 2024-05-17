package View.impl;

import java.awt.*;
import java.util.ArrayList;

public class Town {
    private int x, y;
    private int width, height;
    private Color colorAround, colorFill;
    private ArrayList<Ray> firstSideRays;
    private ArrayList<Ray> secondSideRays;

    public Town(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        colorAround = Color.GREEN;
        colorFill = Color.ORANGE;
        firstSideRays = new ArrayList<>();
        secondSideRays = new ArrayList<>();
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

    public void deleteFirstSideRays(Ray ray) {
        firstSideRays.remove(ray);
    }

    public void deleteSecondSideRays(Ray ray) {
        secondSideRays.remove(ray);
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
        g.setColor(colorAround);
        g.drawOval(x, y, width, height);
        g.setColor(colorFill);
        g.fillOval(x, y, width, height);
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
}
