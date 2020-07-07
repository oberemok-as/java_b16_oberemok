package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTestCase extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().select_group();
    app.getGroupHelper().delete_group();
    app.getGroupHelper().returnToCreatedPage();
  }


}




