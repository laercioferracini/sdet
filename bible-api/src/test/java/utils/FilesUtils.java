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
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = bible-api
 * @since <pre>05/04/2020</pre>
 */
public class FilesUtils {

    private final static Logger LOGGER = Logger.getLogger(FilesUtils.class.getName());

    public static void createFile(Path pathFile) throws IOException {
        if (!Files.exists(pathFile)) Files.createFile(pathFile);
    }

    public static void createDir(Path dir) throws IOException {
        if (!Files.exists(dir)) Files.createDirectories(dir);
    }

    public static void writeFile(Path pathFile, String text) {
        try {
            Files.writeString(pathFile, text.concat("\n"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.severe("An error occurred at write in file." + e);
        }
    }

    public static void main(String[] args) {
        copyFiles("F:\\Dropbox\\Books\\Bible\\CJB\\");
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
}
