package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase{

  @Test
          public void testContactModification () {
    app.getNavigationHelper().gotoHome();

    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().linkAddNew();
      app.getContactHelper().createContact(new ContactData("Tester", "Testor", "Tester",
              null,"9269269269", "926@mail.ru", "100100 Sas str 18 18",
              "10", "May", "1989","Admin"),true);
    }
    List<ContactData> before=app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().editContact();
    ContactData contact =new ContactData(before.get(before.size()-1).getId(),"Tester", "Testor", "Testerov",
            "000000, Тестеровая ул. 0","9269269269", null, "", null,
            null, null,null);
    app.getContactHelper().fillForm(contact,false);
    app.getContactHelper().submitUpdate();
    app.getContactHelper().returnHome();
    List<ContactData> after=app.getContactHelper().getContactList();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() );
    } else {
      Assert.assertEquals(after.size(), before.size()+1);
    }
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId=(c1,c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }


}
