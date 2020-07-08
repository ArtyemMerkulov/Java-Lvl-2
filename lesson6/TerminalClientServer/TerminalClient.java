package TerminalClientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TerminalClient {

    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    private static final String IP_ADDR= "localhost";
    private static final int PORT = 8189;

    volatile boolean endSession = false;

    public TerminalClient() {
        try {
            socket = new Socket(IP_ADDR, PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        if (endSession) break;

                        String msg = in.readUTF();
                        if (!msg.equals("")) System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(() -> {
                Scanner sc = new Scanner(System.in);

                while (true) {
                    if (sc.hasNextLine()) {
                        String msg = sc.nextLine();

                        if (msg.equalsIgnoreCase("/end")) {
                            endSession = true;
                            break;
                        } else if (!msg.equals("")) {
                            sendMsg(msg);
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getEndSession() {
        return endSession;
    }
}
