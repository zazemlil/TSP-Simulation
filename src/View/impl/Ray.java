package View.impl;

import java.awt.*;

public class Ray {
    private int x1, y1, x2, y2;
    private Color colorAround, colorFill;

    public Ray(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        colorAround = Color.GREEN;
        colorFill = Color.ORANGE;
    }

    public float getX1() {
        return this.x1;
    }

    public float getY1() {
        return this.y1;
    }

    public float getX2() {
        return this.x2;
    }

    public float getY2() {
        return this.y2;
    }

    public void paintRay(Graphics g) {
        g.setColor(colorAround);
        g.drawLine(x1, y1, x2, y2);
        g.setColor(colorFill);
        g.drawLine(x1, y1, x2, y2);
    }

    public void moveX1(int deltaX) {
        this.x1 += deltaX;
    }

    public void moveX2(int deltaX) {
        this.x2 += deltaX;
    }

    public void moveY1(int deltaY) {
        this.y1 += deltaY;
    }

    public void moveY2(int deltaY) {
        this.y2 += deltaY;
    }
}
