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

// Icons von https://www.flaticon.com/de/

public class GeneratorPanel extends JPanel implements ActionListener {

    private static final Font HEADING_FONT = new Font("Arial", Font.BOLD, 52);
    private static final Font FONT = new Font("Arial", Font.PLAIN, 38);
    private static final Font OUTPUT_FONT = new Font("Arial", Font.PLAIN, 30);

    private GridBagConstraints gbc;

    private JTextArea textArea;
    private JButton generateBtn, copyBtn, encodeBtn;
    private JSpinner spinner;

    private static final String[] CHECKBOXES_TEXT = {"Numbers", "Special characters", "Uppercase Letters", "Lowercase Letters"};
    private static final JCheckBox[] CHECKBOXES = new JCheckBox[4];

    public GeneratorPanel() {
        initComponents();
    }

    private void initComponents() {

        InputStream inputStream;
        Image img;
        Image newImg;

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        this.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel heading = new JLabel("Generate Password with: ");
        heading.setFont(HEADING_FONT);
        add(heading, gbc);

        SharedMethods.addCheckBoxes(this, gbc, CHECKBOXES, CHECKBOXES_TEXT, FONT);

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
            inputStream = new FileInputStream(new File("src/resources/runIcon.png"));
            img = ImageIO.read(inputStream);
            newImg = img.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
            generateBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        generateBtn.setPreferredSize(new Dimension(72, 72));
        generateBtn.addActionListener(this);
        generateBtn.setFont(FONT);
        add(generateBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        copyBtn = new JButton();
        try {
            inputStream = new FileInputStream(new File("src/resources/copyIcon.png"));
            img = ImageIO.read(inputStream);
            newImg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_AREA_AVERAGING);
            copyBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        copyBtn.setPreferredSize(new Dimension(72, 72));
        copyBtn.addActionListener(this);
        copyBtn.setFont(FONT);
        add(copyBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        encodeBtn = new JButton();
        try {
            inputStream = new FileInputStream(new File("src/resources/encodeIcon.png"));
            img = ImageIO.read(inputStream);
            newImg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_AREA_AVERAGING);
            encodeBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        encodeBtn.setPreferredSize(new Dimension(72, 72));
        encodeBtn.addActionListener(this);
        encodeBtn.setFont(FONT);
        add(encodeBtn, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generateBtn) {

            boolean numbers = CHECKBOXES[0].isSelected();
            boolean specialChars = CHECKBOXES[1].isSelected();
            boolean upperCase = CHECKBOXES[2].isSelected();
            boolean lowerCase = CHECKBOXES[3].isSelected();

            textArea.setText(Generator.generatePassword((int) spinner.getValue(), numbers, specialChars, upperCase, lowerCase));

        }

        if (e.getSource() == copyBtn) {
            // Copy text to clipboard
            StringSelection stringSelection = new StringSelection(textArea.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            JOptionPane.showMessageDialog(null, "Password copied to clipboard");
        }

        if (e.getSource() == encodeBtn) {
            Window encodeWindow = new Window("Encode Options", 1280, 720, new EncodingPanel(), JFrame.DISPOSE_ON_CLOSE);
        }

    }
}
