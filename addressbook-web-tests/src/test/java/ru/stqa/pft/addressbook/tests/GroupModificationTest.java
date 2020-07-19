package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{

  @Test
  public void testGroupModification (){
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("Admin", "GroupAdmin", "administration"));
    }
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Admin", "Administration", null));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToCreatedPage();
    int after = app.getGroupHelper().getGroupCount();
    if (before!=0) {
      Assert.assertEquals(after, before );
    } else {
      Assert.assertEquals(after, before+1);
    }
  }
}
