package com.corona.insights.client;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

public interface GitClient {

    public boolean existsLocally();

    public void clone(String branch) throws GitAPIException;

    public void create(String repoName, String directory) throws IOException;

    public void pull() throws GitAPIException, IOException;

    public void push();

    public void push(String branchName);

    public void pull(String branchName);

}
