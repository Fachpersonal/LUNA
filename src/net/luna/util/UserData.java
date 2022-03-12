package net.luna.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author @falscherIdiot
 * @version 1
 * @since 07-03-2022
 */
public class UserData {
    private String username;
    private String password;
    private String firstName;
    private String surName;
    private LocalDate birthday;
    private int age;
    private boolean loggedin = false;

    public static UserData login(String username, String password) throws IOException {
        File[] userFiles = new File("./res/userData/").listFiles();
        for (File file : userFiles) {
            if (!file.getPath().contains(".lud")) {
                continue;
            }
            if (!R.encryptString(username).equals(file.getPath().split(".")[0])) {
                continue;
            }
            R.fileHelper.readFile(
                    "./res/userData/" + R.encryptString(username) + ".lud");
            ArrayList<String> content = R.fileHelper.gContent();
            R.fileHelper.unloadSafedFile();
            int size = content.size();
            String un = null, pw = null, firstName = null, surName = null;
            LocalDate birthday = null;
            int age = 0;
            un = content.get(0);
            pw = content.get(1);
            if (!R.encryptString(password).equals(pw)) {
                continue;
            }
            firstName = size >= 3 ? content.get(2) : null;
            surName = size >= 4 ? content.get(3) : null;
            birthday = size >= 5 ? LocalDate.parse(content.get(4)) : null;
            age = size >= 6 ? Integer.parseInt(content.get(5)) : null;
            return new UserData(un, pw, firstName, surName, birthday, age, false);
        }
        return null;
    }

    /**
     * Constructor
     * 
     * @param username
     * @param password
     * @param firstName
     * @param surName
     * @param birhtday
     * @param age
     * @throws IOException
     */
    public UserData(String username, String password, String firstName, String surName, LocalDate birhtday, int age,
            boolean createFile)
            throws IOException {
        this.username = username;
        this.password = (createFile ? R.encryptString(password) : password);
        this.firstName = firstName;
        this.surName = surName;
        this.birthday = birhtday;
        this.age = age;
        if (createFile) {
            ArrayList<String> content = new ArrayList<>();
            content.add(username);
            content.add((createFile ? R.encryptString(password) : password));
            content.add(firstName);
            content.add(surName);
            content.add((birhtday == null ? "null" : birthday.toString()));
            content.add(age + "");
            R.fileHelper.writeFile("./res/userData/" + R.encryptString(username) + ".lud", content, false);
        }
    }

    public String gUsername() {
        return username;
    }

    public String gPassword() {
        return password;
    }

    public String gFirstName() {
        return firstName;
    }

    public String gSurName() {
        return surName;
    }

    public LocalDate gBirthday() {
        return birthday;
    }

    public int gAge() {
        return age;
    }

    public boolean gLoggedIn() {
        return loggedin;
    }
}
