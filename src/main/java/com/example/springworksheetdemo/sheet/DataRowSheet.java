package com.example.springworksheetdemo.sheet;

public interface DataRowSheet {

    String getCellName();
    int getColumnIndex();

    String getColumnCharacter();

    String getFieldName();

    boolean isRequired();

    Class getFieldType();

}
