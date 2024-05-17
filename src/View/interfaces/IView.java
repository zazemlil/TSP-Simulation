package View.interfaces;

import java.awt.event.*;

public interface IView {
    void start();
    void keyPressed(KeyEvent keyEvent);
    void keyReleased(KeyEvent keyEvent);
    void mousePressed(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseClicked(MouseEvent mouseEvent);
    void mouseReleased(MouseEvent e);
    void actionPerformed(ActionEvent e);
    void setKeyListener(KeyListener keyListener);
    void setActionListener(ActionListener actionListener);
    void setMouseListener(MouseListener actionListener);
    void setMouseMotionListener(MouseMotionListener actionListener);
}
