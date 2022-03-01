package net.luna.modules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import net.luna.util.ModuleStructure;

public class Config implements ModuleStructure {

    private BufferedReader br;

    private HashMap<String, String> configs;

    private static Config config;

    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private Config() {
        start();
    }

    @Override
    public void start() {
        // Logger.getLogger().INFO("Starting Config module");
        Logger.buf.add(getModuleStartMessage("Config"));
        try {
            File configFile = new File("./settings.conf");
            if (configFile.createNewFile()) {
                Logger.buf.add("Created new Config file $configFilePath:{" + configFile.getAbsolutePath() + "}");
            } else {
                br = new BufferedReader(new FileReader(configFile));
                configs = new HashMap<String, String>();

                String tmp;
                while ((tmp = br.readLine()) != null) {
                    String[] val = tmp.split(" : ");
                    configs.put(val[0], val[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Logger.getLogger().INFO("Config module successfully booted");
        Logger.buf.add(getModuleStartSuccessMessage("Config"));
    }

    @Override
    public void stop() {
        try {
            if (br != null) {
                br.close();
            }
            Logger.getLogger().WARNING(getModuleStopMessage("Config"));
            config = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogDir() {
        if (configs.containsKey("logDir")) {
            return configs.get("logDir");
        }
        return "./logs/";
    }

    public String getCallName() {
        if (configs.containsKey("callName")) {
            return configs.get("callName");
        }
        return "LUNA";
    }

    public String getStorageDir() {
        if (configs.containsKey("logDir")) {
            return configs.get("logDir");
        }
        return "./storage/";
    }

    public String getServerPort() {
        if (configs.containsKey("serverPort")) {
            return configs.get("serverPort");
        }
        return "8105";
    }

    public String getServerIP() {
        if (configs.containsKey("serverIP")) {
            return configs.get("serverIP");
        }
        return "localhost";
    }

    public String getServerTimeOut() {
        if (configs.containsKey("serverTimeout")) {
            return configs.get("serverTimeout");
        }
        return "120000";
    }

    public String getDisplayClientMessages() {
        if (configs.containsKey("displayClientMessage")) {
            return configs.get("displayClientMessage");
        }
        return "true";
    }

    public String getFinanceFilePath() {
        if (configs.containsKey("financeFilePath")) {
            return configs.get("financeFilePath");
        }
        return "./data.fin";
    }

    public String getFinanceHistoryFilePath() {
        if (configs.containsKey("financeHistoryFilePath")) {
            return configs.get("financeHistoryFilePath");
        }
        return "./history.fin";
    }

    public String getModuleStartMessage(String moduleName) {
        return "Starting " + moduleName + " module";
    }

    public String getModuleStartSuccessMessage(String moduleName) {
        return moduleName + "module booted successfully";
    }

    public String getModuleStopMessage(String moduleName) {
        return moduleName + "module stopped";
    }

    public String getLogNewFileMessage(String filepath) {
        return "new file created ${@" + filepath + "}";
    }
}
