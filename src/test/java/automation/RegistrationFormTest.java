package automation;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 07.02.15.
 */
public class RegistrationFormTest extends Base {

    /**
     * Tests subscription if email field is wrong
     */
    @Test
    public void testSubscriptionWithWrongEmail(){
        String formUrl = "http://localhost:8080/MyWebApp/form.jsp";
        //open url
        getDriver().get(formUrl);
        pause(1);
        //enter name
        getDriver().findElement(By.name("name")).sendKeys("sdf");
        //enter incorrect email
        getDriver().findElement(By.xpath("//input[@type='email']")).sendKeys("incorrect email");
        //tick keep posted
        getDriver().findElement(By.name("keepposted")).click();
        //click send
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        pause(3);

        //Checking
        //page url did not changed
        assertTrue(getDriver().getCurrentUrl().equals(formUrl));
    }

    @Test
    public void testSubscriptionWithoutEmail(){
        String formUrl = "http://localhost:8080/MyWebApp/form.jsp";
        //open url
        getDriver().get(formUrl);
        pause(1);
        //enter name
        getDriver().findElement(By.name("name")).sendKeys("sdf");
        //enter some email
        getDriver().findElement(By.xpath("//input[@type='email']")).sendKeys("some@email");
        //we DONOT tick keep posted
        //getDriver().findElement(By.name("keepposted")).click();
        //click send
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        //let us give page time for loading
        pause(3);

        //Checking
        //page contain block with name messages
        assertTrue(getDriver().findElement(By.className("name-success"))!=null);
        
        //test fail because of this checking.!!!!
        //element "subscription-success" should not be on the page! (change it to ==0)
        assertTrue(getDriver().findElements(By.className("subscription-success")).size()==0);

    }

    /**
     * Tests subscription if name field is empty
     */
    @Test
    public void testSubscriptionWithoutName(){
    	
    	String formUrl = "http://localhost:8080/MyWebApp/form.jsp";
        //open url
        getDriver().get(formUrl);
        pause(1);
       
        //enter name
        getDriver().findElement(By.name("name")).sendKeys(" ");
       
        //enter email
        getDriver().findElement(By.xpath("//input[@type='email']")).sendKeys("aaa@aa");
       
        //tick keep posted
        getDriver().findElement(By.name("keepposted")).click();
        
        //click send
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        pause(3);

        //Checking
        //page url did not changed
        assertTrue(getDriver().findElement(By.xpath("//div[@role='alert']"))!=null);
    
        
    }
    	
    	

    /**
     * Tests subscription successfully
     */
    @Test
    public void testSubscriptionSuccessfully(){
    	String formUrl = "http://localhost:8080/MyWebApp/form.jsp";
        //open url
        getDriver().get(formUrl);
        pause(1);
       
        //enter email
        getDriver().findElement(By.xpath("//input[@type='email']")).sendKeys("aaa@aa");
        
        //enter name
        getDriver().findElement(By.name("name")).sendKeys("name");
       
             
        //tick keep posted
        getDriver().findElement(By.name("keepposted")).click();
        
        //click send
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        pause(3);

        //Checking
        assertTrue(getDriver().findElement(By.className("name-success"))!=null);
        assertTrue(getDriver().findElement(By.xpath("//p[@class='name-success']/b/i")).getText().equals("name"));
        assertTrue(getDriver().findElement(By.className("subscription-success"))!=null);
        assertTrue(getDriver().findElement(By.xpath("//p[@class='subscription-success']/i/b")).getText().equals("aaa@aa"));
    }
}
