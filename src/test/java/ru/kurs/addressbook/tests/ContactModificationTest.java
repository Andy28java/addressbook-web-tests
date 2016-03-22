package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yana on 3/2/2016.
 */
public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public  void ensurePreconditionsCont() {
        final ContactHelper h = app.contact();
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {    //!h.hasContacts()) {
            h.addContact();
            h.fillCont(new ContactData()
                    .withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Surov").withNickname("SPI")
                    .withCompany("Testing").withHomephone("1234567").withEmail2("qwe@mail.ru"));
            h.submit();
        }
    }
    @Test (enabled = false)
    public void testContactModification() {
        final ContactHelper h = app.contact();
        Set<ContactData> before = h.all();
        ContactData contact = before.iterator().next();

        ContactData modifiedContact = new ContactData()
                .withId(contact.getId()).withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Surov");
        h.modify(modifiedContact);
        Set<ContactData> after = h.all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(contact);
        before.add(modifiedContact);
        Assert.assertEquals(before, after);

    }


}
