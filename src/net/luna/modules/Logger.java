package net.luna.modules;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalTime;

import net.luna.util.ModuleStructure;
import net.luna.util.R;

/**
 * Logger
 * 
 * @author @falscherIdiot
 * @version 3
 * @see ModuleStructure
 */
public class Logger extends ModuleStructure {

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
        R.logger = null;
    }

    /**
     * Default LOG
     * 
     * @param msg
     * @throws IOException
     */
    public void LOG(String msg) throws IOException {
        R.fileHelper.writeFile(R.config.get("logDir"), "<" + getTime() + ">  " + msg, true);
    }

    /**
     * INFO LOG
     * 
     * @param msg
     */
    public void INFO(String msg) throws IOException {
        R.fileHelper.writeFile(R.config.get("logDir"), "<" + getTime() + ">  [INFO] :: " + msg + "!", true);
    }

    /**
     * WARNING LOG
     * 
     * @param msg
     * @throws IOException
     */
    public void WARNING(String msg) throws IOException {
        R.fileHelper.writeFile(R.config.get("logDir"), "<" + getTime() + ">  [WARNING] :: " + msg + "!", true);
    }

    /**
     * ERROR DEFAULT LOG
     * 
     * @param msg
     * @throws IOException
     */
    public void ERROR(String msg) throws IOException {
        R.fileHelper.writeFile(R.config.get("logDir"), "<" + getTime() + ">  [ERROR] :: " + msg + "!", true);
    }

    /**
     * ERROR EXCEPTION LOG
     * 
     * @param e
     * @throws IOException
     */
    public void ERROR(Exception e) throws IOException {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        R.fileHelper.writeFile(R.config.get("logDir"), "<" + getTime() + ">  [ERROR] :: " + sw.toString(), true);
    }

    private String getTime() {
        return (LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
    }

}
