package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;
import ru.stqa.pft.mantis.model.Status;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoupHelper {
  private ApplicationManager app;

  public SoupHelper(ApplicationManager app){
    this.app = app;
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
    return Arrays.asList(projects).stream()
            .map((p)->new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public Status issueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ObjectRef issueById = mc.mc_issue_get(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId)).getStatus();
    return new Status().withId(issueById.getId().intValue()).withName(issueById.getName());
  }


  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("soap.URL")));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin")
            , app.getProperty("web.adminPassword")
            , BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getId()),issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueID = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
    IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueID);
    return new Issue()
            .withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project()
                    .withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }
}
