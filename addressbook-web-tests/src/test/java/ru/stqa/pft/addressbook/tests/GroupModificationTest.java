package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase{

  @BeforeMethod
  public void enserePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size()==0){
      app.group().create(new GroupData().withName("Admin"));
    }
  }

  @Test
  public void testGroupModification (){

    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData().withId(before.get(index).getId()).withName("Admin").withHeader( "Administration");

    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    if (before.size()!=0) {
      Assert.assertEquals(after.size(), before.size() );
    } else {
      Assert.assertEquals(after.size(), before.size()+1);
    }
    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId=(c1,c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
