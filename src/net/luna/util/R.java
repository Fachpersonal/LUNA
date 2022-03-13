package net.luna.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import net.luna.modules.Core;
import net.luna.modules.Logger;
import net.luna.modules.LunaIO;

/**
 * @author @falscherIdiot
 * @version 3
 * @since 08-03-2022
 */
public class R {
    public static LunaIO io = null;
    public static Thread ioT = null;
    public static Core core = null;
    public static FileHelper fileHelper = null;
    public static Logger logger = null;
    public static HashMap<String, String> config;
    public static HashMap<String, String> language;
    public static HashMap<String, UserData> user;

    /**
     * Loads System Config
     * 
     * @param defaultConfig
     * @return false if something went wrong
     * @throws IOException
     */
    public static boolean loadConfig(boolean defaultConfig) throws IOException {
        if (!defaultConfig) {
            if (!new File("./res/system.conf").exists()) {
                return false;
            }
            fileHelper.readFile(new File("./res/system.conf"));
            if (fileHelper.gFileLength() > 0) {
                ArrayList<String> tmp = fileHelper.gContent();
                for (String line : tmp) {
                    if (!line.contains("=") && !line.contains(";")) {
                        return false;
                    }
                    char[] tline = line.toCharArray();
                    String key = "";
                    String val = "";
                    boolean regex = false;
                    for (char c : tline) {
                        if (regex) {
                            val += c;
                        } else {
                            if (c == '=') {
                                regex = true;
                            } else {
                                key += c;
                            }
                        }
                    }
                    config.put(key, val);
                }
                return true;
            }
        } else {
            config.put("logDir", "./logs/");
            config.put("serverIP", "localhost");
            config.put("serverPORT", "8105");
            config.put("cmdPrefix", "!l");
            return true;
        }
        return false;
    }

    /**
     * Loads System Language
     * 
     * @param defaultLanguage
     * @return false if something went wrong
     * @throws IOException
     */
    public static boolean loadLanguage(boolean defaultLanguage) throws IOException {

        if (!defaultLanguage) {
            if (!new File("./res/system.lang").exists()) {
                return false;
            }
            fileHelper.readFile(new File("./res/system.lang"));
            if (fileHelper.gFileLength() > 0) {
                ArrayList<String> tmp = fileHelper.gContent();
                for (String line : tmp) {
                    if (!line.contains("=") && !line.contains(";")) {
                        return false;
                    }
                    char[] tline = line.toCharArray();
                    String key = "";
                    String val = "";
                    boolean regex = false;
                    for (char c : tline) {
                        if (regex) {
                            val += c;
                        } else {
                            if (c == '=') {
                                regex = true;
                            } else {
                                key += c;
                            }
                        }
                    }
                    language.put(key, val);
                }
                return true;
            }
        } else {
            language.put("callName", "LUNA");
            language.put("moduleStart", "module started");
            language.put("moduleStop", "module stopped");
            language.put("configLoaded", "System config succesfully loaded");
            language.put("configLoadError", "System config could not be loaded! loading default config!");
            language.put("languageLoaded", "System language succesfully loaded");
            language.put("languageLoadError", "System language could not be loaded! loading default language(EN_US)!");
            language.put("LMPFault1", "CMD-Prefix at wrong spot");
            language.put("systemStart", "System starting");
            language.put("systemShutdown", "System shutting down");
            language.put("missingCommand", "Command does not exist");
            language.put("userRegistered", "New user registered! ");
            language.put("loggedIn", "Successfully logged in as ");
            language.put("userNotExisting", "User does not exists!");
            language.put("loginError", "Username or password is wrong!\nTry again!");
            language.put("loginRegister", "Type \'register\' as username to register a new User");
            language.put("loginTries", "Somebody tried to login more than five times!");
            language.put("loginUserErrorLength", "Username needs to be at least 4 characters long!");
            language.put("loginPasswordErrorLenght", "Passwor needs to be at least 4 characters long!");
            return true;
        }
        return false;
    }

    /**
     * loads Users from Files
     * 
     * @throws IOException
     */
    public static void loadUser() throws IOException {
        File[] userFiles = new File("./res/userData/").listFiles();
        for (File file : userFiles) {
            if (!file.getPath().contains(".lud")) {
                continue;
            }
            R.fileHelper.readFile(file);
            ArrayList<String> content = R.fileHelper.gContent();
            int size = content.size();
            String un = null, pw = null, firstName = null, surName = null;
            LocalDate birthday = null;
            int age = 0;
            un = content.get(0);
            pw = content.get(1);
            firstName = size >= 3 ? content.get(2) : null;
            surName = size >= 4 ? content.get(3) : null;
            try {
                birthday = size >= 5 ? LocalDate.parse(content.get(4)) : null;
            } catch (java.time.format.DateTimeParseException e) {
                birthday = null;
                R.logger.ERROR(e);
            }
            try {
                age = size >= 6 ? Integer.parseInt(content.get(5)) : null;
            } catch (java.lang.NumberFormatException e) {
                age = -1;
                R.logger.ERROR(e);
            }
            user.put(un, new UserData(un, pw, firstName, surName, birthday, age, false));
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
