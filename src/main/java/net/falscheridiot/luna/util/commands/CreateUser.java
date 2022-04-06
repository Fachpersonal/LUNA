package net.falscheridiot.luna.util.commands;

import java.io.BufferedReader;
import java.io.PrintWriter;

import net.falscheridiot.luna.modules.ClientHandler;

public class CreateUser extends Command {

    public CreateUser() {
        super(new String[] { "createUser", "username", "password" });
    }

    @Override
    public void crun(ClientHandler ch) {
        BufferedReader br = ch.gBufferedReader();
        PrintWriter pr = ch.gPrintWriter();

    }
}
