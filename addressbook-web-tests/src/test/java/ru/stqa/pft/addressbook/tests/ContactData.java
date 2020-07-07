package ru.stqa.pft.addressbook.tests;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String mobile;
  private final String email;
  private final String address2;
  private final String bday;
  private final String bmonth;
  private final String byear;

  public ContactData(String firstname, String middlename, String lastname, String mobile, String email, String address2, String bday, String bmonth, String byear) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.address2 = address2;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress2() {
    return address2;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }
}
