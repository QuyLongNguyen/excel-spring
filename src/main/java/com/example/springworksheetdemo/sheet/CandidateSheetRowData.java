package com.example.springworksheetdemo.sheet;

import com.example.springworksheetdemo.enums.Gender;

import java.time.LocalDate;

public enum CandidateSheetRowData implements SheetRowData {

    FIRST_NAME("First Name", 1, "A", true, "firstName", String.class),
    LAST_NAME("Last Name", 2, "B",false, "lastName", String.class),
    GENDER("Gender", 3, "C", true, "gender", Gender.class),
    DATE_OF_BIRTH("Date of birth", 4, "D", true, "dob", LocalDate.class),
    GPA("Gpa", 5, "E", true, "gpa", Double.class),
    ;

    private String cellName;
    private int columnIndex;
    private String columnCharacter;
    private boolean isRequired;
    private String fieldName;
    private Class fieldType;

    public static final String SHEET_NAME = "Candidate";

    CandidateSheetRowData(String cellName, int columnIndex, String columnCharacter, boolean isRequired, String fieldName, Class fieldType){
        this.cellName = cellName;
        this.columnIndex = columnIndex;
        this.columnCharacter = columnCharacter;
        this.isRequired = isRequired;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public String getCellName() {
        return cellName;
    }

    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String getColumnCharacter() {
        return columnCharacter;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public Class getFieldType() {
        return fieldType;
    }
}
