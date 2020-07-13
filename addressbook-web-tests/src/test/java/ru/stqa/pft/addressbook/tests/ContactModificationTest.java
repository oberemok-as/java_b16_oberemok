package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase{

  @Test
          public void testContactModification () {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fill_form(new ContactData("Tester", "Testor", "Tester", "000000, Тестеровая ул. 0","9269269269", "926@mail.ru", "100100 Sas str 18 18", "10", "May", "1989"));
    app.getContactHelper().submitUpdate();
    app.getContactHelper().return_home();


  }

}