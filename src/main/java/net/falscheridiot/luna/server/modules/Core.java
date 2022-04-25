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
    public Core(boolean toggleServer) {
        System.out.println("Welcome to LUNA");
        R.toggledServer = toggleServer;
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
        R.logger.INFO("Core starting... [" + (R.toggledServer ? "toggledServer=true" : "toggledServer=false") + "]");
        R.core = this;
        if (R.toggledServer) {
            R.serverConsoleThread = new Thread() {
                @Override
                public void run() {
                    try {
                        R.serverSocket = new ServerSocket(8105);
                        R.serverSocket.setReuseAddress(true);
                        // R.serverSocket.setSoTimeout(60000);
                        while (!R.serverSocket.isClosed()) {
                            ClientHandler ch = new ClientHandler(R.serverSocket.accept());
                            R.users.put(ch.gUserData().gUsername(), ch);
                            new Thread(ch).start();
                        }
                    } catch (IOException e) {
                        R.logger.ERROR(e);
                    }
                }
            };
            R.serverConsoleThread.start();
            R.serverConsoleThread = new Thread() {
                @Override
                public void run() {
                    // System.out.println("Thread started!");
                    String cmd;
                    while ((cmd = System.console().readLine("> ")) != null) {
                        try {
                            if (cmd.length() == 0) {
                                continue;
                            }
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
            };
            R.serverConsoleThread.start();
            R.logger.INFO("Core successfully started");
        } else {
            R.logger.INFO("Core successfully started");
            String cmd;

            while ((cmd = System.console().readLine(" >  ")) != null) {
                if (cmd.length() == 0) {
                    continue;
                } else if (cmd.equals("exit")) {
                    stop();
                }
                System.out.println("[ECHO] " + cmd);
                R.logger.LOG("Server >> " + cmd);
            }
        }
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.WARNING("Core stopping...");
        if (R.toggledServer) {
            try {
                R.broadcast("&LUNA-Server-QUIT&");
                R.serverSocket.close();
            } catch (IOException e) {
                R.logger.ERROR(e);
            }
        }
        R.logger.stop();
        System.exit(1);
    }

}
