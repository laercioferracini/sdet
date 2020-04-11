package br.com.ferracini.connectors;

import java.sql.*;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>25/03/2020</pre>
 */
public class Connector {
    private static String user = "root";
    private static String pass = "Querydefogo19!";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useTimezone=true&serverTimezone=UTC", user, pass);
    }
}
