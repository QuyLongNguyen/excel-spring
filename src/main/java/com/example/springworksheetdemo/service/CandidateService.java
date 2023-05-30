package com.example.springworksheetdemo.service;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.entities.Candidate;

import java.util.List;

public interface CandidateService {

    Iterable<Candidate> saveAll(List<CandidateDTO> candidates);

    List<CandidateDTO> findAll();
}
