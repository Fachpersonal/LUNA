package net.luna.modules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import net.luna.util.ModuleStructure;

public class Logger extends ModuleStructure {

    private BufferedWriter bw;

    public static ArrayList<String> buf;

    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    private Logger() {
        buf = new ArrayList<String>();
        start();
    }

    @Override
    public void start() {
        INFO(Config.getConfig().getModuleStartMessage("Logger"));
        try {
            File save_File = new File(Config.getConfig().getLogDir() + LocalDate.now() + ".log");

            bw = new BufferedWriter(new FileWriter(save_File, true));
            bw.newLine();
            bw.write("####################################################################################");
            bw.newLine();
            bw.write("#                                    SYSTEM START                                  #");
            bw.newLine();
            bw.write("####################################################################################");
            if (buf != null && buf.size() >= 1) {
                for (int i = 0; i < buf.size(); i++) {
                    bw.newLine();
                    bw.write("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                            + LocalTime.now().getSecond() + ">  [INFO] :: " + buf.get(i) + "!");
                    if (Core.loggerOutput) {
                        System.out.println("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                                + LocalTime.now().getSecond() + ">  [INFO] :: " + buf.get(i) + "!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        INFO(Config.getConfig().getModuleStartSuccessMessage("Logger"));
    }

    @Override
    public void stop() {
        WARNING(Config.getConfig().getModuleStopMessage("Logger"));
        buf = null;
        logger = null;
    }

    private void write2File(String msg) {
        try {
            File save_File = new File(Config.getConfig().getLogDir() + LocalDate.now() + ".log");

            bw = new BufferedWriter(new FileWriter(save_File, true));

            bw.newLine();
            bw.write(msg);
            bw.close();
            if (Core.loggerOutput) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LOG(String msg) {
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  " + msg);
    }

    public void INFO(String msg) {
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  [INFO] :: " + msg + "!");
    }

    public void WARNING(String msg) {
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  [WARNING] :: " + msg + "!");
    }

    public void ERROR(String msg) {
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  [ERROR] :: " + msg + "!");
    }

    public void ERROR(String msg, Exception e) {
        StringWriter sw = new StringWriter();
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  [ERROR] :: " + msg + "!");
        e.printStackTrace(new PrintWriter(sw));
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  [ERROR] :: " + sw.toString());
    }

    public void ERROR(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        write2File("<" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":"
                + LocalTime.now().getSecond() + ">  [ERROR] :: " + sw.toString());
    }

}
