package net.falscheridiot.luna.server.modules;

import net.falscheridiot.luna.server.util.FileHelper;
import net.falscheridiot.luna.server.util.ModuleStructure;
import net.falscheridiot.luna.server.util.R;

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
