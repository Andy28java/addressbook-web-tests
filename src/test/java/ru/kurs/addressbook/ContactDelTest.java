package ru.kurs.addressbook;

import org.testng.annotations.Test;
import ru.kurs.addressbook.tests.TestBase;

public class ContactDelTest extends TestBase {

    @Test
    public void testContDel() {

        goToHomeP();
        selectContact();
        //wd.findElement(By.id("8")).click();
        //}
        //"//table/tbody/tr[4]/td[1]/input"
        //wd.findElement(By.name("selected[]")).click();
//        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
        delSelectedContact();
        goToHomeP();


    }


}
