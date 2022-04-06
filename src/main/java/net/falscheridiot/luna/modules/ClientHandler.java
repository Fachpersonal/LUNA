package net.falscheridiot.luna.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import net.falscheridiot.luna.util.ModuleStructure;
import net.falscheridiot.luna.util.R;
import net.falscheridiot.luna.util.UserData;

public class ClientHandler implements ModuleStructure, Runnable {

    private final Socket socket;
    private UserData userData;
    private BufferedReader br;
    private PrintWriter pw;

    public ClientHandler(Socket s) {
        this.socket = s;
        this.userData = null;
        try {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream());
            this.userData = new UserData(br, pw);
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
            pw.close();
            br.close();
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String cmd = br.readLine();
                if (!(cmd.charAt(0) == R.uPREFIX)) {
                    pw.println("ECHO :: " + cmd);
                    continue;
                }
                cmd = cmd.substring(0);
                String[] args = cmd.split(" ");
                // TODO :: ADD FUCTIONS
                if (args[0].equalsIgnoreCase("createAccount")) {
                    if (args.length == 1) {

                    }
                }
            } catch (IOException e) {
                R.logger.ERROR(e);
            }
        }

    }

    public Socket gSocket() {
        return socket;
    }

    public UserData gUserData() {
        return userData;
    }

    public BufferedReader gBufferedReader() {
        return br;
    }

    public PrintWriter gPrintWriter() {
        return pw;
    }
}
