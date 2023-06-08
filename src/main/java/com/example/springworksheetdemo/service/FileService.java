package com.example.springworksheetdemo.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    InputStream getResourceFile(String filename) throws IOException;
}
