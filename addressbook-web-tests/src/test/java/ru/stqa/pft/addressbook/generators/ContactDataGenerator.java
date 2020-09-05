package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names="-n", description="Contact count")
  public int count;
  @Parameter(names="-m", description = "Target file")
  public String file;
  @Parameter(names="-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if(format.equals("csv")) {
      saveAsCSV(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXML(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJSON(contacts, new File(file));
    } else {
      System.out.println("Unregnized format" + format);
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/pp.jpg");
    for (int i=0;i<count;i++){
      contacts.add(new ContactData().withFirstname(String.format("name%s",i))
              .withLastname(String.format("lastname%s",i)).withMiddlename(String.format("middle%s",i))
              .withAddress(String.format("addres%s",i)).withMobile(String.format("999%s",i))
              .withEmail(String.format("email1@m.ru %s",i)).withEmail2(String.format("email2@m.ru %s",i))
              .withEmail3(String.format("email3@m.ru %s",i)).withGroup("Admin").withPhoto(photo))
      ;
    }
    return contacts;

  }

  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
Writer writer = new FileWriter(file);
for (ContactData contact:contacts){
  writer.write(String.format("%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),
          contact.getMiddlename(),contact.getAddress(),contact.getMobile()));
}
writer.close();
  }


}
