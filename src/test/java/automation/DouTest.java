package automation;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Roman Uholnikov
 */
public class DouTest extends Base {

    public static final String DOU_UA_COMPANIES = "http://jobs.dou.ua/companies/?city=%D0%9A%D0%B8%D0%B5%D0%B2";

    /* how much pages should be worked */
    public static final int HOW_MUCH_PAGE_DIAL_WITH = 20; //todo change it
    public static final String EMAIL_SUBJECT = "QA Engineer, Kiev";
    public static final String ATTACHET_FILE_NAME = "Julia Loboda.docx";
    public static final String MESSAGE_TEXT = "Good day! \n" +
            "\n" +
            "My name Is Julia, I am QA Engineer; I am looking for job, that will give me a chance to be the best specialist in my sphere. I want to improve my skills and be useful for team in your company! I am interested in new experience, in the excellent result, that is why I can be interesting for you! Thank you in advance for your attention!\n" +
            "\n\n" +
            "With kind regards,\n" +
            "Julia Loboda";

    /**
     * This method should search through all companies in dou and get their information (names and emails).
     *
     * @throws Exception
     */
    @Test
    public void getCompaniesNamesAndEmails() throws Exception {
        /* open site */
        getDriver().get(DOU_UA_COMPANIES);
        /* unwrap as much as possible pages*/
        for (int i = 0; i < HOW_MUCH_PAGE_DIAL_WITH; i++) {
            Thread.sleep(1000);
            if(getDriver().findElements(By.xpath("//div[@class='more-btn']/a")).size()>0){
                scrollDownToElement(By.xpath("//div[@class='more-btn']/a"));
                getDriver().findElement(By.xpath("//div[@class='more-btn']/a")).click();
            }else{
                break;
            }
        }

        List<DouCompany> listOfCompanies = new LinkedList<DouCompany>();

        pause(1);
        List<WebElement> companiesWebElements = getDriver().findElements(By.xpath("//div[@class='company']"));

        for (WebElement companyWebElement : companiesWebElements) {
            DouCompany company = getDouCompany(companyWebElement);
            listOfCompanies.add(company);
            System.out.println(listOfCompanies.size() + " " + company.toString());
            //sendEmails();
            for(String email: company.getEmails()){
                sendEmails(email, EMAIL_SUBJECT, MESSAGE_TEXT , ATTACHET_FILE_NAME);
            }
        }
    }

    private void sendEmails(String emailReveiver, String title, String TextMessage, String attachetFileName) {
        //create email
        //send email to the each company and show it in the log
    }

    private DouCompany getDouCompany(WebElement webElement) throws InterruptedException {

        if (getDriver().findElements(By.xpath("//span[@class='city'  and  contains(.,'Ки')]")).size() > 0) {

            DouCompany company = new DouCompany();
            company.setName(webElement.findElement(By.className("cn-a")).getText());
            company.setCity(webElement.findElement(By.className("city")).getText());

            String link = webElement.findElement(By.className("cn-a")).getAttribute("href") + "offices/";

            WebDriver driver1 = getNewDriver();
            driver1.get(link);


            Thread.sleep(1000);
            List<WebElement> addressesWebElementList = driver1.findElements(By.xpath("//div[@class='table' and preceding-sibling::h4[contains(.,'Ки')]]//div[@class='contacts']/div[@class='address']"));
            List<WebElement> mailWebElementList = driver1.findElements(By.xpath("//div[@class='table' and preceding-sibling::h4[contains(.,'Ки')]]//div[@class='contacts']/div[@class='mail']/a"));
            List<WebElement> phonesWebElementList = driver1.findElements(By.xpath("//div[@class='table' and preceding-sibling::h4[contains(.,'Ки')]]//div[@class='contacts']/div[@class='phones']"));

            for (WebElement addressWeb : addressesWebElementList) {
                String address = addressWeb.getText();
                if (address != null & !address.equalsIgnoreCase("")) {
                    company.getAdresses().add(address);
                }
            }
            for (WebElement mailWeb : mailWebElementList) {
                String mail = mailWeb.getText();
                if (mail != null & !mail.equalsIgnoreCase("")) {
                    company.getEmails().add(mail);
                }
            }

            for (WebElement phoneWeb : phonesWebElementList) {
                String phone = phoneWeb.getText();
                if (phone != null & !phone.equalsIgnoreCase("")) {
                    company.getPhoneNumbers().add(phone);
                }
            }

            driver1.quit();
            return company;
        } else {
            return null;
        }
    }
}
