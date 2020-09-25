package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemooveFromGroup extends TestBase {
  @BeforeMethod
  public void isContact() {
    if (app.db().contacts().size() == 0) {
      app.goTo().addNew();
      File photo = new File("src/test/resources/pp.jpg");
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear("1989").withPhoto(photo), true);
    }
  }

  @BeforeMethod
  public void isGroup() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Admin").withHeader("newHeader").withFooter("newFooter"));
    }
  }

  @Test
  public void testRemooveContactFromGroup() {
    Groups groups = app.db().groups();
    GroupData selectedGroup = groups.iterator().next();
    Contacts before = selectedGroup.getContacts();
    int selectedGroupId = selectedGroup.getId();
    String selectedGroupName = selectedGroup.getName();
    ContactData contact;
    Contacts contacts = selectedGroup.getContacts();
    if (contacts.size() == 0) {
      contact = app.db().contacts().iterator().next();
      app.contact().selectContactById(contact.getId());
      app.contact().selectGroup(selectedGroupId);
      app.contact().submitAddTo();
      app.contact().returnHomeWithFilterByGroup(selectedGroupName);
    }
    GroupData groupById = app.db().groups().stream().filter(a -> Objects.equals(selectedGroupId, a.getId())).findFirst().orElse(null);
    ContactData selectedContact = groupById.getContacts().iterator().next();
    app.contact().urlHomeWithFilterByGroup(selectedGroupId);
    app.contact().selectContactById(selectedContact.getId());
    app.contact().submitRemoove();
    app.contact().returnHomeWithFilterByGroup(selectedGroupName);
    GroupData groupById2 = app.db().groups().stream().filter(a -> Objects.equals(selectedGroupId, a.getId())).findFirst().orElse(null);
    Contacts after = groupById2.getContacts();
    before.remove(selectedContact);
    assertThat(after, equalTo(before));

  }
}

