package net.luna.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.luna.modules.Logger;
import net.luna.util.errors.LSNException;

public class LSN {

    private ArrayList<LSNObject> obj;

    public LSN() {
        obj = new ArrayList<>();
    }

    public static LSN readData(File f) throws LSNException {
        LSN lsn = new LSN();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(f));
            int lineCount = 1;
            String line;
            while ((line = br.readLine()) != null) {
                char[] cline = line.toCharArray();

                String key = "";
                String value = "";
                LSNType type = null;

                if (cline[0] != '<') {
                    Logger.getLogger().ERROR(
                            new LSNException("Syntax Error at " + f.getPath() + " (" + lineCount + ":" + 1 + ")"));
                } else if (cline[cline.length - 1] != '>') {
                    Logger.getLogger().ERROR(new LSNException(
                            "Syntax Error at " + f.getPath() + " (" + lineCount + ":" + cline.length + ")"));
                }

                for (int i = 1; i < cline.length - 1; i++) {

                    // ? Gets Key
                    do {
                        key += cline[i];
                    } while (cline[i] != '>');
                    // ? Gets Value
                    while (true) {
                        if (cline[i] != '<' && cline[i + 1] != '/') {
                            value += cline[i];
                        } else {
                            i += 2;
                            break;
                        }

                        if (i == cline.length) {
                            br.close();
                            throw new LSNException("Please use following format <$name>$value</$TYPE>");
                        }
                    }
                    // ? Gets Type
                    if (i + 5 == cline.length) {
                        type = LSNType.CONF;
                    } else if (i + 4 == cline.length) {
                        type = LSNType.RES;
                    } else {
                        br.close();
                        throw new LSNException("Invalid type {RES, CONF}");
                    }
                }

                LSNObject lsno = new LSNObject(key, value, type);
                lsn.add(lsno);
                lineCount++;

                br.close();
            }
            br.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger().ERROR("Given file could not be found!", e);
            e.printStackTrace();
        } catch (IOException e) {
            Logger.getLogger().ERROR("Could not read given file", e);
            e.printStackTrace();
        }
        return lsn;
    }

    public void writeData(File f) {
        writeData(this, f);
    }

    public static void writeData(LSN lsn, File f) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(lsn.toString());
            bw.close();
        } catch (IOException e) {
            Logger.getLogger().ERROR("Could not write into given file", e);
            e.printStackTrace();
        }
    }

    public void add(LSNObject object) {
        obj.add(object);
    }

    public void remove(LSNObject object) {
        if (!obj.remove(object)) {
            Logger.getLogger().WARNING("Couldnt remove object!");
        }
    }

    public String toString() {
        String tmp = "";
        for (int i = 0; i < obj.size(); i++) {
            tmp += obj.get(i).toString();
        }
        return tmp;
    }
}
