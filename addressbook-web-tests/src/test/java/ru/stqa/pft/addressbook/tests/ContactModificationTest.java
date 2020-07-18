package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test
          public void testContactModification () {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillForm(new ContactData(null, null, null,
            "000000, Тестеровая ул. 0","9269269269", null, "", null,
            null, null,null),false);
    app.getContactHelper().submitUpdate();
    app.getContactHelper().returnHome();


  }

}
