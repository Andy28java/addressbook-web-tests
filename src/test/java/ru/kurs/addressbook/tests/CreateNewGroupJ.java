package ru.kurs.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.kurs.addressbook.model.GroupData;
import ru.kurs.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewGroupJ extends TestBase {
    //Logger logger = LoggerFactory.getLogger(CreateNewGroupJ.class);

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
       try( BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))){
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return  groups.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
    }}

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")//(enabled = false)
    public void testCreateNewGroupJ(GroupData group) {
            app.goTo().groupPage();
            Groups before = app.db().groups();//app.group().all();
            app.group().create(group);
            app.goTo().groupPage();
            Groups after = app.db().groups();//app.group().all();
            assertThat(after.size(), equalTo(before.size() + 1));

            assertThat(after, equalTo(
                    before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
            verifyGroupListInUI();
        }
    }


