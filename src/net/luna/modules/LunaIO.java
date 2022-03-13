package net.luna.modules;

import java.io.IOException;

import net.luna.util.LMProtocol;
import net.luna.util.ModuleStructure;
import net.luna.util.R;
import net.luna.util.UserData;
import net.luna.util.errors.LMPFault;

/**
 * Luna Input Output
 * 
 * @author @falscherIdiot
 * @since 09-03-2022
 * @version 3
 * @see ModuleStructure
 */
public class LunaIO implements ModuleStructure {

    private UserData user;

    public LunaIO() {
        start();
        stop();
    }

    @Override
    public void start() {
        R.logger.INFO("LunaIO " + R.language.get("moduleStart"));
        String cmd;
        try {
            user = UserData.login();
            System.out.println(R.language.get("loggedIn") + "[" + user.gUsername() + "]!");
            while ((cmd = System.console().readLine()) != null) {
                try {
                    LMProtocol lmp = new LMProtocol(cmd);
                    String prefix = "<" + user.gUsername() + "> ";
                    if (lmp.gType().equals(LMProtocol.Type.SHUTDOWN)) {
                        R.logger.WARNING(prefix + R.language.get("systemShutdown"));
                        stop();
                    } else if (lmp.gType().equals(LMProtocol.Type.COMMAND)) {
                        System.out.println(prefix + R.language.get("missingCommand") + " [" + lmp.gMSG() + "]");
                        R.logger.LOG(prefix + lmp.gMSG());
                    } else if (lmp.gType().equals(LMProtocol.Type.MESSAGE)) {
                        R.logger.LOG(prefix + "ECHO :::  " + lmp.gMSG());
                    }
                } catch (LMPFault e) {
                    R.logger.ERROR(e);
                    R.logger.ERROR(e.getFault());
                }
            }
        } catch (IOException e1) {
            R.logger.ERROR(e1);
        }
    }

    @Override
    public void stop() {
        R.logger.WARNING("LunaIO " + R.language.get("moduleStop"));
        R.core.stop();
    }

}
