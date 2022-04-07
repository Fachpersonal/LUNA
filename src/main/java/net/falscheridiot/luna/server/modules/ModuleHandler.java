package net.falscheridiot.luna.server.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import net.falscheridiot.luna.server.util.ModuleStructure;
import net.falscheridiot.luna.server.util.R;

public class ModuleHandler implements ModuleStructure, Runnable {

    private final Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public ModuleHandler(Socket s) {
        this.socket = s;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        start();
    }

    @Override
    public void start() {
        run();
    }

    @Override
    public void stop() {
        try {
            socket.close();
            br.close();
            pw.close();
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public Socket gSocket() {
        return socket;
    }

}