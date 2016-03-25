package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;
import ru.kurs.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by yana on 3/23/2016.
 */
public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();

        ContactData contactInfoFromEditFormD = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailForm = app.contact().infoFromDetailForm(contact);

        String formData = mergeInfo(contactInfoFromEditFormD);
        String allData = cleaned(contactInfoFromDetailForm.getAllDetails());
        assertThat(allData, equalTo(formData));
    }
    private String mergeInfo(ContactData contact){
       return Arrays.asList(
               contact.getFirstname() + " " + contact.getMiddlename() + " " +contact.getLastname(),
              contact.getAddress(),

               contact.getHomephone().isEmpty() ? "" : "H: " + contact.getHomephone(),
               contact.getMobilephone().isEmpty() ? "" : "M: " + contact.getMobilephone(),
               contact.getWorkphone().isEmpty() ? "" : "W: " + contact.getWorkphone(),
               contact.getEmails().email[0].decorated,
               contact.getEmails().email[1].decorated,
               contact.getEmails().email[2].decorated)
                .stream().filter((s)-> ! s.equals("")).collect(Collectors.joining("\n"));

    }

    private String getEmailAsString(String email) {
        int i = email.indexOf("@");

        if ( i == -1) {
            return email;
        }

        String domain = email.substring(i + 1);
        if (domain.isEmpty()) {

            return email;
        }

        return email + " (www." + domain + ")";
    }

    public String cleaned(String detail){
        return detail.replaceAll("\n\n", "\n");
    }
}