package net.falscheridiot.luna.modules;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import net.falscheridiot.luna.util.FileHelper;
import net.falscheridiot.luna.util.ModuleStructure;
import net.falscheridiot.luna.util.R;
import net.falscheridiot.luna.util.commands.Command;

/**
 * @author @falscherIdiot
 * @version 1.3
 * @see ModuleStructure
 */
public class Core implements ModuleStructure {

    /** Constructor */
    public Core() {
        System.out.println("Welcome to LUNA");
        start();
        stop();
    }

    /**
     * Start Method
     */
    @Override
    public void start() {
        R.logger = new Logger();
        R.fileHelper = new FileHelper();
        R.logger.INFO("Core-bootup");
        R.core = this;
        R.commands = new ArrayList<Command>();

        try {
            R.uServerSocket = new ServerSocket(8105);
            R.uServerSocket.setReuseAddress(true);
            R.uServerSocket.setSoTimeout(60000);
        } catch (IOException e) {
            R.logger.ERROR("User-Server could not be started");
            R.logger.ERROR("Core-bootup failed! Please contact @falscherIdiot");
            stop();
        }
        R.logger.INFO("Core started");
        R.uThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket c = R.uServerSocket.accept();
                        ClientHandler ch = new ClientHandler(c);
                        R.clients.put(R.clients.size() + 1, ch);
                        new Thread(ch).start();
                    } catch (IOException e) {
                        R.logger.ERROR(e);
                    }
                }
            }
        };
        R.uThread.start();
    }

    /**
     * Stop Method
     */
    @Override
    public void stop() {
        R.logger.WARNING("Core shutting down");
        R.logger.stop();
        try {
            R.uServerSocket.close();
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        R.uThread = null;
        System.exit(1);
    }

}
