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
      app.contact().create(new ContactData("Tester", "Testor", "Tester",
              null,"9269269269", "926@mail.ru", "100100 Sas str 18 18",
              "10", "May", "1989","Admin"),true);
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
