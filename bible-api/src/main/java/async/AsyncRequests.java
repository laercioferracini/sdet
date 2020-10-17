package async;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = sdet-multi-project
 * @since <pre>17/10/2020</pre>
 */
public class AsyncRequests {

    private static final Logger logger = Logger.getLogger(AsyncRequests.class.getName());
    HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            //.connectTimeout(Duration.ofSeconds(30))
            .build();

    public static void starting(Object o) {
        CompletableFuture<Void> start = new CompletableFuture<>();
        CompletableFuture<List<String>> uris = start.thenCompose(nil -> getLinks());
        uris.thenRun(() -> logger.info("Uris readed"));

        ExecutorService executor = Executors.newSingleThreadExecutor();

        start.completeAsync(() -> null, executor);

        executor.shutdown();
    }

    private static CompletableFuture<List<String>> getLinks() {
        Supplier<List<String>> uris = () -> Arrays.asList("https://petstore.swagger.io/v2/pet/findByStatus?status=sold", "https://bible.com/pt/bible/1275/PRO.1", "https://bible.com/pt/bible/1275/PRO.2", "https://bible.com/pt/bible/1275/PRO.3");
        return CompletableFuture.supplyAsync(uris);
    }

    public static void main(String[] args) throws InterruptedException {
        var client = new AsyncRequests().httpClient;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://petstore.swagger.io/v2/pet/findByStatus?status=available"))
                .build();

        client.sendAsync(request,
                HttpResponse.BodyHandlers.ofFile(Path.of("bible-api/files/contador.json")))
                .thenApply(HttpResponse::body)
                .thenApply(Path::toFile)
                .thenAcceptAsync(file -> logger.info(file + " has been created"));

        client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenApply(String::toFile)
                .thenAcceptAsync(file -> logger.info(file + " has been created"));

        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://petstore.swagger.io/v2/pet/findByStatus?status=sold"))
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(future.join().body());

        future.thenAccept(response -> {
            String body = response.body();
            System.out.println("BODY: " + body.length() + "[ " + Thread.currentThread().getName() + " ]");

        }).thenRun(() -> System.out.println("Done!"))
        .join();

        Thread.sleep(2000);
        starting(null);
    }
}
