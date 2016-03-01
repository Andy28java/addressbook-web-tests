package ru.kurs.addressbook.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import ru.kurs.addressbook.appmanager.ApplicationManager;

/**
 * Created by yana on 3/1/2016.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeTest
    public void setUp() throws Exception {
        app.init();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }

}
