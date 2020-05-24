package api;

/**
 * @author lferracini
 * @project = sdet-multi-project
 * @since <pre>24/05/2020</pre>
 */
public class RequestBookAPI {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn%s&key=%s";
    private static final String API_KEY = System.getenv("api.key");

    public static String getBaseUrl(String isbn) {

        return String.format(BASE_URL,"%3D"+isbn,API_KEY);
    }


}
