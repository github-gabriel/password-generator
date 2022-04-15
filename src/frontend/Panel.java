package frontend;

import backend.Generator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Panel extends JPanel implements ActionListener {

    private static final Font HEADING_FONT = new Font("Arial", Font.BOLD, 52);
    private static final Font FONT = new Font("Arial", Font.PLAIN, 38);
    private static final Font OUTPUT_FONT = new Font("Arial", Font.PLAIN, 30);

    private GridBagConstraints gbc;

    private JTextArea textArea;
    private JButton generateBtn, copyBtn;
    private JSpinner spinner;

    private static final String[] CHECKBOXES_TEXT = {"Numbers","Special characters", "Uppercase Letters", "Lowercase Letters"};
    private static final JCheckBox[] CHECKBOXES = new JCheckBox[4];

    public Panel() {
        initComponents();
    }

    private void initComponents() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        this.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel("Generate Password with: ");
        label.setFont(HEADING_FONT);
        add(label, gbc);

        addCheckBoxes(CHECKBOXES, CHECKBOXES_TEXT);

        SpinnerNumberModel model = new SpinnerNumberModel(8, 1, 50, 1);

        gbc.gridx = 0;
        gbc.gridy = 5;
        spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(100, 42));
        spinner.setFont(FONT);
        add(spinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(600, 76));
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        textArea.setFont(OUTPUT_FONT);
        textArea.setCursor(null);
        add(textArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        generateBtn = new JButton();
        try {
            InputStream inputStream = new FileInputStream(new File("src/resources/runIcon.png"));
            Image img = ImageIO.read(inputStream);
            Image newImg = img.getScaledInstance( 44, 44,  Image.SCALE_AREA_AVERAGING ) ;
            generateBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        generateBtn.setPreferredSize(new Dimension(52, 52));
        generateBtn.addActionListener(this);
        generateBtn.setFont(FONT);
        add(generateBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        copyBtn = new JButton();
        try {
            InputStream inputStream = new FileInputStream(new File("src/resources/copyIcon.png"));
            Image img = ImageIO.read(inputStream);
            Image newImg = img.getScaledInstance( 40, 40,  java.awt.Image.SCALE_AREA_AVERAGING );
            copyBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        copyBtn.setPreferredSize(new Dimension(52, 52));
        copyBtn.addActionListener(this);
        copyBtn.setFont(FONT);
        add(copyBtn, gbc);

    }

    private void addCheckBoxes(JCheckBox[] checkBoxes, String[] checkBoxesText) {
        for (int i = 0; i < checkBoxes.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            checkBoxes[i] = new JCheckBox(checkBoxesText[i]);
            checkBoxes[i].setFont(FONT);
            checkBoxes[i].setSelected(true);
            add(checkBoxes[i], gbc);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == generateBtn) {

            boolean numbers = CHECKBOXES[0].isSelected();
            boolean specialChars = CHECKBOXES[1].isSelected();
            boolean upperCase = CHECKBOXES[2].isSelected();
            boolean lowerCase = CHECKBOXES[3].isSelected();

            textArea.setText(Generator.generatePassword((int) spinner.getValue(), numbers, specialChars, upperCase, lowerCase));

        }

        if(e.getSource() == copyBtn) {
            // Copy text to clipboard
            StringSelection stringSelection = new StringSelection(textArea.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
    }
}
