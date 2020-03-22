package com.corona.insights.client;


import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileContainerClient {

    public List<File> getFilesToProcess();

    public File markFileForProcessing(File file) throws IOException;

    public void deleteFile(File file);

}
