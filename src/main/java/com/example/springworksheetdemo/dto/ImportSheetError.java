package com.example.springworksheetdemo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImportSheetError {

    private String sheetName;

    private String cellAddress;

    private String description;

}
