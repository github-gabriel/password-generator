package frontend;

import javax.swing.*;
import java.awt.*;

public class SharedMethods {

    public static void addCheckBoxes(JPanel panel, GridBagConstraints gbc,JCheckBox[] checkBoxes, String[] checkBoxesText, Font font) {
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < checkBoxes.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            checkBoxes[i] = new JCheckBox(checkBoxesText[i]);
            checkBoxes[i].setFont(font);
            if(panel instanceof GeneratorPanel) {
                checkBoxes[i].setSelected(true);
            }else if(panel instanceof EncodingPanel){
                checkBoxes[0].setSelected(true);
                buttonGroup.add(checkBoxes[i]);
            }
            panel.add(checkBoxes[i], gbc);
        }
    }

}
