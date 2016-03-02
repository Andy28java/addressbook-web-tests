package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.kurs.addressbook.model.GroupeData;

/**
 * Created by yana on 3/1/2016.
 */
public class GpoupHelper extends HelperBase {

    public GpoupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitGroupeCreation() {
        click(By.name("submit"));
    }

    public void fillGroupeForm(GroupeData groupeData) {
        type(By.name("group_name"), groupeData.getName());
        type(By.name("group_header"), groupeData.getHeader());
        type(By.name("group_footer"), groupeData.getFooter());
    }

    public void initGoupeCreation() {
        click(By.name("group_name"));
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void DeleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupe() {
        click(By.name("selected[]"));
    }
}
