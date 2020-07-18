package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{

  @Test
  public void testGroupModification (){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().select_group();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Admin", "Administration", null));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToCreatedPage();
  }
}
