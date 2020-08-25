package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Testerov")
            .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
            .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin");
    app.goTo().addNew();
    app.contact().create((contact), true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }


}