package com.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainScreenController {

    //Просто выводит информацию о том, по какому адресу можно првести операции
    @GetMapping("/")
    public ResponseEntity list() {
        return new ResponseEntity("Main operations with the file occur under the \"/files\" link", HttpStatus.OK);
    }
}
