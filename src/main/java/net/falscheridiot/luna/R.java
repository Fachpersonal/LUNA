package net.falscheridiot.luna;

/**
 * @author @falscherIdiot
 * @version 1.6
 * @since 08-03-2022
 */
public class R {
    public static int buildNr = 23; // Github commit counter [13-03-2022]

    // ? MODULES
    public static Core core = null;
    public static FileHelper fileHelper = null;
    public static Logger logger = null;
    public static String logDir = "./logs/";
    public static LunaEvents events = null;

    /**
     * Encrypts given string using SHA-512 Method
     * 
     * @param message
     * @return
     */
    public static String encryptString(String message) {
        try {
            String uString = new java.math.BigInteger(1,
                    java.security.MessageDigest.getInstance("SHA-256").digest(message.getBytes())).toString(16);

            while (uString.length() < 32) {
                uString = "0" + uString;
            }
            return uString;
        } catch (java.security.NoSuchAlgorithmException e) {
            R.logger.ERROR(e);
        }
        return null;
    }
}
