package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroup extends TestBase {
  @BeforeMethod
  public void isGroup() {
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Admin").withHeader("newHeader").withFooter("newFooter"));
    }
  }
  @Test
  public void testAddContactInGroup(){
    app.goTo().home();
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    String selectedGroup = app.db().groups().iterator().next().getName();

    app.contact().selectContactById(contact.getId());
    app.contact().selestGroup(selectedGroup);
    app.contact().submitAddTo();
    app.contact().returnHomeWithFilterByGroup(selectedGroup);

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));

    //assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
