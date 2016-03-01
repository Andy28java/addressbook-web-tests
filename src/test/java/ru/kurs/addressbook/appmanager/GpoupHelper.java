package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.kurs.addressbook.model.GroupeData;

/**
 * Created by yana on 3/1/2016.
 */
public class GpoupHelper {
    private FirefoxDriver wd;

    public GpoupHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void submitGroupeCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGroupeForm(GroupeData groupeData) {
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupeData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupeData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupeData.getFooter());
    }

    public void initGoupeCreation() {
        wd.findElement(By.name("group_name")).click();
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void DeleteSelectedGroups() {
        wd.findElement(By.name("delete")).click();
    }

    public void selectGroupe() {
        wd.findElement(By.name("selected[]")).click();
    }
}
