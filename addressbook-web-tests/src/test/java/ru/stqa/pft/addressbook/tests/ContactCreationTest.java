package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before=app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Tester", "Testor", "Testerov",
            null, "9269269269", "926@mail.ru", "100100 Sas str 18 18",
            "10", "May", "1989", "Admin");
    app.getNavigationHelper().linkAddNew();
    app.getContactHelper().createContact((contact), true);
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    int max = 0;
    for (ContactData c : after){
      if (c.getId() >max){
        max=c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }


}