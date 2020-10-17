package br.com.ferracini.consumers;

import br.com.ferracini.connectors.Connector;
import br.com.ferracini.pojo.CustomerDetails;
import br.com.ferracini.pojo.CustomerDetailsList;
import br.com.ferracini.utils.Globals;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>26/03/2020</pre>
 */
public class CustomerConsumer {

    public Consumer<JSONObject> saveJson = jsonObject -> {
        try {
            new ObjectMapper().writeValue(new File(Globals.JSON_LIST), jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    public String getFileJson(){
        return new File(Globals.JSON_LIST).toString();
    }
    public Function<File, CustomerDetailsList> readJsonToObject = (file) -> {
        ObjectMapper mapper = new ObjectMapper();
        CustomerDetailsList customerDetailsList;
        try {
            customerDetailsList = mapper.readValue(file, CustomerDetailsList.class);
        } catch (IOException e) {
            customerDetailsList = new CustomerDetailsList();
            e.printStackTrace();
        }
        return customerDetailsList;
    };

    public Consumer<List<CustomerDetails>> save = (customerDetailsList) -> {
        AtomicInteger i = new AtomicInteger();
        customerDetailsList.forEach((e) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File(Globals.JSON_PATH + i.getAndIncrement() + ".json"), e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    };
    public Function<List<String>, CompletableFuture<List<CustomerDetails>>> getCustomerDetails = courseNames -> {
        Supplier<List<CustomerDetails>> supplier = () -> Try.of(() -> courseNames.stream().map(this::getCustomerDetails).collect(Collectors.toList())
        ).getOrElse(new ArrayList<>());
        //connection.close();
        return CompletableFuture.supplyAsync(supplier);
    };

    public Function<List<String>, CompletableFuture<List<CustomerDetails>>> getCustomerDetails2 = courseNames -> {

        Supplier<List<CustomerDetails>> supplier = () -> Try.of(() -> {
            Thread.sleep(1000);
            return courseNames.stream().map(this::getCustomerDetails).collect(Collectors.toList());
        }).getOrElse(new ArrayList<>());
        //connection.close();
        return CompletableFuture.supplyAsync(supplier);
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

    private CustomerDetails getCustomerDetails(String c) {
        CustomerDetails customerDetails = new CustomerDetails();
        try {
            Connection connection = Connector.getConnection();

            String query = "select * from CustomerInfo where CourseName LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, c);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customerDetails = new CustomerDetails(
                        resultSet.getString("CourseName"),
                        resultSet.getString("PurchasedDate"),
                        resultSet.getString("Amount"),
                        resultSet.getString("Location"));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return customerDetails;
    }

    public List<CustomerDetails> getCustomerDetailsList() throws Exception {
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
}
