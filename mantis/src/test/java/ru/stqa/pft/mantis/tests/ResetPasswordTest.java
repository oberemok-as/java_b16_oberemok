package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }
  @Test
  public void testResetPassword() throws IOException, MessagingException {
    UserData user = app.db().users().iterator().next();
    int id = user.getId();
    String email = user.getEmail();
    String username = user.getUsername();

    app.reset().loginAsAdmin();
    app.reset().selectUserForReset(id);
    app.reset().workWithMessage(email);
    app.reset().finish(username);

    assertTrue(app.newSession().login(user.getUsername(), "Password"));
  }



  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();}
}
