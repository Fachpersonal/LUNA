package net.falscheridiot.luna.server.util;

import java.io.File;
import java.io.IOException;

import net.falscheridiot.luna.server.util.errors.UserNotFound;

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
     * @throws UserNotFound
     */
    public UserData(String username, String password)
            throws IOException, UserNotFound {
        this.username = username;
        this.password = R.encryptString(password);
        if (!new File("./res/userData/" + R.encryptString(username) + ".lud").exists()) {
            R.logger.WARNING("UserData not found for user: " + username);
            throw new UserNotFound();
        } else {
            R.logger.INFO("UserData found for user: " + username);
            R.fileHelper.readFile(new File("./res/userData/" + R.encryptString(username) + ".lud"));
            if (!R.fileHelper.gContent().get(1).equals(R.encryptString(password))) {
                R.logger.WARNING("UserData password incorrect for user: " + username);
            } else {
                R.logger.INFO("UserData password correct for user: " + username);
            }
        }
    }

    /*
     * !OLD CONSTRUCTOR!
     * public UserData(BufferedReader br, PrintWriter pw) throws IOException {
     * // TODO :: LOGIN THINGY
     * String un, pssw;
     * pw.print("username :: ");
     * while (true) {
     * un = br.readLine();
     * // ! CLIENT SIDE use System.console().readPassword();
     * pw.print("\npassword :: ");
     * pssw = br.readLine();
     * File f;
     * if (!(f = new File("./res/" + R.encryptString(un) + ".lud")).exists()) {
     * continue;
     * }
     * R.fileHelper.readFile(f);
     * if (!R.fileHelper.gContent().get(1).equals(R.encryptString(pssw))) {
     * pw.print("\nWrong password! please try again!\nusername :: ");
     * continue;
     * }
     * this.username = un;
     * this.password = pssw;
     * break;
     * }
     * }
     */

    public String gUsername() {
        return username;
    }

    public String gPassword() {
        return password;
    }
}
