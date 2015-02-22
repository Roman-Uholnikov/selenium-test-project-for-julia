package automation;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 07.02.15.
 */
public class MailTest extends Base {
	
	private static String USER_NAME = "enter your login";
	private static String USER_PASSWORD = "enter your password here";

    /**
     * “ест дл€ проверки почтового €щика. “ест не пройдет, в случае если в почтовм €щике есть непрочтенные письма.
     * ≈сли в почтовом €щике нет новых писем - тест будет успешно пройден.
     */
    @Test
    public void testSubscriptionWithWrongEmail(){
        String formUrl = "https://mail.ru";
        getDriver().get(formUrl);
        pause(3);
        getDriver().findElement(By.name("Login")).sendKeys(USER_NAME);
        getDriver().findElement(By.name("Password")).sendKeys(USER_PASSWORD);        
        //click send
        getDriver().findElement(By.className("mailbox__auth__button")).click();
        pause(3);

        //todo sdf
        //Checking
       
        assertTrue(getDriver().findElements(By.xpath("//div[@data-id='0']//span[@class='b-nav__item__count']")).size()==0);
    }

    
}
