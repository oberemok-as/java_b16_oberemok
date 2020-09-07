package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("Admin"));
    }
  }

  @Test
  public void testGroupModification (){
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("Admin").withHeader( "Administration").withFooter("adm");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(), CoreMatchers.equalTo(before.size()));
    Groups after =  app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }
}
