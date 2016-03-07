package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.tests.TestBase;

public class CreateNewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        final ContactHelper h = app.getContactHelper();
        h.addNewContact();
        h.fillContDate(new ContactData("Ivan", "Petrovich", "Surov", "SPI", "Testing", "1234567", "qwe@mail.ru"));
        h.submit();
       app.getNavigationHelper().goToHomePage();
    }
}
