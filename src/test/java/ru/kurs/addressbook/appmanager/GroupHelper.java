package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.kurs.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by yana on 3/1/2016.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupeCreation() {
        click(By.name("submit"));
    }

    public void fillGroupeForm(GroupData groupeData) {
        type(By.name("group_name"), groupeData.getName());
        type(By.name("group_header"), groupeData.getHeader());
        type(By.name("group_footer"), groupeData.getFooter());
    }

    public void createGroup(String name, String header, String footer) {
        wd.findElement(By.name("new")).click();
        initGoupeCreation();
        fillGroupeForm(new GroupData(name, header, footer));
        submitGroupeCreation();
            }
    public void initGoupeCreation() {
         click(By.name("group_name"));
    }

    public void DeleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupeModification() {
        click(By.name("edit"));
    }

    public void submitGroupeModification() {
        click(By.name("update"));
    }

    public boolean hasGroups() {
        By b = By.xpath("/html/body/div/div[4]/form/span[@class='group']");

        List<WebElement> entries =  wd.findElements(b);
        if (entries == null || entries.isEmpty()) {
            return false;
        }
        return true;
    }
}
