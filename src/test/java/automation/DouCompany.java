package automation;

import java.util.LinkedList;
import java.util.List;

/**
 * DAO class for company
 *
 * @author Roman Uholnikov
 */
public class DouCompany {

    private String name;

    private List<String> phoneNumbers = new LinkedList<String>();

    private List<String> emails = new LinkedList<String>();

    private String city;

    private List<String> adresses = new LinkedList<String>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(final List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(final List<String> emails) {
        this.emails = emails;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public List<String> getAdresses() {
        return adresses;
    }

    public void setAdresses(final List<String> adresses) {
        this.adresses = adresses;
    }

    @Override
    public String toString() {
        return  name + ": \n" +
                "    city = " + city + "\n" +
                "    email = " + emails + "\n" +
                "    adress = " + adresses + "\n" +
                "    phoneNumbers = " + phoneNumbers + "\n\n";
    }
}
