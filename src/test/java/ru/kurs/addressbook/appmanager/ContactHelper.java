package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kurs.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yana on 3/2/2016.
 */
public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
    public void fillContDate(ContactData data) {
        type(By.name("firstname"), data.getFirstname());
        type(By.name("middlename"), data.getMiddlename());
        type(By.name("lastname"), data.getLastname());
        type(By.name("nickname"), data.getNickname());
        type(By.name("company"), data.getCompany());
        type(By.name("home"), data.getHomephone());
        type(By.name("email2"), data.getEmail2());
    }

    public void editContact(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr["+(index +2)+"]/td[8]/a/img"));
    }

    /*public void editContact(int index) {
        //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).get(index).click();
        //(By.name("selected[]"));
        click(By.xpath("//table[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img[@alt='Edit']"));
    }*/
    public  void selectContact(int index) {
        wd.findElement(By.xpath("//table/tbody/tr["+(index +2)+"]/td[1]/input")).click();
     }


        public void deleteSelectedContact() {
        click(By.xpath("/html/body/div/div[4]/form[2]/div[2]/input"));
        acceptAlert();
    }

    public boolean hasContacts() {
        By b = By.xpath("//table[@id='maintable']/tbody/tr[2]");

        try {
            WebElement e = wd.findElement(b);

            return (b != null);
        } catch (NoSuchElementException ex) {
            return false;
        }
        /*
        List<WebElement> entries =  wd.findElements(b);
        if (entries == null || entries.isEmpty()) {
            return false;
        }
        return true;
        */
    }

    public void submit() {
        click(By.name("submit"));
    }

    public void update() {
        click(By.name("update"));
    }

    public int getContactCount() {
      return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[1]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> tds = element.findElements(By.tagName("td"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData d = new ContactData(id, firstname, null, lastname, null, null, null, null);
            contacts.add(d);
        }
        return contacts;
    }

    //public void goToHomePage() { click(By.linkText("home")); }

}
