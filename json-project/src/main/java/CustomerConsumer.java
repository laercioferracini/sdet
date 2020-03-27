package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>26/03/2020</pre>
 */
public class CustomerConsumer {
    public List<CustomerDetails> getCustomerDetailsList() throws SQLException, ClassNotFoundException {
        Connection connection = Connector.getConnection();
        String query = "select * from CustomerInfo";// where purchasedDate=\"2020-03-25\"";
        Statement pst = connection.createStatement();
        ResultSet resultSet = pst.executeQuery(query);
        CustomerDetails customerDetails;
        List<CustomerDetails> customerDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            customerDetails = new CustomerDetails(
                    resultSet.getString("CourseName"),
                    resultSet.getString("PurchasedDate"),
                    resultSet.getString("Amount"),
                    resultSet.getString("Location"));
            customerDetailsList.add(customerDetails);


        }
        System.out.println(customerDetailsList.size());
        connection.close();
        return customerDetailsList;
    }

    Consumer<List<CustomerDetails>> save = (customerDetailsList) -> {
        AtomicInteger i = new AtomicInteger();
        customerDetailsList.forEach((e) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File("customerDetails" + i.getAndIncrement() + ".json"), e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    };

    private static void saveJsonFiles(List<CustomerDetails> customerDetailsList) {
        AtomicInteger i = new AtomicInteger();
        customerDetailsList.forEach((e) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File("customerDetails" + i.getAndIncrement() + ".json"), e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }
}
