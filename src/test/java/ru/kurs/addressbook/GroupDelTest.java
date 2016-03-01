package ru.kurs.addressbook;

import org.testng.annotations.Test;

public class GroupDelTest extends TestBase {


    @Test
    public void testGroupDel() {
       gotoGroupPage();
        selectGroupe();
        DeleteSelectedGroups();
        gotoGroupPage();
    }

}
