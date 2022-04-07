package net.falscheridiot.luna.server.util.commands;

import net.falscheridiot.luna.server.modules.ClientHandler;

public abstract class Command {
    protected final String cmd;
    protected final String[] args;
    protected final String description;

    public Command(String cmd, String[] args, String description) {
        this.cmd = cmd;
        this.args = args;
        this.description = description;
    }

    public Command(String[] args, String description) {
        this.cmd = args[0];
        {
            String[] tmp = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                tmp[i - 1] = args[i];
            }
            this.args = tmp;
        }
        this.description = description;
    }

    public Command(String[] args) {
        this.cmd = args[0];
        {
            String[] tmp = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                tmp[i - 1] = args[i];
            }
            this.args = tmp;
        }
        this.description = null;
    }

    public Command(String cmd) {
        this.cmd = cmd;
        this.args = null;
        this.description = null;
    }

    public abstract void crun(ClientHandler ch);

    public String gCmd() {
        return cmd;
    }

    public String[] gArgs() {
        return args;
    }

    public String gDescription() {
        return description;
    }

}
