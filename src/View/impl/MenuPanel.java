package View.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private JPanel buttonPanel;
    private JPanel resultPanel;
    private JButton clearButton;
    private JButton computeButton;
    private JLabel resultLabel;
    public MenuPanel() {
        this.setSize(200, 200);
        this.setBackground(Color.WHITE);

        buttonPanel = new JPanel();
        resultPanel = new JPanel();

        clearButton = new JButton("Clear");
        computeButton = new JButton("Compute");
        resultLabel = new JLabel("Result: ");
        buttonPanel.add(clearButton);
        buttonPanel.add(computeButton);
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        this.add(buttonPanel);
        this.add(resultPanel);

        clearButton.setFocusable(false);
        computeButton.setFocusable(false);
    }
    public void actionPerformed(ActionEvent e, GraphPanel graphPanel)
    {
        Object tmp = e.getSource();
        if (tmp == clearButton) {
            graphPanel.clearGraphPanel();
        }
    }

    public void setActionListener(ActionListener actionListener) {
        clearButton.addActionListener(actionListener);
        computeButton.addActionListener(actionListener);
    }
}
