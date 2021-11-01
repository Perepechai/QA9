import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import  java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class MyTest {
    WebDriver driver;
    WebDriverFactory allDriver = new WebDriverFactory();
    By googleSearchInput = By.xpath("//input[@class=\"gLFyf gsfi\"]");
    By addressSite = By.xpath("//cite");
    @Test
    public void test1()
    {
        driver = allDriver.createWebDriver(DriverType.CHROME);
        driver.get("https://www.google.com/");
        WebElement input1 =  driver.findElement(googleSearchInput);
        input1.sendKeys("SoftServe");
        input1.sendKeys(Keys.ENTER);
        List<WebElement> resultSearch = driver.findElements(addressSite);
        for (WebElement res: resultSearch) {
            String str = res.getText();
            if (str.contains("https://www.softserveinc.com")){
                res.click();
                break;
            }
        }
        String str = driver.getCurrentUrl();
        Assert.assertTrue(str.contains("https://www.softserveinc.com"));
    }
    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result)
    {
        if (!result.isSuccess())
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }

}
