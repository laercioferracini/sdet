package utils;

import java.util.Properties;

public class DriverUtils {

    public static void setDriverProperty() {

        String os = System.getProperty("os.name").toUpperCase();
        String fileSeparator = System.getProperty("file.separator");
        String driverFile = "driver".concat(fileSeparator);

        if (os.contains("LINUX")) {
           driverFile = driverFile.concat("geckodriver");
        } else if (os.contains("WINDOWS")) {
            driverFile = driverFile.concat("geckodriver.exe");
        }

        System.setProperty("webdriver.gecko.driver", driverFile);
    }

    public static void listProperties() {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> System.out.printf("K: %s-> V: %s %n", k, v));
    }

    public static void main(String[] args) {
        listProperties();
    }
}
