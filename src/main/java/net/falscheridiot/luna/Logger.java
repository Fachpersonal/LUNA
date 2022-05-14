package net.falscheridiot.luna;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author @falscherIdiot
 * @version 1.
 * @see ModuleStructure
 */
public class Logger {

    /** Constructor */
    public Logger(String logDir) {
        R.logDir = logDir;
    }

    /** Default Constructor */
    public Logger() {
    }

    /**
     * @param msg
     * @throws IOException
     */
    public void LOG(String msg) {
        try {
            System.out.println("build-" + R.buildNr + " <" + getTime() + ">  " + msg);
            R.fileHelper.writeFile("./logs/" + getDate() + ".log",
                    "build-" + R.buildNr + " <" + getTime() + ">  " + msg, true);
        } catch (Exception e) {
            ERROR(e);
        }
    }

    /**
     * @param msg
     */
    public void INFO(String msg) {
        try {
            System.out.println("build-" + R.buildNr + " <" + getTime() + ">  [INFO] :: " + msg + "!");
            R.fileHelper.writeFile("./logs/" + getDate() + ".log",
                    "build-" + R.buildNr + " <" + getTime() + ">  [INFO] :: " + msg + "!", true);
        } catch (Exception e) {
            ERROR(e);
        }
    }

    /**
     * @param msg
     * @throws IOException
     */
    public void WARNING(String msg) {
        try {
            System.out.println("build-" + R.buildNr + " <" + getTime() + ">  [WARNING] :: " + msg + "!");
            R.fileHelper.writeFile("./logs/" + getDate() + ".log",
                    "build-" + R.buildNr + " <" + getTime() + ">  [WARNING] :: " + msg + "!", true);
        } catch (Exception e) {
            ERROR(e);
        }
    }

    /**
     * @param msg
     * @throws IOException
     */
    public void ERROR(String msg) {
        try {
            System.out.println("build-" + R.buildNr + " <" + getTime() + ">  [ERROR] :: " + msg + "!");
            R.fileHelper.writeFile("./logs/" + getDate() + ".log",
                    "build-" + R.buildNr + " <" + getTime() + ">  [ERROR] :: " + msg + "!", true);
        } catch (Exception e) {
            ERROR(e);
        }
    }

    /**
     * @param e
     * @throws IOException
     */
    public void ERROR(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            System.out.println("build-" + R.buildNr + " <" + getTime() + ">  [ERROR] :: " + sw.toString() + "!");
            R.fileHelper.writeFile("./logs/" + getDate() + ".log",
                    "build-" + R.buildNr + " <" + getTime() + ">  [ERROR] :: " + sw.toString(), true);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private String getDate() {
        return (LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth() + "-" + LocalDate.now().getYear());
    }

    private String getTime() {
        return (LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
    }

}
