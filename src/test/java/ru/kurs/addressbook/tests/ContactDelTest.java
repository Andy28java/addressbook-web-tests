package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.appmanager.NavigationHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

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
    @Test (enabled = false)
    public void testContDel() {
        final ContactHelper h = app.contact();
        final NavigationHelper n = app.goTo();
        n.homePage();
        Set<ContactData> before = h.all();
        ContactData deletedContact = before.iterator().next();
        //int index = before.size() - 1;
        //h.selectContact(deletedContact);
        h.selectContactById(deletedContact.getId());
        h.deleteSelectedContact();
        n.homePage();
        Set<ContactData> after = h.all();

        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}

