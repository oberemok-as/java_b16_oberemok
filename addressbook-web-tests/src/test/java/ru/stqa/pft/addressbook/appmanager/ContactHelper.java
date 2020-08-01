package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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


  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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
    returnHome();
  }

  public boolean isThereContact() {
return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void modify(int index, ContactData contact) {
    selectContact(index);
    editContact(index);
    fillForm(contact,false);
    submitUpdate();
    returnHome();
  }
  public void delete(int index) {
    selectContact(index);
    deleteContact();
    closeAlert();

  }

  public List<ContactData> list() {
     List<ContactData> contacts=new ArrayList<ContactData>();
       List<WebElement> elements=wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
            for (WebElement element : elements){
       List<WebElement> cells = element.findElements(By.tagName("td"));
       String firstname = cells.get(2).getText();
       String lastname = cells.get(1).getText();
       int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
       ContactData contact = new ContactData(id,firstname,null,lastname,null,null,null,null,null,null,null,null);
     contacts.add(contact);
     }
    return contacts;
  }
}
