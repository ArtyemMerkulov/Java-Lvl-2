package TerminalClientServer;

public class TerminalServerStart {
    public static void main(String[] args) {
        TerminalServer ts = new TerminalServer();
        while (true) {
            if (ts.clientConnectionIsClosed()) System.exit(1);
        }
    }
}
