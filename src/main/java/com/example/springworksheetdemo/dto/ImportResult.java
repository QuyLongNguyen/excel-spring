package com.example.springworksheetdemo.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImportResult<T> {

    private String message;

    private List<ImportError> errors;

    private List<T> data;

}
