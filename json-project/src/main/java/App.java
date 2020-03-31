
import com.fasterxml.jackson.databind.ObjectMapper;
import consumers.CustomerConsumer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojo.CustomerDetails;
import pojo.CustomerDetailsList;
import utils.Globals;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>27/03/2020</pre>
 */
public class App {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        CustomerConsumer consumer = new CustomerConsumer();
        List<CustomerDetails> customerDetailsList = consumer.getCustomerDetailsList();

        JSONArray jsonArray = new JSONArray();

        jsonArray.addAll(customerDetailsList);
        jsonArray.forEach(System.out::println);

        JSONObject object = new JSONObject();
        object.put("data", jsonArray);
        consumer.saveJson.accept(object);

        CustomerDetailsList detailsList = consumer.readJsonToObject.apply(new File(Globals.JSON_LIST));
        detailsList.getData().forEach(System.out::println);
    }
}
