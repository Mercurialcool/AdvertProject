package by.vasiliuk.project.util;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;



public class HashUtil {

    private static final int ITERATIONS = 100;
    private static final int SALT_LEN = 32;
    private static final int DESIRED_KEY_LEN = 50;

    public static final String PAGE_SEPARATOR = "\\$";
    public static final String SALT_INSTANCE = "SHA1PRNG";
    public static final String KEY_INSTANCE = "PBKDF2WithHmacSHA1";

    public static String hash(String password) {
        byte[] salt;
        String hashByteString = new String();
        try {
            salt = SecureRandom.getInstance(SALT_INSTANCE).generateSeed(SALT_LEN);
            hashByteString =  Base64.getEncoder().encodeToString(password.getBytes());
         //   hashByteString =  Base64.getEncoder().encodeToString((salt + "$" + hash(password, salt)).getBytes());
        } catch (NoSuchAlgorithmException e) {
            // log todo
            e.printStackTrace();
        }
        return hashByteString;
    }

    public static boolean check(String pass, String hashFromBase){
        String[] saltAndHash = hashFromBase.split(PAGE_SEPARATOR);
        if (saltAndHash.length != 1) {
            throw new IllegalStateException(
                    "The stored password must have the form 'salt$hashFromBase'");
        }
     //   String hashOfInput = hash(pass, Base64.getDecoder().decode(saltAndHash[0]));
        String hashOfInput = hash(pass);
        boolean result = hashOfInput.equals(saltAndHash[0]);
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
            e.printStackTrace();// todo insert log
        }
        return hashString;
    }
}
