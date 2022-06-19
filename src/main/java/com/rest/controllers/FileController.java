package com.rest.controllers;


import com.rest.Models.FileAnalyzer;
import com.rest.Models.FileParser;
import com.rest.Models.FileUploadResponse;
import com.rest.exception.IllegalStructureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    // Возвращает преобразованную информацию из входного файла
    @GetMapping("/files")
    public ResponseEntity getNavigateData() {
        return new ResponseEntity(analyzer.getData(), HttpStatus.OK);
    }

    // Загрузка файла для анализа и преобразования
    @PostMapping("/files")
    public ResponseEntity loadFile(@RequestPart MultipartFile file) {
        parser.clearList();
        analyzer.clearList();

        if (!file.getOriginalFilename().endsWith(".txt")){
            return new ResponseEntity("Text must have .txt format", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        try{
            analyzer.startProcessing(parser.readFile(file));
        } catch (IllegalStructureException e){
            return new ResponseEntity("Invalid file structure", HttpStatus.BAD_REQUEST);
        }

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(file.getOriginalFilename());
        response.setFileSize(file.getSize());
        response.setDownloadURL("/files/");

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
