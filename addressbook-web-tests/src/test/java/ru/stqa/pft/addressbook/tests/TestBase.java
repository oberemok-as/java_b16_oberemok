package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.AplicationManager;

public class TestBase {

  protected final AplicationManager app = new AplicationManager(BrowserType.FIREFOX);

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();

  }



  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
