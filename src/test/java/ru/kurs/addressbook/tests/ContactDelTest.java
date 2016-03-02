package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.tests.TestBase;

public class ContactDelTest extends TestBase {

    @Test
    public void testContDel() {
        final ContactHelper h = app.getContactHelper();
        h.goToHomePage();
        h.selectContact();
        h.deleteSelectedContact();
        h.goToHomePage();
    }
}
