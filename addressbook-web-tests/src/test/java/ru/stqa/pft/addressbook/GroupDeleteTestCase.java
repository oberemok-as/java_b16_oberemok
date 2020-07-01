package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeleteTestCase extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    gotoGroupPage();
    select_group();
    delete_group();
    returnToCreatedPage();
  }


}


  

