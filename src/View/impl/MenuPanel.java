package View.impl;

import View.utilz.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuPanel extends JPanel {
    private JPanel buttonPanel;
    private JPanel resultPanel;
    private JButton clearButton;
    private JButton computeButton;
    private JLabel resultLabel;
    private JCheckBox realTimeUpdatingCheckBox;
    public MenuPanel() {
        this.setSize(200, 200);
        this.setBackground(Color.WHITE);

        buttonPanel = new JPanel();
        resultPanel = new JPanel();

        realTimeUpdatingCheckBox = new JCheckBox("Real time updating");
        clearButton = new JButton("Clear");
        computeButton = new JButton("Compute");
        resultLabel = new JLabel("Result: ");
        buttonPanel.add(realTimeUpdatingCheckBox);
        buttonPanel.add(clearButton);
        buttonPanel.add(computeButton);
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        realTimeUpdatingCheckBox.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);
        resultPanel.setBackground(Color.WHITE);

        this.add(buttonPanel);
        this.add(resultPanel);

        realTimeUpdatingCheckBox.setFocusable(false);
        clearButton.setFocusable(false);
        computeButton.setFocusable(false);
    }
    public int actionPerformed(ActionEvent e, GraphPanel graphPanel)
    {
        Object tmp = e.getSource();
        if (tmp == clearButton) {
            graphPanel.clearGraphPanel();
            resultLabel.setText("Result: ");
            return Actions.CLEAR.getValue();
        }
        else if (tmp == computeButton) {
            return Actions.TSP_COMPUTE.getValue();
        }
        return -1;
    }

    public void setActionListener(ActionListener actionListener) {
        clearButton.addActionListener(actionListener);
        computeButton.addActionListener(actionListener);
    }

    public void setItemStateListener(ItemListener itemListener) {
        realTimeUpdatingCheckBox.addItemListener(itemListener);
    }

    public void setResult(String result) {
        resultLabel.setText(result);
    }

    public int itemStateChanged(ItemEvent itemEvent) {
        Object tmp = itemEvent.getSource();
        Integer[][] params = new Integer[100][3];
        if (tmp == realTimeUpdatingCheckBox) {
            if (itemEvent.getStateChange() == 1) {
                params[0][0] = 1;
                Actions.REAL_TIME_UPDATING.setParams(params);
                computeButton.setEnabled(false);
                return Actions.REAL_TIME_UPDATING.getValue();

            }else {
                params[0][0] = 0;
                Actions.REAL_TIME_UPDATING.setParams(params);
                computeButton.setEnabled(true);
                return Actions.REAL_TIME_UPDATING.getValue();
            }
        }
        return -1;
    }
}
