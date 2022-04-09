package net.falscheridiot.luna.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
        String cmd = "";
        boolean connected = false;
        Socket s = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        while ((cmd = System.console().readLine("\n>> ")) != null) {
            if (cmd.charAt(0) != '/') { // ! if not a command
                if (cmd.charAt(0) == '>') { // ! Send message to server!
                    if (connected) {
                        try {
                            cmd = cmd.substring((cmd.charAt(1) == ' ' ? 2 : 1));
                            pw.println(cmd);
                            pw.flush();
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("Invalid message!");
                            pw.println("");
                            pw.flush();
                        }
                        String rply;
                        while ((rply = br.readLine()) != null) {
                            if (rply.equals("LUNA-END")) {
                                break;
                            }
                            System.out.println(rply);
                        }
                    } else {
                        System.out.println("You are not connected to a server.");
                    }
                    continue;
                }
                System.out.println("You entered: " + cmd);
                continue;
            }
            cmd = cmd.substring(1);
            if (cmd.equals("exit")) {
                System.exit(0);
            } else if (cmd.equals("help")) { // ! Help-Menu
                System.out.println("\n\nAvailable commands:\n");
                System.out.println("/exit - exits the program");
                System.out.println("/help - prints this help");
                System.out.println("/connect - connects to the server");
                System.out.println("/disconnect - disconnects from the server");
            } else if (cmd.equals("connect")) { // ! Connect to the server
                // ! INPUT
                if (connected) {
                    System.out.println("Already connected!");
                    continue;
                }
                System.out.println("Enter hostname: ");
                String hostname = System.console().readLine();
                System.out.println("Enter port: ");
                String port = System.console().readLine();
                try {
                    Integer.parseInt(port);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port!");
                    continue;
                }
                if (Integer.parseInt(port) == 8105) {
                    // ! INPUT
                    System.out.println("Enter username: ");
                    String username = System.console().readLine();
                    System.out.println("Enter password: ");
                    String password = String.valueOf(System.console().readPassword());
                    System.out.println("hostname: " + hostname);
                    System.out.println("port: " + port);
                    // System.out.println("WARNING: You are connecting to the default port 8105!");
                    System.out.println("username: " + username);
                    System.out.println("password: " + password);

                    // ! Connect to the server
                    s = new Socket(hostname, Integer.parseInt(port));
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    pw = new PrintWriter(s.getOutputStream());
                    if (Integer.parseInt(port) == 8105) {
                        pw.println(username);
                        pw.println(password);
                        pw.flush();
                    }
                } else { // ! Connect to the server
                    s = new Socket(hostname, Integer.parseInt(port));
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    pw = new PrintWriter(s.getOutputStream());
                }
                connected = true;
                System.out.println("connected to server!");
            } else if (cmd.equals("disconnect")) { // ! Disconnect from server
                if (!connected) {
                    System.out.println("Not connected!");
                    continue;
                }
                s.close();
                System.out.println("disconnected from server!");
                connected = false;
            } else { // ! Unknown command
                System.out.println("Unknown command: " + cmd);
            }
        }
    }
}
