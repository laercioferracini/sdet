package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        CustomerConsumer consumer = new CustomerConsumer();

        consumer.save.accept(consumer.getCustomerDetailsList());
    }
}
