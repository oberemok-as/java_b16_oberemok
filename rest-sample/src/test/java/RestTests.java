import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(285);
    Set<Issue> oldIssues = RestHelper.getIssues();
    Issue newIssue = new Issue()
            .withSubject("Test issue")
            .withDescription("New test issue");
    int issueId = RestHelper.createIssue(newIssue);
    Set<Issue> newIssues = RestHelper.getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}
