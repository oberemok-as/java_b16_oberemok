package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeleteTest extends TestBase {
  @BeforeMethod
  public void ensureContactPreconditions(){
    app.goTo().home();
    if (app.contact().all().size()==0){
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin"),true);
    }
  }



  @Test
  public void testUntitledTestCase() throws Exception {
    Contacts before=app.contact().all();
    ContactData delCotnact=before.iterator().next();
    app.contact().delete(delCotnact);
    app.goTo().home();
    Contacts after=app.contact().all();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() - 1);
    } else {
      Assert.assertEquals(after.size(), before);
    }
    assertThat(after, equalTo(before.withOut(delCotnact)));

  }




}
