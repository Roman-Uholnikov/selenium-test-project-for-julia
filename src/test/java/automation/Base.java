package automation;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by roman on 07.02.15.
 */
public class Base {

    protected WebDriver driver = new FirefoxDriver();
    protected WebDriver driverForEmail = new FirefoxDriver();

    public WebDriver getDriver() {
        return driver;
    }


    public WebDriver getDriverForEmail() {
        return driverForEmail;
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

        this.getDriverForEmail();
        WebDriver.Options optionsEmail = getDriverForEmail().manage();
        WebDriver.Window windowEmail = optionsEmail.window();

        // Make sure all elements are visible.
        windowEmail.maximize();

    }

    @After
    public void tearDown() {
        getDriver().quit();
        getDriverForEmail().quit();
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

    public WebDriver getNewDriver(){
        WebDriver driver = new FirefoxDriver();
        WebDriver.Options options = driver.manage();
        WebDriver.Window window = options.window();

        // Make sure all elements are visible.
        window.maximize();
        return driver;
    }

    /**
     * Insert text to iframe input
     *
     * @param frameXpath field name
     * @param text      text that going to be entered
     * @throws Exception
     */
    public void enterTextToIFrame(String frameXpath, String text, WebDriver driver) {

        //enter text to iFrame
        pause(2);
        WebElement idElement = driver.findElement(By.xpath(frameXpath));
        driver.findElement(By.xpath(frameXpath)).click();
        pause(2);
        driver.switchTo().frame(idElement);
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "a");
        body.sendKeys(text);
        body.sendKeys(Keys.ENTER);
        driver.switchTo().defaultContent();
    }

    /**
     * @param seconds amount of seconds
     * @param locator elements which is expected
     */
    public void pause(String locator, long seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }
}
