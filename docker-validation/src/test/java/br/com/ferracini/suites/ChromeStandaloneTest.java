package br.com.ferracini.suites;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static br.com.ferracini.utils.DockerUtils.start;
import static br.com.ferracini.utils.DockerUtils.stop;

/**
 * @author lferracini
 * @project = docker-validation
 * @since <pre>31/03/2020</pre>
 */
public class ChromeStandaloneTest {
    private final static Logger LOGGER = Logger.getLogger(ChromeStandaloneTest.class.getName());

    @BeforeSuite
    public void setUp() throws IOException, InterruptedException {
        start();
    }

    @AfterSuite
    public static void after() throws IOException, InterruptedException {
        stop();
    }

    @Test
    public void runFirefox() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            FirefoxOptions caps = new FirefoxOptions();
            caps.toJson().forEach((k, v) -> System.out.printf("K:%s - V:%s%n", k, v));
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("http://github.com");
            System.out.println(driver.getTitle());

            //screenshot(driver, "screenshot");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runChrome() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            Capabilities caps = DesiredCapabilities.chrome();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("http://github.com");
            System.out.println(driver.getTitle());

            screenshot(driver, "screenshot");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runChromeGoogle() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            Capabilities caps = DesiredCapabilities.chrome();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("http://google.com");
            System.out.println(driver.getTitle());

            screenshot(driver, "screenshot");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runChromeGmail() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            Capabilities caps = DesiredCapabilities.chrome();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("http://gmail.com");
            System.out.println(driver.getTitle());

            screenshot(driver, "screenshot");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runChromeYahoo() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            Capabilities caps = DesiredCapabilities.chrome();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("http://yahoo.com");
            System.out.println(driver.getTitle());

            screenshot(driver, "screenshot");
            driver.quit();
        } catch (MalformedURLException e) {
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
