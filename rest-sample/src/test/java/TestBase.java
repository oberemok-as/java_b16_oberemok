import org.testng.SkipException;

import java.io.IOException;

public class TestBase {



private boolean isIssueOpen(int issueId) throws IOException {
    String issueState = RestHelper.getStateIssueById(issueId);
    return !issueState.equals("Closed");
  }



  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
