package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kurs.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yana on 3/2/2016.
 */
public class ContactHelper extends HelperBase {
    private ApplicationManager app;
    public ContactHelper(ApplicationManager a, WebDriver wd) {
        super(wd);
        app = a;
    }

    public void addContact() {
        click(By.linkText("add new"));
    }
    public void fillCont(ContactData data) {
        type(By.name("firstname"), data.getFirstname());
        type(By.name("middlename"), data.getMiddlename());
        type(By.name("lastname"), data.getLastname());
        type(By.name("nickname"), data.getNickname());
        type(By.name("company"), data.getCompany());
        type(By.name("home"), data.getHomephone());
        type(By.name("email2"), data.getEmail2());
    }

    /*public void editContact(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr["+(index +2)+"]/td[8]/a/img"));
    }*/
    public void editContact(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void editContact1(int id) {
        WebElement image = null;
        List<WebElement> l = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement tr : l) {
            WebElement i = tr.findElement(By.xpath("td[1]/input"));
            String iid= i.getAttribute("id");
            if (iid.equals("" + id)) {
                image = tr.findElement(By.xpath("td[8]/a/img"));
                break;
            }
        }
        if (image != null) {
            image.click();
        }
        //click(By.xpath("//table[@id='maintable']/tbody/tr["+(index +2)+"]/td[8]/a/img"));
    }
    public void modify( ContactData modifiedContact) {
        editContact(modifiedContact.getId());
        fillCont(modifiedContact);
        update();
        app.goTo().homePage();
    }
    /*public void editContact(int index) {
        //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).get(index).click();
        //(By.name("selected[]"));
        click(By.xpath("//table[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img[@alt='Edit']"));
    }*/
    public  void selectContact(int index) {
        wd.findElement(By.xpath("//table/tbody/tr["+(index +2)+"]/td[1]/input")).click();
     }
    public  void selectContactById(int id) {
        //wd.findElement(By.xpath("//table/tbody/tr["+(index +2)+"]/td[1]/input")).click();
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
     public void deleteSelectedContact() {
        click(By.xpath("/html/body/div/div[4]/form[2]/div[2]/input"));
        acceptAlert();
    }

    public void deleteSelectedContactById() {
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> tds = element.findElements(By.tagName("td"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> tds = element.findElements(By.tagName("td"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

}
