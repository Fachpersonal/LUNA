package net.falscheridiot.luna.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import net.falscheridiot.luna.modules.Core;
import net.falscheridiot.luna.modules.Logger;

/**
 * @author @falscherIdiot
 * @version 1.4
 * @since 08-03-2022
 */
public class R {
    public static int buildNr = 19; // Github commit counter [13-03-2022]
    public static Core core = null;
    public static FileHelper fileHelper = null;
    public static Logger logger = null;
    public static HashMap<String, UserData> users;

    /**
     * loads Users from Files
     * 
     * @throws IOException
     */
    public static void loadUsers() throws IOException {
        File[] userFiles = new File("./res/userData/").listFiles();
        for (File file : userFiles) {
            if (!file.getPath().contains(".lud")) {
                continue;
            }
            R.fileHelper.readFile(file);
            ArrayList<String> content = R.fileHelper.gContent();
            String un = null, pw = null;
            un = content.get(0);
            pw = content.get(1);
            users.put(un, new UserData(un, pw, false));
            R.fileHelper.unloadSafedFile();
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
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            String uString = new BigInteger(1, md.digest(message.getBytes())).toString(16);

            while (uString.length() < 32) {
                uString = "0" + uString;
            }
            return uString;
        } catch (NoSuchAlgorithmException e) {
            R.logger.ERROR(e);
        }
        return null;
    }
}
