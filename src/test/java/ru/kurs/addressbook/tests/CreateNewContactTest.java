package ru.kurs.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;
import ru.kurs.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewContactTest extends TestBase {
    //Logger logger = LoggerFactory.getLogger(CreateNewContactTest.class);

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new ContactData().withFirstname(split[1]).withMiddlename(split[2]).withLastname(split[3])
                        .withAddress(split[4]).withEmail(split[5])});
                line = reader.readLine();
            }

        /*list.add(new Object[] {new ContactData().withFirstname("Ivan").withMiddlename("Petrovich").withLastname("Surov")
            .withHomephone("11233456677").withAddress("USA,First St,125").withEmail("sip@nf.com")});
         list.add(new Object[] {new ContactData().withFirstname("Ivan2").withMiddlename("Petrovich2").withLastname("Surov2")
                 .withHomephone("21233456677").withAddress("USA,First St,225").withEmail("sip2@nf.com")});
        list.add(new Object[] {new ContactData().withFirstname("Ivan3").withMiddlename("Petrovich3").withLastname("Surov3")
                .withHomephone("31233456677").withAddress("USA,First St,325").withEmail("sip3@nf.com")});*/
            return list.iterator();
        }
    }
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")//(enabled = false)
    public void createNewContact(ContactData contact) {

        final ContactHelper h = app.contact();
        app.goTo().homePage();
        Contacts before = app.db().contacts();//(Contacts) app.contact().all();

        File photo = new File("src/test/resources/jivotnie-1656.png");
        contact = contact.withPhoto(photo);
        //ContactData contact = new ContactData().withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Durov").withPhoto(photo);
        h.addContact();
        h.fillCont(contact);
        h.submit();
        app.goTo().homePage();
        Contacts after = app.db().contacts();//(Contacts) h.all();

        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);

        assertThat(after, equalTo(before));
    }


   /* @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/jivotnie-1656.gif");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}