/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.SettingsLoader;

/**
 *
 * @author TANJA-PC
 */
public class DatabaseConnection {

    private final Connection connection;
    private static DatabaseConnection instance;

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() throws SQLException {
        String enconding = SettingsLoader.getInstance().getProperty("enconding");
        String url = SettingsLoader.getInstance().getProperty("url") + enconding;
        String dbuser = SettingsLoader.getInstance().getProperty("user");
        String dbpassword = SettingsLoader.getInstance().getProperty("password");
        
        connection = DriverManager.getConnection(url, dbuser, dbpassword);
        connection.setAutoCommit(false);
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

}
