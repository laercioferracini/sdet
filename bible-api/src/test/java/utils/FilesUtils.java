package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = bible-api
 * @since <pre>05/04/2020</pre>
 */
public class FilesUtils {

    private final static Logger LOGGER = Logger.getLogger(FilesUtils.class.getName());

    public static void createFile(Path pathFile) {
        if (!Files.exists(pathFile)) {
            try {
                Files.createFile(pathFile);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo: " + pathFile.toString());
            }
        }
    }

    public static void createDir(Path dir) {
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar diretório: " + dir.toString());
            }
        }
    }

    public static void writeFile(Path pathFile, String text) {
        try {
            if (!Files.exists(pathFile)) {
                Files.createFile(pathFile);
            }
            Files.writeString(pathFile, text.concat("\n"), StandardOpenOption.APPEND);

        } catch (IOException e) {
            LOGGER.severe("An error occurred at write in file." + e);
        }
    }

    public static List<String> readFile(String file) {
        try {
            return Files.readAllLines(Path.of(file));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void copyFiles(String destDir) {
        try {
            Files.copy(Paths.get("files/"), Paths.get(destDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void screenshot(RemoteWebDriver driver, String desc) {
        byte[] file = driver.getScreenshotAs(OutputType.BYTES);
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss");
            String dataFormatada = dateTime.format(formatter);
            LOGGER.info("Writing file");
            Path path = Paths.get("screenshots/", desc + "_" + dataFormatada + ".png");
            Files.write(path, file);
        } catch (IOException e) {
            LOGGER.severe("An error occurred at take an screenshot." + e);
        }

    }

    public static boolean verifyBooks() {

        boolean resultado = false;
        String fileName = "bible-api/files/contador.txt";
        Path path = Paths.get(fileName);
        System.out.println(path.toAbsolutePath().getParent().toString());
        List<String> books = FilesUtils.readFile(fileName);
        int cont = 1;
        for (String book : books) {
            System.out.printf("%s%s%n",cont,book.split("\\.")[1]);
            cont++;
        }

        //books.forEach(System.out::println);

        return books.isEmpty();
    }

    public static void main(String[] args) {
        boolean b = FilesUtils.verifyBooks();
        System.out.println(b);
    }
}
