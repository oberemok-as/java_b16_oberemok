package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTestCase extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
           if (! app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("Admin", "GroupAdmin", "administration"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().delete_group();
    app.getGroupHelper().returnToCreatedPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() - 1);
    } else {
      Assert.assertEquals(after.size(), before.size());
    }
    before.remove(before.size()-1);
     Assert.assertEquals(before,after);
    
  }

}




