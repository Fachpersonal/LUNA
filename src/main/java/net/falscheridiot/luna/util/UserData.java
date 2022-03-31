package net.falscheridiot.luna.util;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author @falscherIdiot
 * @version 1.2
 * @since 07-03-2022
 */
public class UserData {
    private String username;
    private String password;

    /**
     * Constructor
     * 
     * @param username
     * @param password
     * @throws IOException
     */
    public UserData(String username, String password,
            boolean createFile)
            throws IOException {
        this.username = username;
        this.password = (createFile ? R.encryptString(password) : password);
        if (createFile) {
            ArrayList<String> content = new ArrayList<>();
            content.add(username);
            content.add((createFile ? R.encryptString(password) : password));
            R.fileHelper.writeFile("./res/userData/" + R.encryptString(username) + ".lud", content, false);
        }
    }

    public String gUsername() {
        return username;
    }

    public String gPassword() {
        return password;
    }
}
