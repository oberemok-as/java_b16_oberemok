package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase{

  @Test
  public void testGroupModification (){
    app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("Admin", "GroupAdmin", "administration"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"Admin", "Administration", null);
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToCreatedPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() );
    } else {
      Assert.assertEquals(after.size(), before.size()+1);
    }
    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
