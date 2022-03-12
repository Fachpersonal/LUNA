package net.luna.util;

import net.luna.util.errors.LMPFault;

public class LMProtocol {

    public static String CMD_PREFIX;

    public static enum Type {
        COMMAND, MESSAGE, SHUTDOWN
    }

    private String msg;
    private Type type;

    public LMProtocol(String msg) throws LMPFault {
        if (CMD_PREFIX == null) {
            CMD_PREFIX = R.config.get("cmdPrefix");
        }
        if (msg.contains(CMD_PREFIX)) {
            char[] prf = CMD_PREFIX.toCharArray();
            for (int i = 0; i < prf.length; i++) {
                if (msg.charAt(i) != prf[i]) {
                    throw new LMPFault(1);
                }
            }
            type = Type.COMMAND;
            this.msg = msg.split(CMD_PREFIX)[1];
        }
        if (msg.equals("shutdown")) {
            type = Type.SHUTDOWN;
        }
        if (type == null) {
            type = Type.MESSAGE;
            this.msg = msg;
        }
    }

    public String gMSG() {
        return msg;
    }

    public Type gType() {
        return type;
    }
}
