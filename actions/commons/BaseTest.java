package commons;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowserName(String browserName) {

        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        if (browser == BrowserList.FIREFOX) {
            System.getProperty("webdriver.gecko.driver\", projectPath + \"\\\\browserDrivers\\\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser == BrowserList.CHROME) {
            System.getProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser == BrowserList.EDGE) {
            System.getProperty("webdriver.msedge.driver\", projectPath + \"\\\\browserDrivers\\\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else throw new RuntimeException("Browser name is not valid");

//        switch (browser) {
//            case FIREFOX -> System.getProperty("webdriver.gecko.driver\", projectPath + \"\\\\browserDrivers\\\\geckodriver.exe");
//            driver = new FirefoxDriver();
//            break;
//            case CHROME -> System.getProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//            driver = new ChromeDriver();
//            break;
//            case EDGE -> System.getProperty("webdriver.msedge.driver\", projectPath + \"\\\\browserDrivers\\\\msedgedriver.exe");
//            driver = new EdgeDriver();
//            break;
//            default -> throw new RuntimeException("Browser name is not valid");
//        }

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1920,1080));

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        return driver;
    }


    protected String getEmailRandom() {
        Random rand = new Random();
        return "john" + rand.nextInt(99999) + "@kennedy.us";
    }

    protected void closeBrowser() {
        if (driver == null) {
            System.out.println("Browser is closed");
        } else {
            driver.quit();
        }
    }
}
