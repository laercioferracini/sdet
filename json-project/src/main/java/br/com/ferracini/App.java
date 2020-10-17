package br.com.ferracini;

import br.com.ferracini.consumers.CustomerConsumer;
import br.com.ferracini.pojo.CustomerDetails;
import io.vavr.control.Try;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>27/03/2020</pre>
 */
public class App {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        CustomerConsumer consumer = new CustomerConsumer();
//        List<CustomerDetails> customerDetailsList = consumer.getCustomerDetailsList();
//

//
//        CustomerDetailsList detailsList = consumer.readJsonToObject.apply(new File(Globals.JSON_LIST));
//        detailsList.getData().forEach(System.out::println);
//
        var executorService = Executors.newSingleThreadExecutor();

        Supplier<List<String>> courseNames = () -> Arrays.asList("selenium", "Protractor", "Appium", "WebServices", "Jmeter");

        Consumer<List<CustomerDetails>> displayer = users -> users.forEach(System.out::println);

        Function<CompletableFuture<List<CustomerDetails>>, CompletableFuture<String>> jsons = customerDetailsList -> {
            Supplier<String> supplier = () ->
                    Try.of(() -> {
                        JSONArray jsonArray = new JSONArray();

                        jsonArray.addAll(customerDetailsList.get());
                        jsonArray.forEach(System.out::println);

                        JSONObject object = new JSONObject();
                        object.put("data", jsonArray);

                        consumer.saveJson.accept(object);

                        return consumer.getFileJson();
                    }).getOrElse("");
            return CompletableFuture.supplyAsync(supplier);
        };

        var list = CompletableFuture.supplyAsync(courseNames);

//        list.thenComposeAsync(consumer.getCustomerDetails, executorService)
//                .thenAcceptAsync(displayer, executorService);


        var customers1 = list.thenComposeAsync(consumer.getCustomerDetails);
        var customers2 = list.thenComposeAsync(consumer.getCustomerDetails2);

        customers1.thenRun(() -> System.out.println("Customers 111"));
        customers2.thenRun(() -> System.out.println("Customers 222"));

        customers1.acceptEither(customers2, displayer);
        Thread.sleep(6000);
        executorService.shutdown();

    }
}
