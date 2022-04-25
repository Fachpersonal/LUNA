package net.falscheridiot.luna.server.util.commands;

import net.falscheridiot.luna.server.modules.ClientHandler;

public abstract class Command {
    protected final int id;
    protected final String cmd;
    protected String[] args;
    protected String description;

    public Command(String cmd, int id) {
        this.id = id;
        this.cmd = cmd;
        this.args = null;
        this.description = null;
    }

    public Command setArguments(String[] args) {
        this.args = args;
        return this;
    }

    public Command removeArguments() {
        this.args = null;
        return this;
    }

    public Command setDescription(String description) {
        this.description = description;
        return this;
    }

    public Command removeDescription() {
        this.description = null;
        return this;
    }

    public abstract void crun(ClientHandler ch);

    public int gId() {
        return id;
    }

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
