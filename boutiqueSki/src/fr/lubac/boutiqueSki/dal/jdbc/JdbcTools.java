package fr.lubac.boutiqueSki.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.lubac.boutiqueSki.dal.Settings;

public class JdbcTools {

    private static final String DATABASE_NAME = "BOUTIQUESKI_DB";

    private static String urldb;
    private static String userdb;
    private static String passwordDb;
    private static Connection connection;

    // load jdbc driver in memory when class is first loaded in JVM memory :
    static {
        try {
            Class.forName(Settings.getProperty("driverdb"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        urldb = Settings.getProperty("urldb") + DATABASE_NAME + ";trustServerCertificate=true";
        userdb = Settings.getProperty("userdb");
        passwordDb = Settings.getProperty("db_password");
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(urldb, userdb, passwordDb);
            System.out.println("Connexion à la base de données");
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connexion à la base fermée");
            connection = null;
        }
    }

}
