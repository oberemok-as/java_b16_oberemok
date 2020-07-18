package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().linkAddNew();
    app.getContactHelper().fillForm(new ContactData("Tester", "Testor", "Tester",
            null,"9269269269", "926@mail.ru", "100100 Sas str 18 18",
            "10", "May", "1989","Admin"),true);
    app.getContactHelper().submitContact();
    app.getContactHelper().returnHome();
      }


}