package com.example.springworksheetdemo.service.impl;

import com.example.springworksheetdemo.dto.ImportError;
import com.example.springworksheetdemo.dto.ImportResult;
import com.example.springworksheetdemo.service.SheetService;
import com.example.springworksheetdemo.sheet.SheetRowData;
import com.example.springworksheetdemo.utils.WorkbookUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class SheetServiceImpl implements SheetService {

    @Override
    public <T, R extends SheetRowData> ImportResult<T> importFile(MultipartFile multipartFile, String sheetName, Class<R> sheetRowData, Class<T> objectType) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream())) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            List<ImportError> errors = new LinkedList<>();
            List<T> importData = new LinkedList<>();

            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }

                T dataObject;
                try {
                    dataObject = objectType.getConstructor().newInstance();
                } catch (Exception e) {
                    return new ImportResult<>(e.getMessage(), null, null);
                }

                for (SheetRowData cell : sheetRowData.getEnumConstants()) {
                    Object value;

                    if (!isValidateCell(errors, sheet, cell,
                            row.getRowNum() + 1, cell.getColumnIndex())) {
                        continue;
                    }

                    try {
                        value = WorkbookUtils.getValueAt(sheet,
                                row.getRowNum() + 1,
                                cell.getColumnIndex(),
                                cell.getFieldType());
                    } catch (IllegalStateException e) {
                        ImportError importError = new ImportError();
                        importError.setSheet(sheetName);
                        importError.setAddress(cell.getColumnCharacter() + row.getRowNum() + 1);
                        importError.setDetail(String.format("%s must be %s", cell.getCellName(), cell.getFieldType().getSimpleName()));
                        errors.add(importError);
                        continue;
                    }

                    try {
                        Field field = objectType.getDeclaredField(cell.getFieldName());
                        field.setAccessible(true);
                        field.set(dataObject, value);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                importData.add(dataObject);
            }
            if (errors.isEmpty()) {
                return new ImportResult<>("Upload success", errors, importData);
            }
            return new ImportResult<>("Upload failed", errors, Collections.emptyList());
        }

    }

    @Override
    public <T, E extends SheetRowData> ByteArrayInputStream exportFile(InputStream inputStream,
                                                                       String sheetName,
                                                                       Class<E> sheetRowData,
                                                                       Iterable<T> data) throws IOException {

        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowIdx = 1;
            for (T row: data){
                for (SheetRowData cell : sheetRowData.getEnumConstants()) {
                    Field field;
                    Object value;

                    try {
                        field = row.getClass().getDeclaredField(cell.getFieldName());
                        field.setAccessible(true);
                        value = field.get(row);
                    }catch (NoSuchFieldException | IllegalAccessException e){
                        e.printStackTrace();
                        continue;
                    }
                    WorkbookUtils.setValueAt(sheet, rowIdx, cell.getColumnIndex(), cell.getFieldType(), value);
                }
                rowIdx++;
            }
            workbook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
    }

    private boolean isValidateCell(List<ImportError> importErrors, Sheet sheet, SheetRowData cell, int rowIndex, int columnIndex) {

        if (cell.isRequired() && WorkbookUtils.isEmptyCell(sheet, rowIndex, columnIndex)) {
            ImportError importError = new ImportError();
            importError.setSheet(sheet.getSheetName());
            importError.setAddress(cell.getColumnCharacter() + rowIndex);
            importError.setDetail(String.format("%s is required", cell.getCellName()));
            importErrors.add(importError);
            return false;
        }
        return true;

    }
}
