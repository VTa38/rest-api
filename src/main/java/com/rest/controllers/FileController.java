package com.rest.controllers;


import com.rest.exception.IllegalStructureException;
import com.rest.model.FileAnalyzer;
import com.rest.model.FileParser;
import com.rest.model.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private FileParser parser;
    private FileAnalyzer analyzer;

    @Autowired
    public FileController(FileParser parser, FileAnalyzer analyzer) {
        this.parser = parser;
        this.analyzer = analyzer;
    }

    // Загрузка файла для анализа и преобразования, сразу выводит структуру
    @PostMapping("/files")
    public ResponseEntity loadFile(@RequestPart MultipartFile file) {

        if (!file.getOriginalFilename().endsWith(".txt")) {
            return new ResponseEntity("Text must have .txt format", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(file.getOriginalFilename());
        response.setFileSize(file.getSize());

        try {
            response.setStructure(analyzer.startProcessing(parser.readFile(file)));
        } catch (IllegalStructureException e) {
            return new ResponseEntity("Invalid file structure", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
