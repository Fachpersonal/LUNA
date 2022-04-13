package net.falscheridiot.luna.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ConsoleClient {

    private BufferedReader br;
    private PrintWriter pw;
    private Socket s;

    private final String host;
    private final int port;

    private ConsoleClient(String host, int port) {
        try {
            this.s = new Socket(host, port);
            this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.pw = new PrintWriter(s.getOutputStream());
        } catch (NumberFormatException e) {
            System.out.println("Invalid port!");
            System.exit(1);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IOException!");
            System.exit(1);
        }
        this.host = host;
        this.port = port;
        {// ! LOGIN PROCESS
            String[] login = new String[2];
            login[0] = System.console().readLine("Username: ");
            login[1] = String.valueOf(System.console().readLine("Password: "));
            send(login);
            try {
                String[] r = receive();
                if (r == null && r.length == 1 || r[0].equals("&LUNA-LOGIN-ERR&")) {
                    System.out.println("WELL U FUCKED UP!");
                }
            } catch (NullPointerException e) {
                System.out.println("Login successful!");
            }
        }
        String[] serMSG;
        String cliMSG;
        do {
            cliMSG = "_" + System.console().readLine("> ");
            System.out.println("Sending:  " + cliMSG);
            if (cliMSG.substring(0).equals("exit")) {
                send("&LUNA-CLIENT-QUIT&");
                stop();
            }
            send(cliMSG);
        } while (!s.isClosed() && (serMSG = receive()) != null && serMSG.length != 0
                && !serMSG[0].equals("&LUNA-SERVER-QUIT&"));

        // String serverMSG, clientMSG;
        // try {
        // serverMSG = br.readLine();
        // if (serverMSG.equals("&LUNA-LOGIN-ERR&")) {
        // System.out.println("Login failed!");
        // System.exit(1);
        // } else if (serverMSG.equals("&LUNA-ENDL&")) {
        // System.out.println("Login successful!");
        // } else {
        // System.out.println("Unknown server message!");
        // System.exit(1);
        // }
        // } catch (IOException e1) {
        // e1.printStackTrace();
        // }
        // while (true) {
        // System.out.println("[CLIENT]");
        // clientMSG = "_" + System.console().readLine(":");
        // if (clientMSG.equalsIgnoreCase("QUIT")) {
        // pw.println("&LUNA-CLIENT-QUIT&");
        // pw.flush();
        // break;
        // }
        // send(clientMSG);
        // try {
        // while ((serverMSG = br.readLine()) != null) {
        // if (serverMSG.equals("&LUNA-ENDL&")) {
        // break;
        // } else if (serverMSG.equals("&LUNA-SERVER-QUIT&")) {
        // System.out.println("Server closed connection!");
        // System.exit(0);
        // }
        // System.out.println(serverMSG);
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        stop();
    }

    private void stop() {
        try {
            s.close();
            br.close();
            pw.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void send(String[] messages) {
        for (String message : messages) {
            pw.println(message);
        }
        pw.println("&LUNA-ENDL&");
        pw.flush();
    }

    private void send(String message) {
        pw.println(message);
        pw.println("&LUNA-ENDL&");
        pw.flush();
    }

    private String[] receive() {
        ArrayList<String> received = new ArrayList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("{Received:" + line + "}");
                if (line.equals("&LUNA-ENDL&")) {
                    // ?return (String[]) received.toArray();
                    return received.toArray(new String[received.size()]);

                }
                if (line.equals("&LUNA-LOGIN&")) {
                    return null;
                }
                received.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Socket getSocket() {
        return s;
    }

    public BufferedReader getBufferedReader() {
        return br;
    }

    public PrintWriter getPrintWriter() {
        return pw;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ConsoleClient <host> <port>");
            System.exit(-1);
        } else {
            new ConsoleClient(args[0], Integer.parseInt(args[1]));
        }
    }
}