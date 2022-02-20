package net.luna.util;

import java.util.ArrayList;

import net.luna.util.errors.LSNException;

public class ResourceHandler {

    // *R.module.Logger == returns instance of Logger
    // ! R.module.$moduleName == returns instance of singelton Module

    // * R.string.welcome == returns welcome message string
    // ! R.string.keyName == return keyName message as String

    public ResourceHandler() throws LSNException {
        LSNObject[] lsn = LSN.readData("strings.lsn").getLSN();

        for (int i = 0; i < lsn.length; i++) {
            if (lsn[i].getType().equals(LSNType.CONF)) {
            } else if (lsn[i].getType().equals(LSNType.RES)) {

            } else {
                throw new LSNException("Non Supported LSNType! @ ResourceHandler");
            }

        }
    }

    public static class R {
        public class module {
            private static ArrayList<ModuleStructure> mods;

            public static ModuleStructure get(int index) {
                return mods.get(index);
            }

            public static ModuleStructure get(String name) {
                for (int i = 0; i < mods.size(); i++) {
                    if (mods.get(i).getClass().getSimpleName().toLowerCase().equals(name.toLowerCase())) {
                        return mods.get(i);
                    }
                }
                return null;
            }

            public static ModuleStructure getByID(String ID) {
                for (int i = 0; i < mods.size(); i++) {
                    if (mods.get(i)..equals(name.toLowerCase())) {
                        return mods.get(i);
                    }
                }
                return null;
            }
        }

        public class string {
            private static ArrayList<LSNObject> strings;

            public static LSNObject get(int index) {
                return strings.get(index);
            }

            public static String get(String keyName) {
                for (int i = 0; i < strings.size(); i++) {
                    if (strings.get(i).getKey().toLowerCase().equals(keyName.toLowerCase())) {
                        if (strings.get(i).getType().equals(LSNType.RES)) {
                            return strings.get(i).getValue();
                        }
                    }
                }
                return null;
            }
        }

        public static Object getItemByName(String name) {
            ModuleStructure m = module.get(name);
            String s = string.get(name);
            return (m != null) ? m : ((s == null) ? s : null);
        }

        public static Object getItemByID(String ID) {
            ModuleStructure m = module.getByID(ID);
        }
    }
}
