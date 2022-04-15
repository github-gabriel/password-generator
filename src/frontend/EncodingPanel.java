package frontend;

import backend.Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class EncodingPanel extends JPanel implements ActionListener {

    private static final Font HEADING_FONT = new Font("Arial", Font.BOLD, 52);
    private static final Font FONT = new Font("Arial", Font.PLAIN, 38);
    private static final Font OUTPUT_FONT = new Font("Arial", Font.PLAIN, 30);

    private GridBagConstraints gbc;

    private JTextArea encodedPassword, password;
    private JButton generateBtn;

    private static final String[] CHECKBOXES_TEXT = {"Base 64","","",""};
    private static final JCheckBox[] CHECKBOXES = new JCheckBox[4];

    public EncodingPanel() {

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
        JLabel heading = new JLabel("Encoding Algorithm: ");
        heading.setFont(HEADING_FONT);
        add(heading, gbc);

        SharedMethods.addCheckBoxes(this, gbc,CHECKBOXES, CHECKBOXES_TEXT, FONT);

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

        gbc.gridx = 0;
        gbc.gridy = 6;
        password = new JTextArea();
        password.setEditable(true);
        password.setPreferredSize(new Dimension(600, 76));
        password.setLineWrap(true);
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        password.setFont(OUTPUT_FONT);
        password.setCursor(null);
        add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        encodedPassword = new JTextArea();
        encodedPassword.setEditable(false);
        encodedPassword.setPreferredSize(new Dimension(600, 76));
        encodedPassword.setLineWrap(true);
        encodedPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        encodedPassword.setFont(OUTPUT_FONT);
        encodedPassword.setCursor(null);
        add(encodedPassword, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == generateBtn) {

            boolean base64 = CHECKBOXES[0].isSelected();

            encodedPassword.setText(Encoder.encode(password.getText(), base64));

        }

    }

}
