package TerminalClientServer;

public class TerminalClientStart {
    public static void main(String[] args) {
        TerminalClient tc = new TerminalClient();
        while (true) {
            if (tc.getEndSession()) {
                System.exit(1);
            }
        }
    }
}
