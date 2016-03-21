package ru.kurs.addressbook.appmanager;

import javafx.application.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kurs.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yana on 3/1/2016.
 */
public class GroupHelper extends HelperBase {
    private ApplicationManager app;
    public GroupHelper(ApplicationManager a, WebDriver wd) {
        super(wd);
        app = a;
    }

    public void submitGroupeCreation() {
        click(By.name("submit"));
    }

    public void fillGroupeForm(GroupData groupeData) {
        type(By.name("group_name"), groupeData.getName());
        type(By.name("group_header"), groupeData.getHeader());
        type(By.name("group_footer"), groupeData.getFooter());
    }

    //
    public void create(String name, String header, String footer) {
        create(new GroupData().withName(name).withHeader(header).withFooter(footer));
    }

    public void create(GroupData gd)
    {
        wd.findElement(By.name("new")).click();
        initGoupeCreation();
        fillGroupeForm(gd);
        submitGroupeCreation();
    }

    public void modify(int index, GroupData group) {
        selectGroup(index);
        initGroupeModification();
        // GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
        fillGroupeForm(group); //"1", "11"));
        submitGroupeModification();
        app.goTo().groupPage();
    }
    public void delete(int index) {
        selectGroup(index);
        DeleteSelectedGroups();
        app.goTo().groupPage();
    }

    public void initGoupeCreation() {
         click(By.name("group_name"));
    }

    public void DeleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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
    public int getGroupCount() {
       return wd.findElements(By.name("selected[]")).size();
    }


    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
