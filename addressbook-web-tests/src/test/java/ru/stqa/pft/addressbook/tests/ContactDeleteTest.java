package ru.stqa.pft.addressbook.tests;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {
  //private boolean acceptNextAlert = true;

  @Test
  public void testUntitledTestCase() throws Exception {
    app.getNavigationHelper().gotoHome();

    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().linkAddNew();
      app.getContactHelper().createContact(new ContactData("Tester", "Testor", "Tester",
              null,"9269269269", "926@mail.ru", "100100 Sas str 18 18",
              "10", "May", "1989","Admin"),true);
    }
    List<ContactData> before=app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContact();
   // acceptNextAlert = true;
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after=app.getContactHelper().getContactList();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() - 1);
    } else {
      Assert.assertEquals(after.size(), before);
    }

    before.remove(before.size() - 1);
      Assert.assertEquals(before,after);

  }


}
