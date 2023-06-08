package com.example.springworksheetdemo.service.impl;

import com.example.springworksheetdemo.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public InputStream getResourceFile(String filename) throws IOException {
        return new ClassPathResource(filename).getInputStream();
    }
}
