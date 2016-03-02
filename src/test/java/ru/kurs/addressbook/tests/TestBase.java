package ru.kurs.addressbook.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import ru.kurs.addressbook.ContactData;
import ru.kurs.addressbook.appmanager.ApplicationManager;

import static org.testng.Assert.assertTrue;

/**
 * Created by yana on 3/1/2016.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
    protected WebDriver wd = null;

    public TestBase() {
        System.out.println("Create test base.");
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void delSelectedContact() {
        wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/div[2]/input")).click();
        closeAlertAndGetItsText();
    }

    protected void selectContact() {
        wd.findElement(By.xpath("//table/tbody/tr[4]/td[1]/input")).click();
    }

    private void closeAlertAndGetItsText() {
        try {
            Alert a = wd.switchTo().alert();
            assertTrue(a != null, "Expected alert does not occur");

            a.accept();

        } catch (NoAlertPresentException e) {
            assertTrue(false, "Expected alert does not occur");
        }
    }

   /* public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }*/

    @BeforeTest
    public void setUp() throws Exception {
        app.init();

        wd = app.getWebDriver();
    }

   /* @BeforeTest
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); wd.get("http://localhost:8081/addressbook/edit.php");
        login("admin", "secret");

    }*/

    private void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    protected void goToHomeP() {
        wd.findElement(By.linkText("home")).click();
    }

    protected void submit() {
        wd.findElement(By.name("submit")).click();
    }

    private void finishNewC() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    protected void fillContDate(ContactData contactDate) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactDate.getFirstname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactDate.getMiddlename());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactDate.getLastname());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactDate.getNickname());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactDate.getCompany());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactDate.getHomephone());
        wd.findElement(By.name("email2")).click();
        wd.findElement(By.name("email2")).clear();
        wd.findElement(By.name("email2")).sendKeys(contactDate.getEmail2());
    }

    protected void addNewContact() {
        WebElement e = wd.findElement(By.linkText("add new"));

        assertTrue(e != null, "No \"add new\" link found...");

        e.click();

    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }

   /* @AfterTest
    public void tearDown() {
        wd.quit();
    }*/
}
