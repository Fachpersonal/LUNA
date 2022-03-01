package net.luna;

public class App {

    public static String[] arguments;

    public static void main(String[] args) {
        arguments = args;
        System.out.println("Welcome to " + Config.getConfig().getCallName());
    }
}
