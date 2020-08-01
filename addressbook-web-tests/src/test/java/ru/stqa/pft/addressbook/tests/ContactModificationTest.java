package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (app.contact().list().size()==0) {
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin"),true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modCotnact=before.iterator().next();
    ContactData contact = new ContactData().withId(modCotnact.getId()).withFirstname("Tester")
            .withLastname("Testor").withMiddlename("Testerov").withAddress("000000, Тестеровая ул. 0").withMobile("9269269269");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    if (before.size() != 0) {
      Assert.assertEquals(after.size(), before.size());
    } else {
      Assert.assertEquals(after.size(), before.size() + 1);
    }
    before.remove(modCotnact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}



