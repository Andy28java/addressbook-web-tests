package ru.kurs.addressbook;

import org.testng.annotations.Test;
import ru.kurs.addressbook.tests.TestBase;

public class CreateNewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        //app.getNavigationHelper().
        addNewContact();
        fillContDate(new ContactData("Ivan", "Petrovich", "Surov", "SPI", "Testing", "1234567", "qwe@mail.ru"));
        submit();
        goToHomeP();
    }

}
