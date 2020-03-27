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
            String query = "select * from CustomerInfo where purchasedDate=\"2020-03-25\" and Location ='Asia' and Amount = 21";
            Statement pst = connection.createStatement();
            ResultSet resultSet = pst.executeQuery(query);
            CustomerDetails customerDetails = null;

            while (resultSet.next()) {
                customerDetails = new CustomerDetails(
                        resultSet.getString("CourseName"),
                        resultSet.getString("PurchasedDate"),
                        resultSet.getString("Amount"),
                        resultSet.getString("Location"));



            }

            assert customerDetails != null;
            System.out.println(customerDetails.toString());
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
