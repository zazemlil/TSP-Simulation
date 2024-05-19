package View.impl;

import View.interfaces.IObserver;
import View.interfaces.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements IView, IObserver, Runnable {
    private Thread thread;
    private final int FPS_SET = 400;
    private GraphPanel graphPanel;
    private MenuPanel menuPanel;
    private JLabel fpsLabel;
    public Window() {
        super("Traveling Salesman Problem Simulation");
        this.setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        graphPanel = new GraphPanel();
        menuPanel = new MenuPanel();
        fpsLabel = new JLabel();
        fpsLabel.setForeground(Color.GREEN);
        this.add(graphPanel);
        this.add(menuPanel, BorderLayout.SOUTH);
        graphPanel.add(fpsLabel);

        thread = new Thread(this);
    }
    public Window(int width, int height) {
        this.setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void setWindowSize(int width, int height) {
        this.setSize(width, height);
    }

    @Override
    public void start() {
        this.setVisible(true);
        thread.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        graphPanel.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e)
    {
        graphPanel.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        graphPanel.mouseDragged(e);
    }

    @Override
    public int mouseClicked(MouseEvent mouseEvent) {
        return graphPanel.mouseClicked(mouseEvent);
    }

    @Override
    public int actionPerformed(ActionEvent e) {
        return menuPanel.actionPerformed(e, graphPanel);
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        menuPanel.setActionListener(actionListener);
    }

    @Override
    public void setMouseListener(MouseListener mouseListener) {
        graphPanel.setMouseListener(mouseListener);
    }

    @Override
    public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
        graphPanel.addMouseMotionListener(mouseMotionListener);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        double deltaF = 0;

        long lastCheck = System.currentTimeMillis();
        while (true) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaF >= 1) {
                graphPanel.repaint();
                menuPanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                fpsLabel.setText("FPS: " + frames);
                frames = 0;
            }
        }
    }

    @Override
    public void update(String result) {
        menuPanel.setResult("Result: " + result);
    }
}
