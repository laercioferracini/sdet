import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lferracini
 * @project = bible-api
 * @since <pre>05/04/2020</pre>
 */
public class BibleTest {

    public WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://bible.com/pt/bible/1275/DAN.1.CJB");

        driver.manage().window().maximize();
        return driver;
    }

    @Test
    public void getChaptersFromBook() throws IOException {

        WebDriver driver = getDriver();
        driver.get("https://bible.com/pt/bible/1275/GEN.1");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        System.out.println(driver.getTitle());
        FilesUtils.screenshot((RemoteWebDriver) driver, "screenshot");
        Assert.assertThat(driver.getTitle(), CoreMatchers.containsString("CJB"));
        int cont = 1;
        while (!driver.getTitle().contains("(Rev) 22")) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nav-right")));
            String chapter = ((RemoteWebDriver) driver).findElementByClassName("chapter").getText();
            //System.out.println(chapter);
            writeBookFile(driver.getTitle(), chapter);
            element.click();
            FilesUtils.writeFile(Paths.get("files/", "contador.txt"), String.valueOf(cont++));
        }

        System.out.println(driver.getTitle());
        driver.quit();// kill the driver and the browser
    }

    private static void writeBookFile(String title, String chapter) throws IOException {

        String tChapter = title.split(",")[0];
        String book = tChapter.substring(tChapter.indexOf("(") + 1, tChapter.lastIndexOf(")"));
        Path dir = Paths.get("files/" + book + "/");

        FilesUtils.createDir(dir);

        Path pathFile = Paths.get("files/" + book + "/", tChapter + ".txt");

        FilesUtils.createFile(pathFile);

        FilesUtils.writeFile(pathFile, chapter);

    }


    @Test
    public void runChromeYahoo() {
        try {

            URL url = new URL("http://localhost:4444/wd/hub");
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            RemoteWebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("https://bible.com/pt/bible/1275/DAN.1.CJB");
            System.out.println(driver.getTitle());

            FilesUtils.screenshot(driver, "screenshot");
            driver.findElement(By.cssSelector(".next-arrow > a:nth-child(1) > div:nth-child(1) > svg:nth-child(1)")).click();
            Thread.sleep(2000);
            System.out.println(driver.getTitle());
            driver.quit();
        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
