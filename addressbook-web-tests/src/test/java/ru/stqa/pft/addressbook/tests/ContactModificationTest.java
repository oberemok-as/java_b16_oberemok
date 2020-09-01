package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (app.contact().all().size()==0) {
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin"),true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modCotnact=before.iterator().next();
    ContactData contact = new ContactData().withId(modCotnact.getId()).withFirstname("Tester")
            .withLastname("Testor").withMiddlename("Testerov").withAddress("000000, Тестеровая ул. 0").withMobile("9269269269");
    app.contact().modify(contact);

    if (before.size() != 0) {
      Assert.assertEquals(app.contact().count(), before.size());
    } else {
      Assert.assertEquals(app.contact().count(), before.size() + 1);
    }
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(modCotnact).withAdded(contact)));
  }

}



