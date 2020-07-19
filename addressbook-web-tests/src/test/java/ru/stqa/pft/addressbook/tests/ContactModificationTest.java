package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test
          public void testContactModification () {
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().linkAddNew();
      app.getContactHelper().createContact(new ContactData("Tester", "Testor", "Tester",
              null,"9269269269", "926@mail.ru", "100100 Sas str 18 18",
              "10", "May", "1989","Admin"),true);
    }
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillForm(new ContactData(null, null, null,
            "000000, Тестеровая ул. 0","9269269269", null, "", null,
            null, null,null),false);
    app.getContactHelper().submitUpdate();
    app.getContactHelper().returnHome();
    int after = app.getContactHelper().getContactCount();
    if (before!=0) {
      Assert.assertEquals(after, before );
    } else {
      Assert.assertEquals(after, before+1);
    }
  }


}
