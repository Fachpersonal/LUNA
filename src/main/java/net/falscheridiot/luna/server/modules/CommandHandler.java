package net.falscheridiot.luna.server.modules;

import java.util.HashMap;

import net.falscheridiot.luna.server.util.ModuleStructure;
import net.falscheridiot.luna.server.util.R;
import net.falscheridiot.luna.server.util.commands.Command;

public class CommandHandler implements ModuleStructure {

    public CommandHandler() {
        if (R.commands == null) {
            R.commands = new HashMap<Integer, Command>();
        }
        start();
        stop();
    }

    private void loadAllCommands() {
        for (Integer id : R.commands.keySet()) {
            System.out.println("Command [" + id + "]" + R.commands.get(id).gCmd() + " loaded.")
        }
    }

    private boolean isCommandLoaded(Command cmd) {
        for (int i = 0; i < R.commands.size(); i++) {
            if (R.commands.get(i).gCmd().equals(cmd)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCommandLoaded(int id) {
        for (int i = 0; i < R.commands.size(); i++) {
            if (R.commands.get(i).gId() == id) {
                return true;
            }
        }
        return false;
    }

    public void registerCommand(Command command) {
        if (isCommandLoaded(command)) {
            System.out.println("Command [" + command.gId() + "]" + command.gCmd() + " already loaded.");
            return;
        }
        if (isCommandLoaded(R.commands.size())) {
            System.out.println("Command [" + R.commands.size() + "]" + command.gCmd() + " already loaded.");
            return;
        }
        R.commands.put(R.commands.size(), command);
        R.logger.INFO("Command [" + R.commands.size() + "]" + command.gCmd() + " registered.");
    }

    @Override
    public void start() {
        loadAllCommands();
    }

    @Override
    public void stop() {

    }
}
