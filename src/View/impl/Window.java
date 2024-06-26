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
    private JPanel infoPanel;
    private JLabel fpsLabel;
    public Window() {
        super("Traveling Salesman Problem Simulation");
        this.setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        graphPanel = new GraphPanel();
        menuPanel = new MenuPanel();
        infoPanel = new JPanel();
        fpsLabel = new JLabel();

        infoPanel.add(fpsLabel);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        fpsLabel.setForeground(Color.GREEN);
        fpsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(infoPanel, BorderLayout.NORTH);
        this.add(graphPanel);
        this.add(menuPanel, BorderLayout.SOUTH);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        thread = new Thread(this);
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

    @Override
    public void mouseReleased(MouseEvent e)
    {
        graphPanel.mouseReleased(e);
    }

    @Override
    public int mouseDragged(MouseEvent e) {
        return graphPanel.mouseDragged(e);
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
    public int itemStateChanged(ItemEvent itemEvent) {
        return menuPanel.itemStateChanged(itemEvent);
    }

    @Override
    public void setItemStateListener(ItemListener itemListener) {
        menuPanel.setItemStateListener(itemListener);
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
        graphPanel.setMouseMotionListener(mouseMotionListener);
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
        if (result != "")
            menuPanel.setResult("Result: " + result);
    }
}
