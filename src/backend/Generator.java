package backend;

import javax.swing.*;
import java.util.Random;

public final class Generator {

    private Generator() {
    }

    final static char[] BIBLIO = {'!', '§', '$', '%', '&', '/', '{', '(', '[', ')', ']', '=', '}', '?', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'};

    public static String generatePassword(int length, boolean numbers, boolean specialChars, boolean upperCase, boolean lowerCase) {

            // Check if all parameters are false
            if (!numbers && !specialChars && !upperCase && !lowerCase) {
                JOptionPane.showMessageDialog(null, "At least one of the options must be selected!");
                return null;
            }

            StringBuilder passwort = new StringBuilder();

            for(int i = 0; i < length;) {

                int random = new Random().nextInt(BIBLIO.length);

                // Check if random is a number
                if (numbers) {
                    if (Character.isDigit(BIBLIO[random])) {
                        char character = BIBLIO[random];
                        passwort.append(character);
                        i++;
                    }
                }
                // Check if random is a special character
                if (specialChars) {
                    if (!Character.isLetter(BIBLIO[random]) && !Character.isDigit(BIBLIO[random])) {
                        char character = BIBLIO[random];
                        passwort.append(character);
                        i++;
                    }
                }

                // Check if random is a uppercase letter
                if (upperCase) {
                    if (Character.isUpperCase(BIBLIO[random])) {
                        char character = BIBLIO[random];
                        passwort.append(character);
                        i++;
                    }
                }

                // Check if random is a lowercase letter
                if (lowerCase) {
                    if (Character.isLowerCase(BIBLIO[random])) {
                        char character = BIBLIO[random];
                        passwort.append(character);
                        i++;
                    }
                }
            }

            return String.join("", passwort.toString());

    }

}
