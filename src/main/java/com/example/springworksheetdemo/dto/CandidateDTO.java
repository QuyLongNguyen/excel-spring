package com.example.springworksheetdemo.dto;

import com.example.springworksheetdemo.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CandidateDTO {

    private int id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private Gender gender;

    private double gpa;
}
