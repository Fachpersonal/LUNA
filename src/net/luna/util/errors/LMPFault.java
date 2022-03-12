package net.luna.util.errors;

import net.luna.util.R;

public class LMPFault extends Exception {

    private final int ID;

    public LMPFault(int ID) {
        super();
        this.ID = ID;
    }

    public String getFault() {
        switch (ID) {
            case 1:
                return R.language.get("LMPFault1");
            default:
                return null;
        }
    }
}
