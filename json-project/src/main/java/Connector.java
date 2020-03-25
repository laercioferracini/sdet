package main.java;

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

    public static void main(String[] args) throws ClassNotFoundException {

        try {
            Connection connection = getConnection();
            String query = "select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia'";
            Statement pst = connection.createStatement();
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                System.out.printf("Result: %s %s %s %s %n",
                        resultSet.getString("CourseName"), resultSet.getString("PurchasedDate"), resultSet.getString("Amount"), resultSet.getString("Location"));

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
