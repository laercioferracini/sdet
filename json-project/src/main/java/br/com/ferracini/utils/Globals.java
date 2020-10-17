package br.com.ferracini.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>31/03/2020</pre>
 */
public class Globals {
    public static final String BASE_PATH = System.getProperty("user.dir");
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String JSON_PATH =
            BASE_PATH.concat(FILE_SEPARATOR).concat("jsonFiles").concat(FILE_SEPARATOR).concat("customerDetailsList");

    static Function<String, String> file = file -> {
        var path = Paths.get(BASE_PATH, FILE_SEPARATOR, "json-project", file);
        return Files.exists(path) ? path.toString() : path.toString().concat(" not found!");
    };

    public static final String JSON_LIST = file.apply("jsonFiles/customerDetailsList.json");
}
