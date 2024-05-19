package View.impl;

import javax.swing.*;
import java.awt.*;

public class Ray {
    private int x1, y1, x2, y2;
    private Color colorAround, colorFill;
    private JTextField weightTextField;
    private Town town1, town2;

    public Ray(int x1, int y1, int x2, int y2, Town town1, Town town2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.town1 = town1;
        this.town2 = town2;

        colorAround = Color.GREEN;
        colorFill = Color.ORANGE;

        weightTextField = new JTextField("");
        weightTextField.setSize(35, 17);
        weightTextField.setPreferredSize(new Dimension(35, 17));
        weightTextField.setFocusable(false);
        weightTextField.setVisible(true);
    }
    public JTextField getWeightTextField() {
        weightTextField.setLocation((x1+x2)/2, (y1+y2)/2);
        return weightTextField;
    }

    public void moveWeightTextField() {
        weightTextField.setLocation((x1+x2)/2, (y1+y2)/2);
        int l = (int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        weightTextField.setText(Integer.toString(l));
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
        this.moveWeightTextField();
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
