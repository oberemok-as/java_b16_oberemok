package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().linkAddNew();
    app.getContactHelper().createContact(new ContactData("Tester", "Testor", "Tester",
            null, "9269269269", "926@mail.ru", "100100 Sas str 18 18",
            "10", "May", "1989", "Admin"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }


}