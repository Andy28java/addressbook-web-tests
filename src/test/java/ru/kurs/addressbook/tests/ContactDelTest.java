package ru.kurs.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.appmanager.NavigationHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactDelTest extends TestBase {

    @BeforeMethod
    public  void ensurePreconditionsCont() {
        final ContactHelper h = app.contact();
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {   //!h.hasContacts()) {
            h.addContact();
            h.fillCont(new ContactData()
                    .withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Surov").withNickname("SPI")
                    .withCompany("Testing").withHomephone("1234567").withEmail2("qwe@mail.ru"));
            h.submit();
        }
    }
    @Test(enabled = true)
    public void testContDel() {
        final ContactHelper h = app.contact();
        final NavigationHelper n = app.goTo();
        n.homePage();
        Contacts before = (Contacts) h.all();
        ContactData deletedContact = before.iterator().next();
       h.selectContactById(deletedContact.getId());
        h.deleteSelectedContact();
        n.homePage();
        Contacts after = (Contacts) h.all();

        assertEquals(after.size(),before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)) );
    }
}

