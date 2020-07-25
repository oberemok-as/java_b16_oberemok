package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before=app.getContactHelper().getContactList();
    app.getNavigationHelper().linkAddNew();
    app.getContactHelper().createContact(new ContactData("Tester", "Testor", "Testerov",
            null, "9269269269", "926@mail.ru", "100100 Sas str 18 18",
            "10", "May", "1989", "Admin"), true);
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }


}