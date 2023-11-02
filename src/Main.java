import frontend.GeneratorPanel;
import frontend.Window;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        Window window = new Window("Password Generator", 1280,720, new GeneratorPanel(), JFrame.EXIT_ON_CLOSE);
        window.update();

    }

}
