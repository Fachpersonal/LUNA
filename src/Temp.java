import java.io.IOException;

public class Temp {
    public static void main(String[] args) throws IOException {
        char[] pssw = null;
        pssw = System.console().readPassword("password :: ");
        String ps = "";
        for (char c : pssw) {
            ps += c;
        }
        System.out.println(ps);
    }
}
