package net.luna.util;

import java.io.File;

//! Setup program it runs befor 
public class ResourceHandler {

    // *R.module.Logger == returns instance of Logger
    // ! R.module.$moduleName == returns instance of singelton Module

    // * R.string.welcome == returns welcome message string
    // ! R.string.keyName == return keyName message as String

    public static void run(File f) {

    }

    public static void run(File[] files) {
        for (int i = 0; i < files.length; i++) {
            run(files[i]);
        }
    }
}
