package automation;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 07.02.15.
 */
public class MailTest extends Base {

    /* you should enter your credential here */
	private static String USER_NAME = "enter your login";
	private static String USER_PASSWORD = "enter your password here";

    public static final String HTTPS_MAIL_RU = "https://mail.ru";

    /**
     * This your mail inbox for mail.ru
     * If your inbox is empty - test will success
     * if you have new unread messages- test will fail.
     *
     */
    @Test
    public void testSubscriptionWithWrongEmail(){
        String url = HTTPS_MAIL_RU;
        getDriver().get(url);
        pause(3);
        getDriver().findElement(By.name("Login")).sendKeys(USER_NAME);
        getDriver().findElement(By.name("Password")).sendKeys(USER_PASSWORD);        
        //click send
        getDriver().findElement(By.className("mailbox__auth__button")).click();
        pause(3);

        //Checking
        //this XPath try grab all elements, that shows unread messages
        assertTrue(getDriver().findElements(By.xpath("//div[@data-id='0']//span[@class='b-nav__item__count']")).size()==0);
    }

    
}
