package net.luna.util;

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

    public static UserData login() throws IOException {
        System.out.println("-:: Login ::-");

        System.out.println("[1] - Login");
        System.out.println("[2] - Register");
        try {
            if (Integer.parseInt(System.console().readLine()) == 2) {
                return register();
            }
        } catch (java.lang.NumberFormatException e) {
            R.logger.ERROR(e);
        }
        int tries = 0;
        while (true) {
            if (tries >= 3) {
                System.out.println(R.language.get("loginRegister"));
            }
            if (tries == 5) {
                R.logger.WARNING(R.language.get("loginTries"));
                tries = 0;
            }
            System.out.print("Enter username:: ");
            String username = System.console().readLine();
            if (username.length() < 4) {
                System.out.println(R.language.get("loginUsernameErrorLength"));
                continue;
            }
            if (username.equalsIgnoreCase("register")) {
                return register();
            }
            String password = null;
            {
                String tmp = "";
                {
                    for (char c : System.console().readPassword("Enter password:: ")) {
                        tmp += c;
                    }
                    if (tmp.length() < 4) {
                        System.out.println(R.language.get("loginPasswordErrorLength"));
                        continue;
                    }
                }
                password = R.encryptString(tmp);
            }
            if (!R.user.containsKey(username)) {
                System.out.println(R.language.get("loginError"));
                tries++;
                continue;
            }
            UserData x = R.user.get(username);
            if (!x.gPassword().equals(password)) {
                System.out.println(R.language.get("loginError"));
                tries++;
                continue;
            }
            return x;
        }

    }

    public static UserData register() throws IOException {
        System.out.println("-:: Register ::-");
        String username = null, password = "", firstName = null, surName = null;
        LocalDate ld = null;
        int age = -1;
        System.out.print("username :: ");
        username = System.console().readLine();
        {
            for (char c : System.console().readPassword("password :: ")) {
                password += c;
            }
        }
        System.out.print("firstName :: ");
        firstName = System.console().readLine();
        System.out.print("surName :: ");
        surName = System.console().readLine();
        System.out.print("birthday :: ");
        try {
            ld = LocalDate.parse(System.console().readLine());
        } catch (java.time.format.DateTimeParseException e) {
            ld = null;
            R.logger.ERROR(e);
        }

        System.out.print("age :: ");
        try {
            age = Integer.parseInt(System.console().readLine());
        } catch (java.lang.NumberFormatException e) {
            age = -1;
            R.logger.ERROR(e);
        }
        R.logger.INFO(R.language.get("userRegistered") + "[" + username + "]");
        return new UserData(username, password, firstName, surName, ld, age, true);
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
