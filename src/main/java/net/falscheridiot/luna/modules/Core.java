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
        start();
        System.out.println("Welcome to LUNA");
        stop();
    }

    /**
     * Start Method
     */
    @Override
    public void start() {
        R.core = this;
        R.users = new HashMap<String, UserData>();
        R.fileHelper = new FileHelper();
        try {
            R.loadUsers();
            if (!R.users.containsKey("admin")) {
                R.users.put("admin",
                        new UserData("admin", "password", true));
            }
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        R.logger.INFO("Core module started");
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.WARNING("Core module started");
        R.logger.stop();
        System.exit(1);
    }

}
