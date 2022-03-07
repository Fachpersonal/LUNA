package net.luna.modules;

import java.io.IOException;

import net.luna.util.FileHelper;
import net.luna.util.ModuleStructure;
import net.luna.util.R;

/**
 * @author @falscherIdiot
 * @version 2
 * @see ModuleStructure
 */
public class Core implements ModuleStructure {

    /** Constructor */
    public Core() {
        start();
        System.out.println("Welcome to " + R.config.get("callName"));
        stop();
    }

    /**
     * Start Method
     */
    @Override
    public void start() {
        R.core = this;
        R.logger = new Logger();
        R.fileHelper = new FileHelper();
        try {
            if (R.loadConfig(false) == 1) {
                R.logger.INFO(R.config.get("configLoaded"));
            } else {
                R.loadConfig(true);
                R.logger.ERROR(R.config.get("configLoadError"));
            }
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        R.logger.INFO("Core " + R.config.get("moduleStart"));
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.stop();
        R.logger.WARNING("Core " + R.config.get("moduleStop"));
        R.config.clear();
    }

}
