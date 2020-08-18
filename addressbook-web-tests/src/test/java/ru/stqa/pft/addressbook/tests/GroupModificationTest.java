package ru.stqa.pft.addressbook.tests;

import io.netty.util.internal.ConstantTimeUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static io.netty.util.internal.ConstantTimeUtils.equalsConstantTime;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("Admin").withHeader( "Administration");
    app.group().modify(group);
    if (before.size()!=0) {
          assertThat(app.group().count(), CoreMatchers.equalTo(before.size() ) );
    } else {
          assertThat(app.group().count(), CoreMatchers.equalTo(before.size() + 1));
    }
    Groups after =  app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
