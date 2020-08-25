package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnHome() {
    click(By.linkText("home page"));
  }

  public void submitContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    date(By.name("bday"), contactData.getBday());
    date(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"), contactData.getAddress2());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void submitUpdate() {
    click(By.name("update"));
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void create(ContactData contact, boolean creation) {
    fillForm(contact, true);
    submitContact();
    contactCache=null;
    returnHome();
  }

  public boolean isThereContact() {
return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    editContact(contact.getId());
    fillForm(contact,false);
    submitUpdate();
    contactCache=null;
    returnHome();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactCache=null;
    closeAlert();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache!=null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements=wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
     }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    return null;
  }
}
