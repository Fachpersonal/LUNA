package net.falscheridiot.luna.server.util.errors;

public class UserNotFound extends Exception {
    public UserNotFound() {
        super("User not found!");
    }
}
