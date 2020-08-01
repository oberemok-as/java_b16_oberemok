package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (app.contact().list().size()==0) {
      app.goTo().addNew();
      app.contact().create(new ContactData("Tester", "Testor", "Tester",
              null, "9269269269", "926@mail.ru", "100100 Sas str 18 18",
              "10", "May", "1989", "Admin"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Tester", "Testor", "Testerov",
            "000000, Тестеровая ул. 0", "9269269269", null, "", null,
            null, null, null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    if (before.size() != 0) {
      Assert.assertEquals(after.size(), before.size());
    } else {
      Assert.assertEquals(after.size(), before.size() + 1);
    }
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}



