package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private WebDriver driver;

    public WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(Config.WINDOW_SIZE);

        return driver;
    }

    public void openUrl(String url) {
        driver.get(url);
    }
}
