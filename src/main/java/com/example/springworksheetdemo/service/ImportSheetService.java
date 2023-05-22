package com.example.springworksheetdemo.service;

import com.example.springworksheetdemo.dto.ImportSheetResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportSheetService {

    ImportSheetResult uploadFile(MultipartFile multipartFile) throws IOException;
}
