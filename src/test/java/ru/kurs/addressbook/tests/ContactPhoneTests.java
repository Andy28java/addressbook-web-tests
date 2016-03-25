package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;
import ru.kurs.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


/**
 * Created by yana on 3/22/2016.
 */
public class ContactPhoneTests  extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getPhones(),equalTo(contactInfoFromEditForm.getPhones()));
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getEmails(),equalTo(contactInfoFromEditForm.getEmails()));
    }
    public String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
