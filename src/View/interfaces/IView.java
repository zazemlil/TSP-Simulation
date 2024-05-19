package View.interfaces;

import java.awt.event.*;

public interface IView {
    void start();
    void mousePressed(MouseEvent e);
    void mouseDragged(MouseEvent e);
    int mouseClicked(MouseEvent mouseEvent);
    void mouseReleased(MouseEvent e);
    int actionPerformed(ActionEvent e);
    void setActionListener(ActionListener actionListener);
    void setMouseListener(MouseListener actionListener);
    void setMouseMotionListener(MouseMotionListener actionListener);
}
