package net.luna.util;

import java.time.LocalDate;

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

    public UserData(String username, String password) {
        createUser(username, password, null, null, null, -1);
    }

    public void createUser(String username, String password, String firstName, String surName, LocalDate birthday,
            int age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
        this.birthday = birthday;
        this.age = age;
    }

    public String gUsername() {
        return username;
    }

    // ! TODO rework (use SHA512)
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
}
