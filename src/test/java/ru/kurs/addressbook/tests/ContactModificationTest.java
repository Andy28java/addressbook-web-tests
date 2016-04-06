package ru.kurs.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by yana on 3/2/2016.
 */
public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public  void ensurePreconditionsCont() {
        final ContactHelper h = app.contact();
        if (app.db().contacts().size() == 0){
        app.goTo().homePage();
        //if (app.contact().all().size() == 0) {    //!h.hasContacts()) {
            h.addContact();
            h.fillCont(new ContactData()
                    .withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Surov").withNickname("SPI")
                    .withCompany("Testing").withHomephone("1234567").withEmail2("qwe@mail.ru"),false);
            h.submit();
        }
    }

    @Test//(enabled = true)
    public void testContactModification() {
        final ContactHelper h = app.contact();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();

        ContactData modifiedContact = contact
                .withId(contact.getId()).withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Surov");
        app.goTo().homePage();
        h.modify(modifiedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(contact).withAdded(modifiedContact)));

    }


}
