package com.rest.model;

import org.springframework.stereotype.Service;

@Service
public class FileUploadResponse {
    // Содержит информацию о входном файле: имя, размер, ссылка на получение стрктуры файла

    private String fileName;
    private long fileSize;
    private String structureURL;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStructureURL() {
        return structureURL;
    }

    public void setStructureURL(String structureURL) {
        this.structureURL = structureURL;
    }
}
