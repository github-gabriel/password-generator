package frontend;

import backend.Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class EncodingPanel extends JPanel implements ActionListener, DocumentListener {

    private static final Font HEADING_FONT = new Font("Arial", Font.BOLD, 52);
    private static final Font FONT = new Font("Arial", Font.PLAIN, 38);
    private static final Font OUTPUT_FONT = new Font("Arial", Font.PLAIN, 30);
    private static final Font OUTPUT_FONT_SMALL = new Font("Arial", Font.PLAIN, 20);

    private GridBagConstraints gbc;
    private ButtonGroup buttonGroup = new ButtonGroup();

    private JTextArea encodedPassword, password, passwordToCompare;
    private JButton generateBtn, copyBtn;

    private Graphics g;

    private static final String[] CHECKBOXES_TEXT = {"Base 64", "MD5", "SHA256", "SHA512"};
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

        SharedMethods.addCheckBoxes(this, gbc, CHECKBOXES, CHECKBOXES_TEXT, FONT);

        gbc.gridx = 1;
        gbc.gridy = 6;
        generateBtn = new JButton();
        try {
            inputStream = new FileInputStream(new File("src/resources/runIcon.png"));
            img = ImageIO.read(inputStream);
            newImg = img.getScaledInstance(42, 42, Image.SCALE_AREA_AVERAGING);
            generateBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        generateBtn.setPreferredSize(new Dimension(60, 60));
        generateBtn.addActionListener(this);
        generateBtn.setFont(FONT);
        add(generateBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        copyBtn = new JButton();
        try {
            inputStream = new FileInputStream(new File("src/resources/copyIcon.png"));
            img = ImageIO.read(inputStream);
            newImg = img.getScaledInstance(46, 46, java.awt.Image.SCALE_AREA_AVERAGING);
            copyBtn.setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        copyBtn.setPreferredSize(new Dimension(60, 60));
        copyBtn.addActionListener(this);
        copyBtn.setFont(FONT);
        add(copyBtn, gbc);

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
        encodedPassword.setFont(OUTPUT_FONT_SMALL);
        encodedPassword.setCursor(null);
        encodedPassword.getDocument().addDocumentListener(this);
        add(encodedPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        passwordToCompare = new JTextArea();
        passwordToCompare.setEditable(true);
        passwordToCompare.setPreferredSize(new Dimension(600, 76));
        passwordToCompare.setLineWrap(true);
        passwordToCompare.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        passwordToCompare.setFont(OUTPUT_FONT_SMALL);
        passwordToCompare.setCursor(null);
        passwordToCompare.getDocument().addDocumentListener(this);
        add(passwordToCompare, gbc);

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        String input = passwordToCompare.getText();
        String hashedPassword = encodedPassword.getText();
        if(input.equals(hashedPassword)){
            g.setColor(Color.GREEN);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }else{
            g.setColor(Color.RED);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generateBtn) {

            boolean base64 = CHECKBOXES[0].isSelected();
            boolean md5 = CHECKBOXES[1].isSelected();
            boolean sha256 = CHECKBOXES[2].isSelected();
            boolean sha512 = CHECKBOXES[3].isSelected();

            try {
                encodedPassword.setText(Encoder.encode(password.getText(), base64, md5, sha256, sha512));
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }

        }

        if (e.getSource() == copyBtn) {
            // Copy to clipboard
            StringSelection stringSelection = new StringSelection(encodedPassword.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            JOptionPane.showMessageDialog(null, "Encoded Password copied to clipboard!");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        g = this.getGraphics();
        String input = passwordToCompare.getText();
        String hashedPassword = encodedPassword.getText();
        if(input.equals(hashedPassword)){
            g.setColor(Color.GREEN);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }else{
            g.setColor(Color.RED);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        g = this.getGraphics();
        String input = passwordToCompare.getText();
        String hashedPassword = encodedPassword.getText();
        if(input.equals(hashedPassword)){
            g.setColor(Color.GREEN);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }else{
            g.setColor(Color.RED);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        g = this.getGraphics();
        String input = passwordToCompare.getText();
        String hashedPassword = encodedPassword.getText();
        if(input.equals(hashedPassword)){
            g.setColor(Color.GREEN);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }else{
            g.setColor(Color.RED);
            g.fillRoundRect(passwordToCompare.getX() + passwordToCompare.getWidth() + 18, passwordToCompare.getY() + 15, 50,50,50,50);
        }
    }

}
