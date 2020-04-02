package com.corona.insights.configuration;

import com.corona.insights.client.BashFileContainerClient;
import com.corona.insights.client.FileContainerClient;
import com.corona.insights.client.JGitClient;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class Configuration {

    @Value("${git.repo.url}")
    private String repoUrl;

    @Value("${repo.destination.path}")
    private String destinationPath;

    @Value("${file.container.base.path}")
    private String fileContainerBasePath;

    @Value("${incoming.folder.name}")
    private String incomingFolder;

    @Value("${processing.folder.name}")
    private String processingFolder;

    @Value("${archive.folder}")
    private String archiveFolder;

    @Value("${source.folder.path}")
    private String sourceFolderPath;

    @Bean
    JGitClient getGitClient() {
        return new JGitClient(repoUrl, destinationPath);
    }

    @Bean
    FileContainerClient getFileContainerClient() {
        return new BashFileContainerClient(sourceFolderPath, fileContainerBasePath, incomingFolder, processingFolder, archiveFolder);
    }


}
