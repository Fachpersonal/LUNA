package net.falscheridiot.luna.server.modules;

import java.io.IOException;
import java.net.ServerSocket;

import net.falscheridiot.luna.server.util.FileHelper;
import net.falscheridiot.luna.server.util.ModuleStructure;
import net.falscheridiot.luna.server.util.R;

/**
 * @author @falscherIdiot
 * @version 1.4
 * @see ModuleStructure
 */
public class Core implements ModuleStructure {

    /** Constructor */
    public Core() {
        System.out.println("Welcome to LUNA");
        start();
        // stop();
    }

    /**
     * Start Method
     */
    @Override
    public void start() {
        R.logger = new Logger();
        R.fileHelper = new FileHelper();
        R.logger.INFO("Core starting...");
        R.core = this;
        new Thread() {
            @Override
            public void run() {
                try {
                    R.serverSocket = new ServerSocket(8105);
                    R.serverSocket.setReuseAddress(true);
                    // R.serverSocket.setSoTimeout(60000);
                    R.logger.INFO("Core successfully started");
                    while (true) {
                        ClientHandler ch = new ClientHandler(R.serverSocket.accept());
                        R.users.put(ch.gUserData().gUsername(), ch);
                        new Thread(ch).start();
                    }
                } catch (IOException e) {
                    R.logger.ERROR(e);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread started!");
                String cmd;
                while ((cmd = System.console().readLine("> ")) != null) {
                    try {

                        if (cmd.charAt(0) != '/') {
                            R.broadcast("[BROADCAST] >> " + cmd);
                            continue;
                        }
                        cmd = cmd.substring(1);
                        if (cmd.equals("exit")) {
                            R.core.stop();
                            break;
                        }
                    } catch (Exception e) {
                        R.logger.ERROR(e);
                        continue;
                    }
                }
            }
        }.start();
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.WARNING("Core stopping...");
        try {
            R.broadcast("&LUNA-SERVER-QUIT&");
            R.serverSocket.close();
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        R.logger.stop();
        System.exit(1);
    }

}
