package View.impl;

import javax.swing.*;
import java.awt.*;

public class Ray {
    private int x1, y1, x2, y2;
    private Color color;
    private JTextArea weightTextArea;
    private Town town1, town2;

    public Ray(int x1, int y1, int x2, int y2, Town town1, Town town2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.town1 = town1;
        this.town2 = town2;

        color = Color.LIGHT_GRAY;

        weightTextArea = new JTextArea("");
        weightTextArea.setSize(28, 17);
        weightTextArea.setPreferredSize(new Dimension(28, 17));
        weightTextArea.setFocusable(false);
        weightTextArea.setVisible(true);
    }

    public Integer[] getRay() {
        Integer[] res = new Integer[3];
        res[0] = town1.getId();
        res[1] = town2.getId();
        res[2] = (int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return res;
    }

    public JTextArea getWeightTextArea() {
        weightTextArea.setLocation((x1+x2)/2, (y1+y2)/2);
        return weightTextArea;
    }

    public void moveWeightTextArea() {
        weightTextArea.setLocation((x1+x2)/2-weightTextArea.getWidth()/2, (y1+y2)/2-weightTextArea.getHeight()/2);
        int l = (int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        weightTextArea.setText(Integer.toString(l));
    }

    public void paintRay(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);

        this.moveWeightTextArea();
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
