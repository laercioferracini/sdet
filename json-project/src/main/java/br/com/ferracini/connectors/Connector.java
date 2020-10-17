package br.com.ferracini.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>25/03/2020</pre>
 */
public class Connector {
    private static String user = "root";
    private static String pass = "";

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useTimezone=true&serverTimezone=UTC", user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("An error has got it, something screw it up on: " + e.getMessage());
        }
    }
}
