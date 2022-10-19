package framework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class Driver {
    private WebDriver driver;
    private final String PATH_TO_DRIVERS_REPOSITORY = "src/target/drivers/";
    // hardcoding browserName for demo purposes
    private String browserName = "chrome";

    private Driver() {
    }

    private static Driver instance = new Driver();

    public static Driver getInstance() {
        return instance;
    }

    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            DesiredCapabilities capabilities;
            if (driver == null) {
                switch (browserName) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", new File(PATH_TO_DRIVERS_REPOSITORY + "chromedriver.exe").getPath());
                        capabilities = DesiredCapabilities.chrome();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("start-maximized");
                        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                        driver = new ChromeDriver(capabilities);
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new IllegalArgumentException("Browser is not supported:" + browserName);
                }
            }
            return driver;
        }
    };

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    public void removeDriver() {
        driver = threadLocal.get();
        try {
            driver.manage().deleteAllCookies();
            driver.quit();
        } finally {
            threadLocal.remove();
        }
        closeDrivers();
    }

    private void closeDrivers() {
        switch (browserName) {
            case "chrome":
                try {
                    Runtime.getRuntime().exec("taskkill /f /t /im " + "chromedriver.exe").waitFor();
                } catch (IOException e) {
                    System.out.println("Failed to kill process: " + e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
