package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.AplicationManager;

public class TestBase {

  protected static final AplicationManager app
          = new AplicationManager(System.getProperty("browser",BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }



}
