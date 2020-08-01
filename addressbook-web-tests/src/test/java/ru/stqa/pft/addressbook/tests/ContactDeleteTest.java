package ru.stqa.pft.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().home();
    if (app.contact().list().size()==0){
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989").withGroup("Admin"),true);
    }
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    List<ContactData> before=app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().home();
    List<ContactData> after=app.contact().list();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() - 1);
    } else {
      Assert.assertEquals(after.size(), before);
    }

    before.remove(index);
      Assert.assertEquals(before,after);

  }




}
