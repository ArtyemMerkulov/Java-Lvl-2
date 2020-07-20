import models.DB;
import models.Group;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private static final int PORT = 12345;

    private ConcurrentHashMap<String, ClientHandler> clients;

    private Connection conn;

    public ChatServer() {
        ServerSocket server = null;
        Socket socket = null;
        conn = null;
        try {
            // Да здравствует однопоточная БД
            conn = DB.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            clients = new ConcurrentHashMap<>();

            server = new ServerSocket(PORT);
            System.out.println("Server running...");

            while (true) {
                socket = server.accept();
                System.out.println("Client " + socket + " connected...");

                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                DB.closeConnection(conn);
            } catch (SQLException e) {
                e.getCause();
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

    public Connection getConnection() { return conn; }

    public void subscribe(String login, ClientHandler client) {
        clients.put(login, client);
    }

    public void unsubscribe(String login) {
        clients.remove(login);
    }

    public boolean isOnline(String login) {
        return clients.containsKey(login);
    }

    public boolean isUserAlreadyAuthorized(String login) {
        return clients.containsKey(login);
    }

    public void sendMsgToUser(String srcLogin, String dstLogin) {
        if (isOnline(dstLogin)) clients.get(dstLogin).sendMsg("/sendtouserfrom:" + srcLogin);
    }

    public void sendMsgToGroup(String srcLogin, String dstLogin) {
        try {
            Connection conn = clients.get(srcLogin).getUser().getDBConnection();
            for (String userDstLogin : Group.getUsersFromGroupByLogin(conn, dstLogin))
                if (isOnline(userDstLogin))
                    clients.get(userDstLogin).sendMsg("/sendtogroup:" + dstLogin + ":" + srcLogin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
