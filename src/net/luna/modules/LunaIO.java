package net.luna.modules;

import java.io.IOException;
import java.util.Scanner;

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

    public LunaIO() {
        start();
        stop();
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        R.logger.INFO(R.language.get("lunaIOstart"));
        Scanner in = new Scanner(System.in);
        String cmd;
        login(in);
        while ((cmd = in.nextLine()) != null) {
            try {
                LMProtocol lmp = new LMProtocol(cmd);
                if (lmp.gType().equals(LMProtocol.Type.SHUTDOWN)) {
                    System.out.println("SHUTTING SYSTEM DOWN (NOT WORKING LMAO XD)    " + lmp.gMSG());
                } else if (lmp.gType().equals(LMProtocol.Type.COMMAND)) {
                    System.out.println("COMMANDS ARE NOT IMPLEMENTED! [" + lmp.gMSG() + "]");
                } else if (lmp.gType().equals(LMProtocol.Type.MESSAGE)) {
                    System.out.println("ECHO :::  " + lmp.gMSG());
                }
            } catch (LMPFault e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                e.getFault();
            }
        }
        in.close();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    /**
     * login
     * 
     * @return false if failed
     * @throws IOException
     */
    private boolean login(Scanner in) {
        int tries = 0;
        while (true) {
            if (tries <= 3) {
                System.out.print("Please Enter username :: ");
                String username = in.nextLine();
                System.out.print("Please Enter password :: ");
                String password = in.nextLine();

                try {
                    UserData ud = UserData.login(username, password);
                    if (ud.gLoggedIn()) {
                        return true;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                tries++;
            } else {
                System.out.println("BRO U FUCKED UP! THREE TIMES HAHA FCK OFF!");
                System.exit(3);
            }
        }
    }
}
