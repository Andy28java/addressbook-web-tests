package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GpoupHelper;
import ru.kurs.addressbook.model.GroupData;

/**
 * Created by yana on 3/2/2016.
 */
public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() {
        final GpoupHelper h = app.getGpoupHelper();
        h.gotoGroupPage();
        h.selectGroup();
        h.initGroupeModification();
        h.fillGroupeForm(new GroupData("test1", "1", "11"));
        h.submitGroupeModification();
        h.gotoGroupPage();
    }
}
