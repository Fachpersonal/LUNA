package net.falscheridiot.luna;

import java.io.File;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void checkSystemFiles() {
        if (new File("./res/").mkdir()) {
            System.out.println("/res Directory created!");
        } else {
            System.out.println("/res Directory exists!");
        }

        if (new File("./logs/").mkdir()) {
            System.out.println("/logs Directory created!");
        } else {
            System.out.println("/logs Directory exists!");
        }
    }

    @Test
    public void tempTest() {
        System.out.println("Peter, ".substring(0, "Peter, ".length() - 2));
    }
}
