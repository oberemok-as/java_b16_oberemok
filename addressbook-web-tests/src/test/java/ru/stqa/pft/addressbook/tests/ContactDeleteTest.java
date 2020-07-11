package ru.stqa.pft.addressbook.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactDeleteTest extends TestBase {
  //private boolean acceptNextAlert = true;

  @Test
  public void testUntitledTestCase() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
   // acceptNextAlert = true;
    app.getContactHelper().closeAlert();

  }


}
