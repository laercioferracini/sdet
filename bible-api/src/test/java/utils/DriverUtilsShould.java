package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Driver utils should")
class DriverUtilsShould {

    @Test
    @DisplayName("set driver property correctly")
    void setDriverPropertyCorrectly() {
        DriverUtils.setDriverProperty();
        assertEquals("driver/geckodriver", System.getProperty("webdriver.gecko.driver"));
    }
}