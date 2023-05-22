package com.example.springworksheetdemo.entities;

import com.example.springworksheetdemo.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double gpa;
}
