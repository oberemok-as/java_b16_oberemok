package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class GroupDeleteTestCase extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.gotoGroupPage();
    app.select_group();
    app.delete_group();
    app.returnToCreatedPage();
  }


}




