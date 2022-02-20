
import java.io.File;

import net.luna.util.LSN;
import net.luna.util.errors.LSNException;

public class Temp {

        public static void main(String[] args) {
                try {
                        LSN lsn = LSN.readData(new File("./strings.lsn"));
                        System.out.println(lsn.toString());
                } catch (LSNException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
}