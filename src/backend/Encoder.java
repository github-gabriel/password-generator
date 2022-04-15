package backend;

import java.util.Base64;

public final class Encoder {

    private Encoder() {
    }

    public static String encode(String password, boolean base64) {

        if(base64){
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(password.getBytes());
        }

        return password;

    }

}
