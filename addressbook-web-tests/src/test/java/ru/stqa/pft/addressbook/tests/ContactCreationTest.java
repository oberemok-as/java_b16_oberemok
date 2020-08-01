package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before=app.contact().list();
    ContactData contact = new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Testerov")
            .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
            .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin");
    app.goTo().addNew();
    app.contact().create((contact), true);
    List<ContactData> after=app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId=(c1, c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}