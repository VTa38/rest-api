package com.rest.model;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUploadResponse {
    // Содержит информацию о входном файле: имя, размер, ссылка на получение стрктуры файла

    private String fileName;
    private long fileSize;
    private List<NavigationDTO> structure;

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

    public List<NavigationDTO> getStructure() {
        return structure;
    }

    public void setStructure(List<NavigationDTO> structure) {
        this.structure = structure;
    }
}
