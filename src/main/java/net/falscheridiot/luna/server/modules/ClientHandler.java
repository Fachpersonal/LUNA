package net.falscheridiot.luna.server.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import net.falscheridiot.luna.server.util.ModuleStructure;
import net.falscheridiot.luna.server.util.R;
import net.falscheridiot.luna.server.util.UserData;
import net.falscheridiot.luna.server.util.errors.UserNotFound;

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
            {
                String[] login = receive();
                if (login.length != 2) {
                    R.logger.WARNING("Invalid client connected! [" + socket.getInetAddress() + "]");
                    send("Invalid client!");
                    stop();
                }
                try {
                    this.userData = new UserData(login[0], login[1]);
                    send("&LUNA-LOGIN&");
                } catch (UserNotFound e) {
                    R.logger.ERROR(e);
                    send("&LUNA-LOGIN-ERR&");
                    stop();
                }
            }
            // String username = br.readLine();
            // System.out.println("Username: " + username);
            // String password = br.readLine();
            // System.out.println("Password: " + password);
            // {
            // String tmp = br.readLine();
            // System.out.println(tmp);
            // if (tmp.equals("&LUNA-ENDL")) {
            // System.out.println("Login failed!");
            // send("&LUNA-LOGIN-ERR&");
            // stop();
            // }
            // send("&LUNA-ENDL&");
            // }
            // try {
            // this.userData = new UserData(username, password);
            // } catch (UserNotFound e) {
            // R.logger.ERROR(e);
            // stop();
            // }
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
        start();
    }

    @Override
    public void start() {
        R.logger.INFO("ClientHandler started [" + socket.getInetAddress() + "]");
        run();
    }

    @Override
    public void stop() {
        try {
            pw.close();
            br.close();
            R.logger.INFO("ClientHandler stopped [" + socket.getInetAddress() + "]");
            socket.close();
            R.users.remove(userData.gUsername());
        } catch (IOException e) {
            R.logger.ERROR(e);
        }
    }

    @Override
    public void run() {
        String[] line;
        while (!socket.isClosed() && (line = receive()) != null && line.length != 0) {
            // System.out.println(line);
            for (String str : line) {
                str = (str.charAt(0) == '_' ? str.substring(1) : str);
                if (str.equals("&LUNA-CLIENT-QUIT&")) {
                    stop();
                }
                R.logger.LOG("[" + socket.getInetAddress() + "] >> " + str);
                send("[SERVER-ECHO] :: " + str);
            }
        }
        // while ((line = br.readLine()) != null) {
        // line = line.charAt(0) == '_' ? line.substring(1) : line;
        // if (line.equals("&LUNA-CLIENT-QUIT&")) {
        // break;
        // }
        // R.logger.INFO("Received: " + line);
        // send("ECHO:: " + line);
        // }
        // stop();
    }

    public void send(String msg) {
        pw.println(msg);
        pw.println("&LUNA-ENDL&");
        pw.flush();
    }

    public String[] receive() {
        ArrayList<String> received = new ArrayList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '_') {
                    line = line.substring(1);
                } else if (line.equals("&LUNA-ENDL&")) {
                    // ?return (String[]) received.toArray();
                    return received.toArray(new String[received.size()]);

                    // String[] tmp = new String[received.size()];
                    // for (String string : received) {
                    // tmp[received.indexOf(string)] = string;
                    // }
                    // return tmp;
                }
                received.add(line);
            }
        } catch (IOException e) {
            R.logger.ERROR(e);
            return null;
        }
        return null;
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
