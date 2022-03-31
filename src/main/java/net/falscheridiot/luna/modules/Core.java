package net.falscheridiot.luna.modules;

import java.io.IOException;
import java.util.HashMap;

import net.falscheridiot.luna.util.FileHelper;
import net.falscheridiot.luna.util.ModuleStructure;
import net.falscheridiot.luna.util.R;
import net.falscheridiot.luna.util.UserData;

/**
 * @author @falscherIdiot
 * @version 1.3
 * @see ModuleStructure
 */
public class Core implements ModuleStructure {

    /** Constructor */
    public Core() {
        System.out.println("Welcome to LUNA");
        start();
        stop();
    }

    /**
     * Start Method
     */
    @Override
    public void start() {
        R.logger = new Logger();
        R.fileHelper = new FileHelper();
        R.logger.INFO("Core-bootup");
        R.core = this;
        R.users = new HashMap<String, UserData>();
        try {
            R.loadUsers();
            if (!R.users.containsKey("admin")) {
                R.users.put("admin",
                        new UserData("admin", "password", true));
                R.logger.INFO("Created default admin Account");
            }
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        R.logger.INFO("Core started");
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.WARNING("Core shutting down");
        R.logger.stop();
        System.exit(1);
    }

}
