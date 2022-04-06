package net.falscheridiot.luna.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

    public UserData(BufferedReader br, PrintWriter pw) throws IOException {
        // TODO :: LOGIN THINGY
        String un, pssw;
        pw.print("username :: ");
        while (true) {
            un = br.readLine();
            // ! CLIENT SIDE use System.console().readPassword();
            pw.print("\npassword :: ");
            pssw = br.readLine();
            File f;
            if (!(f = new File("./res/" + R.encryptString(un) + ".lud")).exists()) {
                continue;
            }
            R.fileHelper.readFile(f);
            if (!R.fileHelper.gContent().get(1).equals(R.encryptString(pssw))) {
                pw.print("\nWrong password! please try again!\nusername :: ");
                continue;
            }
            this.username = un;
            this.password = pssw;
            break;
        }
    }

    public String gUsername() {
        return username;
    }

    public String gPassword() {
        return password;
    }
}
