package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private ConcurrentHashMap<Socket, ClientHandler> clients;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            clients = new ConcurrentHashMap<>();

            while (true) {
                socket = server.accept();
                System.out.println("Клиент " + socket + " подключился");
                clients.put(socket, new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    public void deleteClient(Socket socket) {
        System.out.println("Клиент " + socket + " отключился");
        clients.remove(socket);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients.values()) {
            o.sendMsg(msg);
        }
    }
}
