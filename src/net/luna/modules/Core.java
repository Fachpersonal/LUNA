package net.luna.modules;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import net.luna.util.FileHelper;
import net.luna.util.ModuleStructure;
import net.luna.util.R;
import net.luna.util.UserData;

/**
 * @author @falscherIdiot
 * @version 2
 * @see ModuleStructure
 */
public class Core implements ModuleStructure {

    /** Constructor */
    public Core() {
        start();
        System.out.println("Welcome to " + R.language.get("callName"));
        stop();
    }

    /**
     * Start Method
     */
    @Override
    public void start() {
        R.core = this;
        R.config = new HashMap<String, String>();
        R.language = new HashMap<String, String>();
        R.user = new HashMap<String, UserData>();
        R.fileHelper = new FileHelper();
        try {
            if (R.loadLanguage(false)) {
                R.logger = new Logger();
                if (R.loadConfig(false)) {
                    R.logger.INFO(R.language.get("configLoaded"));
                } else {
                    R.loadConfig(true);
                    R.logger.WARNING(R.language.get("configLoadError"));
                }
                R.logger.INFO(R.language.get("languageLoaded"));
            } else {
                R.loadLanguage(true);
                R.logger = new Logger();
                if (R.loadConfig(false)) {
                    R.logger.INFO(R.language.get("configLoaded"));
                } else {
                    R.loadConfig(true);
                    R.logger.WARNING(R.language.get("configLoadError"));
                }
                R.logger.INFO(R.language.get("languageLoadError"));
            }
            R.loadUser();
            if (!R.user.containsKey("admin")) {
                R.user.put("admin",
                        new UserData("admin", "password", "falscher", "Idiot", LocalDate.of(2003, 4, 23), 18, true));
            }
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        R.io = new LunaIO();
        R.logger.INFO("Core " + R.language.get("moduleStart"));
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.WARNING("Core " + R.language.get("moduleStop"));
        R.logger.stop();
        R.config.clear();
    }

}
