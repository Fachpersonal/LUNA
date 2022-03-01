package net.luna.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import net.luna.modules.*;
import net.luna.util.errors.LSNException;

public class R {

    public R() throws LSNException, IOException {
        ArrayList<LSNObject> lsno = LSN.readSpecificData("./config.lsn", new LSNType[] { LSNType.STR }).getLSN();
        for (int i = 0; i < lsno.size(); i++) {

        }
        lsn = LSN.readSpecificData("./strings.lsn", new LSNType[] { LSNType.CONF });
    }

    public class module {
        public static Logger logger = null;
        public static Core core = null;
    }

    public class config {
        public static String displayName = null;
    }

    public class string {
        public static ArrayList<String> buf = new ArrayList<String>();
        public static String welcomMessage = null;
    }
}