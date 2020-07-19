package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeleteTestCase extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("Admin", "GroupAdmin", "administration"));
    }
    app.getGroupHelper().select_group();
    app.getGroupHelper().delete_group();
    app.getGroupHelper().returnToCreatedPage();
    int after = app.getGroupHelper().getGroupCount();
    if (before!=0) {
      Assert.assertEquals(after, before - 1);
    } else {
      Assert.assertEquals(after, before);
    }
  }

}




