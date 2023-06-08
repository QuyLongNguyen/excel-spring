package com.example.springworksheetdemo.resources;

import com.example.springworksheetdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/files")
public class FileResource {

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<InputStreamResource> getFile(@RequestParam("fileName") String fileName) {
        InputStream inputStream;
        HttpHeaders httpHeaders;

        try {
            inputStream = fileService.getResourceFile(fileName);
        }catch (IOException e){
            return ResponseEntity.notFound().build();
        }
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(inputStream));
    }
}
