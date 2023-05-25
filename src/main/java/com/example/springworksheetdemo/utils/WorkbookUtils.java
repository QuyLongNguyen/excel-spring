package com.example.springworksheetdemo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.time.ZoneId;

public class WorkbookUtils {

    public static <T> T getValueAt(Sheet sheet, int rowIndex, int columnIndex, Class<T> classType) throws IllegalStateException {
        Row row = sheet.getRow(rowIndex - 1);
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(columnIndex - 1);
        if (cell == null) {
            return null;
        }
        return getValueAt(sheet, cell, classType);
    }

    public static <T> T getValueAt(Sheet sheet, Cell cell, Class<T> classType) throws IllegalStateException {
        if (classType.isEnum()) {
            String cellValue = cell.getStringCellValue();
            for (Object enumValue : classType.getEnumConstants()) {
                if (enumValue.toString().equals(cellValue.toUpperCase())) {
                    return (T) enumValue;
                }
            }
        }

        switch (classType.getSimpleName()) {
            case "Integer":
                return (T) Integer.valueOf((int) cell.getNumericCellValue());
            case "Double":
                return (T) Double.valueOf(cell.getNumericCellValue());
            case "String":
                return (T) cell.getStringCellValue();
            case "LocalDate":
                return (T) cell.getDateCellValue()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
        }
        return null;
    }

    public static boolean isEmptyCell(Sheet sheet, int rowIndex, int columnIndex) {
        Row row = sheet.getRow(rowIndex - 1);
        if (row == null) {
            return false;
        }
        Cell cell = row.getCell(columnIndex - 1);
        return isEmptyCell(cell);
    }

    public static boolean isEmptyCell(Cell cell) {
        return cell == null || cell.getCellType() == CellType.BLANK;
    }
}
