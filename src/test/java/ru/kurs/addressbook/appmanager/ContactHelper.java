package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kurs.addressbook.model.ContactData;

import java.util.List;

import static org.testng.Assert.assertTrue;

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

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public  void selectContact() {
        click(By.xpath("//table/tbody/tr[2]/td[1]/input"));
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

    //public void goToHomePage() { click(By.linkText("home")); }

}
