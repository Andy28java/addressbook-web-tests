package ru.kurs.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.appmanager.GpoupHelper;
import ru.kurs.addressbook.model.ContactData;
import static org.testng.Assert.assertTrue;
/**
 * Created by yana on 3/2/2016.
 */
public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        final ContactHelper h = app.getContactHelper();
        h.editContact();

        h.fillContDate(new ContactData("Ivan", "Petrovich", "Surov",null, null, null, null));// "SPI", "Testing", "1234567", "qwe@mail.ru"));
        h.update();
       app.getNavigationHelper().goToHomePage();
    }
}
