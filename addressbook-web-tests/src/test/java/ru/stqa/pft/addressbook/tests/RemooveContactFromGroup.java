package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemooveContactFromGroup extends TestBase {
  @BeforeMethod
  public void isContact() {
    if (app.db().contacts().size()==0) {
      app.goTo().addNew();
      File photo=new File("src/test/resources/pp.jpg");
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withPhoto(photo),true);
    }
  }
  @BeforeMethod
  public void isGroup() {
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Admin").withHeader("newHeader").withFooter("newFooter"));
    }
  }
  @Test
  public void testRemooveContactFromGroup(){
    GroupData selectedGroup = app.db().groups().iterator().next();
    if (selectedGroup.getContacts().size() == 0){
      app.goTo().addNew();
      File photo=new File("src/test/resources/pp.jpg");
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withPhoto(photo).inGroups(selectedGroup),true);
    }
    String selectedGroupName = selectedGroup.getName();
    int selectedGroupID = selectedGroup.getId();
    Contacts contacts = selectedGroup.getContacts();
    ContactData contact = contacts.iterator().next();
    Groups before = contact.getGroups();

    app.contact().urlHomeWithFilterByGroup(selectedGroupID);
    app.contact().selectContactById(contact.getId());
    app.contact().submitRemoove();
    app.contact().returnHomeWithFilterByGroup(selectedGroupName);
    before.remove(selectedGroup);
    Groups after = contact.getGroups();
    System.out.println(after);
    assertThat(after, equalTo(before));

  }
}
