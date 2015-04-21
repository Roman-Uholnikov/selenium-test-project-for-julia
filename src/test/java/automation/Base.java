package automation;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by roman on 07.02.15.
 */
public class Base {

    protected WebDriver driver = new FirefoxDriver();

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Before
    public void setUp() throws Exception {
        this.getDriver();
        WebDriver.Options options = getDriver().manage();
        WebDriver.Window window = options.window();

        // Make sure all elements are visible.
        window.maximize();
    }

    @After
    public void tearDown() {
        getDriver().quit();
    }

//    /**
//     * @param seconds amount of seconds
//     * @param locator elements which is expected
//     */
//    public void pause(String locator, long seconds) {
//        new WebDriverWait(driver, seconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
//    }

    public void pause(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param locator locator
     */
    public void scrollDownToElement(By locator) {
        WebElement element = getElementByLocator(locator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement getElementByLocator(By locator) {
        WebElement element = null;
        try {
            element = getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found. Locator: " + locator);
        }
        return element;
    }
}
