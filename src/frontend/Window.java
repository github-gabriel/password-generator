package frontend;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final JPanel panel;
    private int width;
    private int height;

    public Window(String title, int width, int height, JPanel panel, int defaultCloseOperation) {
        this.width = width;
        this.height = height;
        this.panel = panel;
        this.add(panel);
        this.setTitle(title);
        this.setMinimumSize(new Dimension(900, 650));
        this.setSize(new Dimension(this.width, this.height));
        this.setDefaultCloseOperation(defaultCloseOperation);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void update() {
        this.panel.repaint();
        this.panel.revalidate();
    }

    public void getWidth(int width) {
        this.width = width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void getHeight(int height) {
        this.height = height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
