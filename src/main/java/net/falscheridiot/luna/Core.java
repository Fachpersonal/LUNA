package net.falscheridiot.luna;

import java.util.Arrays;

/**
 * @author @falscherIdiot
 * @version 2
 */
public class Core {

    /** Constructor */
    public Core() {
        R.fileHelper = new FileHelper();
        R.events = new LunaEvents();
        R.logger = new Logger();
        R.logger.INFO("Welcome to LUNA");
        new Todo();
        R.events.RegisterTriggerEvent("core::stop", args -> {
            // System.out.println("Stopping LUNA");
            R.logger.WARNING("Stopping LUNA");
            System.exit(0);
        });

        while (true) {
            String cmd = System.console().readLine(" > ");

            String[] input = cmd.split(" ");
            R.logger.LOG(cmd);
            if (!R.events.TriggerEvent(input[0],
                    (input.length >= 2 ? Arrays.copyOfRange(input, 1, input.length) : null))) {
                R.logger.INFO("Command does not exists! [" + input[0] + "]");
            }
        }
    }

}
