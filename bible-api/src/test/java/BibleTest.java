import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = bible-api
 * @since <pre>05/04/2020</pre>
 */
public class BibleTest {
    private final static Logger LOGGER = Logger.getLogger(BibleTest.class.getName());

    public WebDriver getDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://bible.com/pt/bible/1275/DAN.1.CJB");

        driver.manage().window().maximize();
        return driver;
    }

    @Test
    public void verificaTitulo() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://bible.com/pt/bible/1275/DAN.1.CJB");
        System.out.println(driver.getTitle());
        screenshot((RemoteWebDriver) driver, "screenshot");
        Assert.assertThat(driver.getTitle(), CoreMatchers.containsString("CJB"));
        driver.findElement(By.cssSelector(".next-arrow > a:nth-child(1) > div:nth-child(1) > svg:nth-child(1)")).click();
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        driver.quit();// kill the driver and the browser
    }

    @Test
    public void runChromeYahoo() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("https://bible.com/pt/bible/1275/DAN.1.CJB");
            System.out.println(driver.getTitle());

            screenshot(driver, "screenshot");
            driver.findElement(By.cssSelector(".next-arrow > a:nth-child(1) > div:nth-child(1) > svg:nth-child(1)")).click();
            Thread.sleep(2000);
            System.out.println(driver.getTitle());
            driver.quit();
        } catch (MalformedURLException | InterruptedException e) {
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
