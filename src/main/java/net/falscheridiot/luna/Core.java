package net.falscheridiot.luna;

import java.util.Arrays;

/**
 * @author @falscherIdiot
 * @version 2
 */
public class Core {

    /** Constructor */
    public Core() {
        System.out.println("Welcome to LUNA");
        R.fileHelper = new FileHelper();
        R.events = new LunaEvents();
        R.logger = new Logger();
        new Todo();
        R.events.RegisterTriggerEvent("core::stop", "Shuts down LUNA", args -> {
            // System.out.println("Stopping LUNA");
            R.logger.WARNING("Stopping LUNA");
            System.exit(0);
        });
        R.events.RegisterTriggerEvent("core::help", "Shows this menu", args -> {
            System.out.println("LUNA Help Menu");
            System.out.println("---------------------------------");
            R.events.printEvents();
            System.out.println("---------------------------------");
        });

        while (true) {
            String cmd = System.console().readLine();
            String[] input = cmd.split(" ");
            R.logger.LOG(cmd);
            if (!R.events.TriggerEvent(input[0], Arrays.copyOfRange(input, 1, input.length))) {
                R.logger.INFO("Command does not exists! [" + input[0] + "]");
            }
        }
    }

}
