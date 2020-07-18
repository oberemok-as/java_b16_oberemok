package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String mobile;
  private final String email;
  private final String address2;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private String group;

  public ContactData(String firstname, String middlename, String lastname, String address, String mobile, String email, String address2, String bday, String bmonth, String byear, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
    this.address2 = address2;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group;
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
  public String getAddress() {
    return address;
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

  public String getGroup() {
    return group;
  }
}
