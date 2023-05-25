package com.example.springworksheetdemo.service;

import com.example.springworksheetdemo.dto.ImportResult;
import com.example.springworksheetdemo.sheet.SheetRowData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportSheetService {

    <T,E extends SheetRowData> ImportResult importFile(MultipartFile multipartFile,
                                                       String sheetName,
                                                       Class<E> sheetRowData,
                                                       Class<T> objectType) throws IOException;
}
