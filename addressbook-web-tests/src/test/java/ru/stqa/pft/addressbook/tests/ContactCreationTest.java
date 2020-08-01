package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before=app.contact().all();
    ContactData contact = new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Testerov")
            .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
            .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin");
    app.goTo().addNew();
    app.contact().create((contact), true);
    Set<ContactData> after=app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}