package com.rest.Models;

import com.rest.exception.IllegalStructureException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileParser {

    private List<String> listStr = new ArrayList<>();

    public List<String> readFile(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            while (reader.ready()){
                listStr.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listStr;
    }

    public void clearList(){
        listStr = new ArrayList<>();
    }
}
