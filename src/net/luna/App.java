package net.luna;

import net.luna.modules.Config;
import net.luna.modules.Core;

public class App {

    public static String[] arguments;

    public static void main(String[] args) {
        arguments = args;
        Core.getCore();
        System.out.println("Welcome to " + Config.getConfig().getCallName());
    }
}
