package ru.stqa.pfr.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("3e077401ac46bd491eec1b73db4f5bb576976693");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("oberemok-as", "java_b16_oberemok")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    };
  }
}
