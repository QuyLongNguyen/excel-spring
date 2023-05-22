package com.example.springworksheetdemo.service.impl;

import com.example.springworksheetdemo.dto.ImportSheetError;
import com.example.springworksheetdemo.dto.ImportSheetResult;
import com.example.springworksheetdemo.entities.Candidate;
import com.example.springworksheetdemo.service.CandidateService;
import com.example.springworksheetdemo.service.ImportSheetService;
import com.example.springworksheetdemo.sheet.CandidateSheetCell;
import com.example.springworksheetdemo.sheet.DataRowSheet;
import com.example.springworksheetdemo.utils.WorkbookUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImportSheetServiceImpl implements ImportSheetService {

    @Autowired
    private CandidateService candidateService;

    @Override
    public ImportSheetResult uploadFile(MultipartFile multipartFile) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream())) {

            XSSFSheet candidateSheet = workbook.getSheet("Candidate");
            List<ImportSheetError> importSheetErrors = new LinkedList<>();
            List<Candidate> candidates = new LinkedList<>();

            Iterator<Row> iterator = candidateSheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (row.getRowNum() < 1) {
                    continue;
                }
                Candidate candidate = new Candidate();
                for (CandidateSheetCell cell : CandidateSheetCell.values()) {
                    try {
                        if (!isValidateCell(importSheetErrors, candidateSheet, cell,
                                    row.getRowNum() + 1, cell.getColumnIndex())) {
                            continue;
                        }
                        Object value = WorkbookUtils.getValueAt(candidateSheet,
                                row.getRowNum() + 1,
                                cell.getColumnIndex(),
                                cell.getFieldType());
                        Field field = Candidate.class.getDeclaredField(cell.getFieldName());
                        field.setAccessible(true);
                        field.set(candidate, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                candidates.add(candidate);
            }
            if(importSheetErrors.isEmpty()){
                candidateService.saveAll(candidates);
                return new ImportSheetResult("Upload success", importSheetErrors);
            }
            return new ImportSheetResult("Upload failed", importSheetErrors);
        }

    }

    private boolean isValidateCell(List<ImportSheetError> importSheetErrors, Sheet sheet, DataRowSheet cell, int rowIndex, int columnIndex) {

//        Object value = WorkbookUtils.getValueAt(sheet,
//                                                rowIndex,
//                                                cell.getColumnIndex(),
//                                                cell.getFieldType());

        if (cell.isRequired() && WorkbookUtils.isEmptyCell(sheet, rowIndex, columnIndex)) {
            ImportSheetError importSheetError = new ImportSheetError();
            importSheetError.setSheetName(sheet.getSheetName());
            importSheetError.setCellAddress(cell.getColumnCharacter() + rowIndex);
            importSheetError.setDescription(String.format("%s is required", cell.getCellName()));
            importSheetErrors.add(importSheetError);
            return false;
        }
        return true;

    }
}
