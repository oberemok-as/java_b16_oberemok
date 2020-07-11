package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactTestCase extends TestBase{

  @Test
  public void testContactTestCase() throws Exception {
    app.getNavigationHelper().link_add_new();
    app.getContactHelper().fill_form(new ContactData("Tester", "Testor", "Tester", "9269269269", "926@mail.ru", "100100 Sas str 18 18", "10", "May", "1989"));
    app.getContactHelper().submit_contact();
    app.getContactHelper().return_home();
      }


}