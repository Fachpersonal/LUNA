package net.luna.util;

import java.util.ArrayList;

import net.luna.util.errors.LSNException;

//! Setup program it runs befor 
public class ResourceHandler {

    // *R.module.Logger == returns instance of Logger
    // ! R.module.$moduleName == returns instance of singelton Module

    // * R.string.welcome == returns welcome message string
    // ! R.string.keyName == return keyName message as String

    public ResourceHandler() {
        try {
            LSN lsn = LSN.readData("strings.lsn");
        } catch (LSNException e) {
            // ! TODO: Log Error Message to BUFFER
            e.printStackTrace();
        }
    }

    public static class R {
        public class module {
            ArrayList<ModuleStructure> mods;

            public ModuleStructure get(int index) {
                return mods.get(index);
            }

            public ModuleStructure get(String name) {
                for (int i = 0; i < mods.size(); i++) {
                    if (mods.get(i).getClass().getSimpleName().toLowerCase().equals(name.toLowerCase())) {
                        return mods.get(i);
                    }
                }
                return null;
            }
        }

        public class string {
            ArrayList<LSNObject> strings;

            public LSNObject get(int index) {
                return strings.get(index);
            }

            public String get(String keyName) {
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
    }

}
