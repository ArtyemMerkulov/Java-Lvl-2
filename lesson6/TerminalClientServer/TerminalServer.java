package TerminalClientServer;

import server.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.Vector;

public class TerminalServer {
    ServerSocket server;
    volatile Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public boolean clientConnectionIsClosed() {
        return socket.isClosed();
    }

    public TerminalServer() {
        server = null;
        socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            System.out.println("Клиент " + socket + " подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                Scanner sc = new Scanner(System.in);

                while (true) {
                    if (sc.hasNextLine()) {
                        String msg = sc.nextLine();

                        if (msg.equalsIgnoreCase("/end")) break;
                        else if (!msg.equals("")) {
                            sendMsg("Server: " + msg);
                            System.out.println("Server: " + msg);
                        }
                    }
                }
            }).start();

            while (true) {
                try {
                    String msg = in.readUTF();
                    if (msg.equals("/end")) break;
                    if (!msg.equals("")) {
                        System.out.println("Client: " + msg); // как клиент
                        sendMsg("Client: " + msg); // как сервер
                    }
                } catch (SocketException e) {
                    break;
                }
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
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
