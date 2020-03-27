package com.corona.insights.client;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JGitClient implements GitClient {

    private Repository localRepo;
    private Git gitObj;
    private String repoUrl;
    private String destination;

    public JGitClient(String repoUrl, String destination) {
        this.repoUrl = repoUrl;
        this.destination = destination;
        try {
            this.localRepo = new FileRepository(destination + "/.git");
            this.gitObj = new Git(localRepo);
        } catch (IOException e) {
            log.error("Error building the repo, Exception = {}", e.getMessage());
        }
    }

    @Override
    public boolean existsLocally() {
        for (Ref ref : localRepo.getAllRefs().values()) {
            if (ref.getObjectId() == null)
                continue;
            return true;
        }
        return false;
    }

    @Override
    public void clone(String branch) throws GitAPIException {
        log.info("Cloning git repo with url = {} to destination = {}", repoUrl, destination);
        CloneCommand clone = Git.cloneRepository();
        clone.setURI(repoUrl).setDirectory(new File(destination)).setBranch(branch);
        clone.call().getRepository().close();
    }

    @Override
    public void create(String repoName, String directory) throws IOException {

    }

    @Override
    public void pull() throws GitAPIException, IOException {
        log.info("updating git repo = {}", localRepo);
        gitObj.getRepository().resolve("HEAD");
        gitObj = new Git(localRepo);
        gitObj.pull().call();
    }

    @Override
    public void push() {
    }

    @Override
    public void push(String branchName) {

    }

    @Override
    public void pull(String branchName) {

    }

}
