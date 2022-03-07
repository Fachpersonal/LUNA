package net.luna.modules;

import java.io.IOException;

import net.luna.util.FileHelper;
import net.luna.util.ModuleStructure;
import net.luna.util.R;

public class Core extends ModuleStructure {

    public Core() {
        start();
        System.out.println("Welcome to " + R.config.get("callName"));
        stop();
    }

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

    @Override
    public void stop() {
        R.logger.stop();
        R.logger.WARNING("Core " + R.config.get("moduleStop"));
        R.config.clear();
    }

}
