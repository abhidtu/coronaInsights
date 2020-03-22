package com.corona.insights.scheduler;

import com.corona.insights.client.GitClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class GitScheduler {

    @Value("${git.repo.branch}")
    private String branch;

    private GitClient gitClient;

    public GitScheduler(GitClient gitClient) {
        this.gitClient = gitClient;
    }

    //@Scheduled(cron = "0 * * ? * *")
    public void pollForRepo() {
        try {
            log.info("Executing the repository poll scheduler");
            cloneOrUpdate();
        } catch (Exception e) {
            log.info("Error updating the repository");
        }
    }

    private void cloneOrUpdate() throws GitAPIException, IOException {
        if(!gitClient.existsLocally()) {
            log.info("Repository exists locally");
            gitClient.clone(branch);
        }else {
            log.info("Repository already exists will update");
            gitClient.pull();
        }
    }

}