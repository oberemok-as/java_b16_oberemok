package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeleteTest extends TestBase {

  @BeforeMethod
  public void enserePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("Admin"));
    }
  }
  @Test
  public void testGroupDelete() throws Exception {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size()-1);
    } else {
      Assert.assertEquals(after.size(), before.size());
    }
    before.remove(deletedGroup);
     Assert.assertEquals(before,after);

  }



}




