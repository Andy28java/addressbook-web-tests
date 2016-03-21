package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.appmanager.NavigationHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.List;

public class ContactDelTest extends TestBase {

    @BeforeMethod
    public  void ensurePreconditionsCont() {
        final ContactHelper h = app.contact();
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {   //!h.hasContacts()) {
            h.addContact();
            h.fillCont(new ContactData()
                    .withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Surov").withNickname("SPI")
                    .withCompany("Testing").withHomephone("1234567").withEmail2("qwe@mail.ru"));
            h.submit();
        }
    }
    @Test //(enabled = false)
    public void testContDel() {
        final ContactHelper h = app.contact();
        final NavigationHelper n = app.goTo();        n.homePage();
        List<ContactData> before = h.list();
        int index = before.size() - 1;
        h.selectContact(index);
        h.deleteSelectedContact();
        n.homePage();
        List<ContactData> after = h.list();

        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}

