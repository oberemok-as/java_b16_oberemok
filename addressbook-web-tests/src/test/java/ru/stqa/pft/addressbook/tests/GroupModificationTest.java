package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase{

  @BeforeMethod
  public void enserePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("Admin"));
    }
  }

  @Test
  public void testGroupModification (){
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("Admin").withHeader( "Administration");
    app.group().modify(group);
    Set<GroupData> after =  app.group().all();
        if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() );
    } else {
      Assert.assertEquals(after.size(), before.size()+1);
    }
    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }


}
