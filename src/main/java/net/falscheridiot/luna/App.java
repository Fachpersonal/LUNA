package net.falscheridiot.luna;

import net.falscheridiot.luna.client.ConsoleClient;
import net.falscheridiot.luna.server.modules.Core;

public class App {
    public static void main(String[] args) {
        if (args[0].equals("-c")) {
            if (args.length == 2) {
                new ConsoleClient(args[1], Integer.parseInt(args[2]));
            } else {
                System.out.println("Usage: java -jar Luna.jar -c <host> <port>");
            }
        } else if (args.length == 1) {
            if (args[0].equals("-toggleServer=true")) {
                new Core(true);
            } else if (args[0].equals("-toggleServer=false")) {
                new Core(false);
            } else {
                System.out.println("Usage: java -jar Luna.jar -toggleServer=[true|false]");
            }
        } else {
            System.out.println("Usage: java -jar Luna.jar -c <host> <port>");
            System.out.println("Usage: java -jar Luna.jar -toggleServer=[true|false]");
        }
    }
}
