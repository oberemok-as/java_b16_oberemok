package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{



  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void return_home() {
    click(By.linkText("home page"));
  }

  public void submit_contact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fill_form(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    date(By.name("bday"), contactData.getBday());
    date(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"), contactData.getAddress2());
  }


  public void selectContact() {
    click(By.name("selected[]"));
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

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }


  public void fillFormForChange(String s) {

  }
}
