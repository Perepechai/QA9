import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public WebDriver createWebDriver(DriverType driverType) {
        WebDriver driver;
        switch (driverType) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case IE:
                driver = createIEDriver();
                break;
            default:
                throw new RuntimeException("Unknown web driver type. Need to be added to webDriver.factory");
        }
        return driver;
    }
    protected WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
    protected WebDriver createIEDriver() {
        System.setProperty("webdriver.IE.driver", "IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}
