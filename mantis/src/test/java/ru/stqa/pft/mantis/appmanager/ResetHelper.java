package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

public class ResetHelper extends HelperBase {
  public ResetHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAdmin() {
    wd.get(app.getProperty("web.baseURL") + "login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    click(By.xpath("//form[@id='login-form']/fieldset/input[2]"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.xpath("//input[@type='submit']"));
  }

  public void selectUserForReset(int id) {
    wd.get(app.getProperty("web.baseURL") + "manage_user_page.php");
    wd.findElement(By.xpath(format("//a[contains(@href, 'manage_user_edit_page.php?user_id=%s')]", id))).click();
    click(By.xpath("//fieldset/span/input"));

  }

  public void workWithMessage(String email) throws IOException, MessagingException {
  List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
  String confirmationLink = findConfirmationLink(mailMessages, email);
    wd.get(confirmationLink);

}
  public String findConfirmationLink(List < MailMessage > mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void finish(String username) {
    String newpassword = "Password";
    type(By.name("realname"),username);
    type(By.name("password"),newpassword);
    type(By.name("password_confirm"),newpassword);
    click(By.xpath("//button[@type='submit']"));
  }


}
