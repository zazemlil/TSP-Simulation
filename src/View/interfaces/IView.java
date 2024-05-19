package View.interfaces;

import java.awt.event.*;

public interface IView {
    void start();

    void mousePressed(MouseEvent e);
    int mouseDragged(MouseEvent e);
    int mouseClicked(MouseEvent mouseEvent);
    void mouseReleased(MouseEvent e);
    int actionPerformed(ActionEvent e);
    int itemStateChanged(ItemEvent itemEvent);

    void setItemStateListener(ItemListener itemListener);
    void setActionListener(ActionListener actionListener);
    void setMouseListener(MouseListener actionListener);
    void setMouseMotionListener(MouseMotionListener actionListener);
}
