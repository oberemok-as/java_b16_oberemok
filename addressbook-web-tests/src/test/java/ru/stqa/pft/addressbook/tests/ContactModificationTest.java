package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensureContactPreconditions() {
    if (app.db().contacts().size()==0) {
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin"),true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modCotnact=before.iterator().next();
    File photo = new File("src/test/resources/pp.jpg");
    ContactData contact = new ContactData().withId(modCotnact.getId()).withFirstname("Tester")
            .withLastname("Testor").withMiddlename("Testerov").withAddress("000000, Тестеровая ул. 0").withMobile("9269269269").withPhoto(photo);
    app.contact().modify(contact);
      Assert.assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modCotnact).withAdded(contact)));
  }

}



