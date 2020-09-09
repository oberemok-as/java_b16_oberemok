package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTest extends TestBase {

 @BeforeMethod
  public void ensureContactPreconditions(){
    if (app.db().contacts().size()==0){
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Tester").withLastname("Testor").withMiddlename("Tester")
              .withMobile("9269269269").withEmail("926@mail.ru").withAddress("100100 Sas str 18 18")
              .withBday("10").withBmonth("May").withByear( "1989"),true);
    }
  }



  @Test
  public void testUntitledTestCase() throws Exception {
    Contacts before=app.db().contacts();
    ContactData delCotnact=before.iterator().next();
    app.goTo().home();
    app.contact().delete(delCotnact);
    app.goTo().home();
      Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after=app.db().contacts();
    assertThat(after, equalTo(before.withOut(delCotnact)));
    verifyContactListInUI();
  }




}
