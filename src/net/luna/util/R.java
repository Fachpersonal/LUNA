package net.luna.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import net.luna.modules.ClientHandler;
import net.luna.modules.Core;
import net.luna.modules.Logger;
import net.luna.modules.Server;

/**
 * @author @falscherIdiot
 * @version 3
 */
public class R {
    public static Server server = null;
    public static Core core = null;
    public static FileHelper fileHelper = null;
    public static Logger logger = null;
    public static HashMap<String, String> config;
    public static ArrayList<ClientHandler> clients;

    /**
     * 
     * @return -1 Syntax Error
     * @throws IOException
     */
    public static int loadConfig(boolean defaultConfig) throws IOException {
        if (!defaultConfig) {
            fileHelper.readFile("./res/system.conf");
            if (fileHelper.gFileLength() > 0) {
                ArrayList<String> tmp = fileHelper.gContent();
                for (String line : tmp) {
                    if (!line.contains("=") && !line.contains(";")) {
                        return -1;
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
                return 1;
            }
        } else {
            config.put("logDir", "./logs");
            config.put("callName", "LUNA");
            config.put("moduleStart", "module started");
            config.put("moduleStop", "module stopped");
            config.put("configLoaded", "Config succesfully loaded");
            config.put("configLoadError", "Config could not be loaded! loading default config!");
            config.put("serverIP", "localhost");
            config.put("serverPORT", "8105");
        }
        return -1;
    }
}
