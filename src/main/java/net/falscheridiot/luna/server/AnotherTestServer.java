package net.falscheridiot.luna.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AnotherTestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(23043);
        System.out.println("Server started [port: 23043]");
        Socket client = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter pw = new PrintWriter(client.getOutputStream());
        System.out.println("Client connected");
        while (client.isConnected()) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
            pw.println("ECHO :: " + line);
            pw.println("LUNA-END");
            pw.flush();
        }
        if (!client.isConnected()) {
            System.out.println("Client disconnected");
            br.close();
            pw.close();
            client.close();
            ss.close();
            System.exit(0);
        }
    }
}
