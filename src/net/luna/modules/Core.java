package net.luna.modules;

import java.io.File;

import net.luna.App;
import net.luna.util.ModuleStructure;

public class Core implements ModuleStructure {

    public static boolean loggerOutput = true;

    public static Core core;

    public static Core getCore() {
        if (core == null) {
            core = new Core(App.arguments);
        }
        return core;
    }

    private Core(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                String tmp = args[i].substring(1);
                if (tmp.equalsIgnoreCase("loggerOutput=false")) {
                    loggerOutput = false;
                }
            }
        }
        start();
    }

    @Override
    public void start() {
        Logger.getLogger().INFO(Config.getConfig().getModuleStartMessage("Core"));
        // * Check if all directories exists!
        // * No need to check for logs Directory
        if (new File(Config.getConfig().getStorageDir()).mkdir()) {
            Logger.getLogger()
                    .INFO("Storage directory got created! $storageDir:{" + Config.getConfig().getStorageDir() + "}");
        }
        //
        Logger.getLogger().INFO(Config.getConfig().getModuleStartSuccessMessage("Core"));
    }

    @Override
    public void stop() {
        Logger.getLogger().WARNING(Config.getConfig().getModuleStopMessage("Core"));
        Config.getConfig().stop();
        Logger.getLogger().stop();

        core = null;
    }

}
