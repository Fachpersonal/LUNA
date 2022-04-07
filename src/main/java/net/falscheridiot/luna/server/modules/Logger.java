package net.falscheridiot.luna.server.modules;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import net.falscheridiot.luna.server.util.ModuleStructure;
import net.falscheridiot.luna.server.util.R;

/**
 * Logger
 * 
 * @author @falscherIdiot
 * @version 1.4
 * @see ModuleStructure
 */
public class Logger implements ModuleStructure {

    /** Constructor */
    public Logger() {
        start();
    }

    /**
     * Start function of Logger
     */
    @Override
    public void start() {
        R.logger = this;
    }

    /** Stop function of Logger */
    @Override
    public void stop() {
        WARNING("Logger module shutting down");
        R.logger = null;
    }

    /**
     * Default LOG
     * 
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
     * INFO LOG
     * 
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
     * WARNING LOG
     * 
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
     * ERROR DEFAULT LOG
     * 
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
     * ERROR EXCEPTION LOG
     * 
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
