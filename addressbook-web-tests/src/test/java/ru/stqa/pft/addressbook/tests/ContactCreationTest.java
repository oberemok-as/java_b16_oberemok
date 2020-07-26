package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

  import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before=app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Tester", "Testor", "Testerov",
            null, "9269269269", "926@mail.ru", "100100 Sas str 18 18",
            "10", "May", "1989", "Admin");
    app.getNavigationHelper().linkAddNew();
    app.getContactHelper().createContact((contact), true);
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }


}