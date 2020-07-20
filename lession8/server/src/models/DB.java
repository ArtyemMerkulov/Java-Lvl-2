package models;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static final String DBCredentialsFile = "../server/src/configs/DBConfig.conf";

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get(DBCredentialsFile))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = props.getProperty("url");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url);
    }

    public static void closeConnection(Connection con) throws SQLException {
        con.close();
    }
}
