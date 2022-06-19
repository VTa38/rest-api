package com.rest.Models;

import org.springframework.stereotype.Service;

@Service
public class FileUploadResponse {
    // Содержит информацию о входном файле: имя, размер, ссылка на скачивание преобразованного файла

    private String fileName;
    private long fileSize;
    private String downloadURL;

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

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
