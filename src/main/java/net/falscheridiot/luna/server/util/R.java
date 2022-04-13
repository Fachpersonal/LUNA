package net.falscheridiot.luna.server.util;

import java.net.ServerSocket;
import java.util.HashMap;

import net.falscheridiot.luna.server.modules.ClientHandler;
import net.falscheridiot.luna.server.modules.Core;
import net.falscheridiot.luna.server.modules.Logger;

/**
 * @author @falscherIdiot
 * @version 1.5
 * @since 08-03-2022
 */
public class R {
    public static int buildNr = 21; // Github commit counter [13-03-2022]

    // ? MODULES
    public static Core core = null;
    public static FileHelper fileHelper = null;
    public static Logger logger = null;

    // ? SERVER-CLIENT
    public static ServerSocket serverSocket = null;
    public static HashMap<String, ClientHandler> users = new HashMap<>();

    public static void broadcast(String message) {
        for (ClientHandler ch : users.values()) {
            ch.send("[BROADCAST] :: " + message);
        }
    }

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
