package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTest extends TestBase {

  @BeforeMethod
  public void enserePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size()==0){
      app.group().create(new GroupData("Admin", "GroupAdmin", "administration"));
    }
  }
  @Test
  public void testGroupDelete() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size()-1);
    } else {
      Assert.assertEquals(after.size(), before.size());
    }
    before.remove(index);
     Assert.assertEquals(before,after);

  }



}




