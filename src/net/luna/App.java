package net.luna;

import net.luna.util.R;

/**
 * App Start Class
 * 
 * @author @falscherIdiot
 * @version 1
 */
public class App {

    public static String[] arguments;

    public static void main(String[] args) {
        arguments = args;
        System.out.println("Welcome to " + R.config.get("callName"));
    }
}
