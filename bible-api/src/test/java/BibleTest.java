import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FilesUtils;
import utils.Link;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static api.RequestBookAPI.getBaseUrl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DriverUtils.setDriverProperty;
import static utils.FilesUtils.screenshot;
import static utils.FilesUtils.writeFile;

/**
 * @author lferracini
 * @project = bible-api
 * @since <pre>05/04/2020</pre>
 */
public class BibleTest {
    private final static Logger LOGGER = Logger.getLogger(BibleTest.class.getName());
    static WebDriver driver;

    @AfterAll
    //@DisabledIfSystemProperty(named = "api",matches = "api")
    static void destroy() {
        LOGGER.info("After all");
        driver.quit();// kill the driver and the browser
    }

    private static void writeBookFile(String title, String chapter) {

        String tChapter = title.split(",")[0];
        String book = tChapter.substring(tChapter.indexOf("(") + 1, tChapter.lastIndexOf(")"));
        Path dir = Paths.get("files/" + book + "/");

        FilesUtils.createDir(dir);
        if (tChapter.contains("|"))
            tChapter = tChapter.split("\\|")[0];
        Path pathFile = Paths.get("files/" + book + "/", tChapter + ".txt");

        FilesUtils.createFile(pathFile);

        writeFile(pathFile, chapter);

    }

    private static void writeDownloadLinks(String link) {

        Path dir = Paths.get("files/");

        FilesUtils.createDir(dir);

        Path pathFile = Paths.get("files/links.txt");

        FilesUtils.createFile(pathFile);

        writeFile(pathFile, link);

    }

    @BeforeEach
        //@DisabledIfSystemProperty(named = "api",matches = "api")
    void initDriver() {
        driver = getDriver();
    }

    public WebDriver getDriver() {
        setDriverProperty();

        if (driver == null) driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        return driver;
    }

    @Test
    //@DisabledIfSystemProperty(named = "api",matches = "api")
    public void getChaptersFromBook() {

        driver.get("https://bible.com/pt/bible/1275/REV.22");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        screenshot((RemoteWebDriver) driver, "screenshot");
        assertThat(driver.getTitle(), CoreMatchers.containsString("CJB"));
        int cont = 1;
        boolean lastChapter = true;
        while (lastChapter) {
            if (!driver.getTitle().contains("(Rev) 22")) {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nav-right")));
                String chapter = ((RemoteWebDriver) driver).findElementByClassName("chapter").getText();
                LOGGER.info(driver.getTitle());
                writeBookFile(driver.getTitle(), chapter);
                element.click();
                writeFile(Paths.get("files/", "contador.txt"), cont++ + ". " + driver.getTitle());
            } else {
                String chapter = ((RemoteWebDriver) driver).findElementByClassName("chapter").getText();
                writeBookFile(driver.getTitle(), chapter);
                writeFile(Paths.get("files/", "contador.txt"), cont++ + ". " + driver.getTitle());
                lastChapter = false;
            }
        }
    }

    //@ParameterizedTest
    //@CsvFileSource(resources = "links.csv", numLinesToSkip = 1, delimiter = ';')
    //@DisabledIfSystemProperty(named = "api",matches = "api")
    public void getLinksDownloadBooks(ArgumentsAccessor accessor) {
        Link link = new Link(
                accessor.get(0).toString(),
                accessor.get(1).toString(),
                accessor.get(2).toString(),
                accessor.get(3).toString(),
                accessor.get(4).toString()
        );
        System.out.println(link);
        System.out.printf("%s - %s%n", accessor.get(0).toString(), accessor.get(4).toString());

        WebDriver driver = getDriver();
        driver.get(link.getLink());
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String title = driver.getTitle();
        System.out.println(title);
        screenshot((RemoteWebDriver) driver, "screenshot");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("test-bookpdf-link")));

        writeDownloadLinks(element.getAttribute("href"));
        if (botaoExiste((RemoteWebDriver) driver)) {
            WebElement ebookLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("test-bookepub-link")));
            if (ebookLink.isDisplayed()) writeDownloadLinks(ebookLink.getAttribute("href"));
        }

    }

    private boolean botaoExiste(RemoteWebDriver driver) {
        try {
            return driver.findElementByClassName("test-bookepub-link").isDisplayed();
        } catch (Exception e) {
            System.err.println("Não tem ebook");
            return false;
        }

    }

    @Test
    void callBookAPI() {
        assertTrue(getBaseUrl("978-1-137-52502-4".replace("-", ""))
                .contains("https://www.googleapis.com/books/v1/volumes?q=isbn%3D9781137525024&key="));
    }

    public void runFirefox() {
        try {

            URL url = new URL("http://localhost:4445/wd/hub");
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("http://github.com");
            System.out.println(driver.getTitle());

            //screenshot(driver, "screenshot");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
