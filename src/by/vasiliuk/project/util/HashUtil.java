package by.vasiliuk.project.util;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import static by.vasiliuk.project.util.HashUtilProvider.KEY_INSTANCE;
import static by.vasiliuk.project.util.HashUtilProvider.PAGE_SEPARATOR;


public class HashUtil {

    private static final int ITERATIONS = 100;
    private static final int DESIRED_KEY_LEN = 50;



    public static String hash(String password) {

        String hashByteString;
        hashByteString =  Base64.getEncoder().encodeToString(password.getBytes());
        return hashByteString;
    }

    public static boolean check(String pass, String hashFromBase){
        String[] hash = hashFromBase.split(PAGE_SEPARATOR);
        if (hash.length != 1) {
            throw new IllegalStateException(
                    "The stored password must have the form 'salt$hashFromBase'");
        }
        String hashOfInput = hash(pass);
        boolean result = hashOfInput.equals(hash[0]);
        return result;
    }

    private static String hash(String password, byte[] salt) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Empty passwords are not supported.");
        }
        SecretKeyFactory keyFactory ;
        String hashString = new String();
        try {
            keyFactory = SecretKeyFactory.getInstance(KEY_INSTANCE);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LEN));
            hashString =  Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hashString;
    }
}
