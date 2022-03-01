package net.luna.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

    public static LSN readSpecificData(File f, LSNType[] specificTypes) throws LSNException, IOException {

        LSN lsn = new LSN();
        BufferedReader br = new BufferedReader(new FileReader("strings.lsn"));
        String line;
        boolean contains = false;
        while ((line = br.readLine()) != null) {
            contains = false;
            char[] cline = line.toCharArray();
            int pointer = 1;
            String key = "";
            String value = "";
            LSNType type = null;
            do { // *Gets Key
                key += cline[pointer];
                pointer++;
            } while (cline[pointer] != '>');
            pointer++;
            do { // *Gets value
                value += cline[pointer];
                pointer++;
            } while (cline[pointer] != '<' && cline[pointer + 1] != '/');
            pointer += 2;
            { // ? Gets Type
                String STR_Type = "";
                LSNType[] types = (specificTypes == null ? LSNType.values() : specificTypes);
                do {
                    STR_Type += cline[pointer];
                    pointer++;
                } while (cline[pointer] != '>');
                for (int j = 0; j < types.length; j++) {
                    if (types[j].toString().equals(STR_Type)) {
                        type = types[j];
                        contains = true;
                    }
                }
            }
            if (contains) {
                lsn.add(new LSNObject(key, value, type));
            }
        }
        br.close();
        return lsn;
    }

    public static LSN readSpecificData(String path, LSNType[] specificTypes) throws LSNException, IOException {
        return readSpecificData(new File(path), specificTypes);
    }

    public static LSN readData(String path) throws LSNException, IOException {
        return readSpecificData(new File(path), null);
    }

    public static LSN readData(File f) throws LSNException, IOException {
        return readSpecificData(f, null);
    }

    public void writeData(File f) throws IOException {
        writeData(this, f);
    }

    public static void writeData(LSN lsn, File f) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(lsn.toString());
        bw.close();
    }

    public void add(LSNObject object) {
        obj.add(object);
    }

    public void remove(LSNObject object) {
        if (!obj.remove(object)) {
            Logger.getLogger().WARNING("Couldnt remove object!");
        }
    }

    public LSNObject get(int index) {
        return obj.get(index);
    }

    public ArrayList<LSNObject> getLSN() {
        return obj;
    }

    public String toString() {
        String tmp = "";
        for (int i = 0; i < obj.size(); i++) {
            tmp += obj.get(i).toString();
        }
        return tmp;
    }
}
