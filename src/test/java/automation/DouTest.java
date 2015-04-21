package automation;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Roman Uholnikov
 */
public class DouTest extends Base {

    public static final String DOU_UA_COMPANIES = "http://jobs.dou.ua/companies/?city=%D0%9A%D0%B8%D0%B5%D0%B2";

    /* how much pages should be worked */
    public static final int HOW_MUCH_PAGE_DIAL_WITH = 2; //todo change it

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
            scrollDownToElement(By.xpath("//div[@class='more-btn']/a"));
            getDriver().findElement(By.xpath("//div[@class='more-btn']/a")).click();
        }

        List<DouCompany> listOfCompanies = new LinkedList<DouCompany>();

        List<WebElement> companiesWebElements = getDriver().findElements(By.xpath("//div[@class='company']"));

        //todo after coming back here is not list anymore
        for (WebElement companyWebElement : companiesWebElements) {
            DouCompany company = getDouCompany(companyWebElement);
            listOfCompanies.add(company);
            System.out.println(company);
            //sendEmails();
        }
    }

    private void sendEmails(String[] emails, String title, String TextMessage, String attachetFileName) {
        //create email
        //send email to the each company and show it in the log
    }

    private DouCompany getDouCompany(WebElement webElement) throws InterruptedException {

        if (getDriver().findElements(By.xpath("//span[@class='city'  and  contains(.,'Ки')]")).size() > 0) {

            Thread.sleep(1000);
            DouCompany company = new DouCompany();
            company.setName(webElement.findElement(By.className("cn-a")).getText());
            company.setCity(webElement.findElement(By.className("city")).getText());
            webElement.findElements(By.className("fbs")).get(1).click();

            Thread.sleep(1000);
            List<WebElement> addressesWebElementList = getDriver().findElements(By.xpath("//div[@class='table' and preceding-sibling::h4[contains(.,'Ки')]]//div[@class='contacts']/div[@class='address']"));
            List<WebElement> mailWebElementList = getDriver().findElements(By.xpath("//div[@class='table' and preceding-sibling::h4[contains(.,'Ки')]]//div[@class='contacts']/div[@class='address']"));

            for (WebElement addressWeb : addressesWebElementList) {
                String address = addressWeb.getText();
                if (address != null & !address.equalsIgnoreCase("")) {
                    company.getAdresses().add(address);
                }
            }
            for (WebElement mailWeb : mailWebElementList) {
                String mail = mailWeb.findElement(By.xpath("//a")).getText();
                if (mail != null & !mail.equalsIgnoreCase("")) {
                    company.getEmails().add(mail);
                }
            }
            getDriver().navigate().back();

            return company;
        } else {
            return null;
        }
    }
}
