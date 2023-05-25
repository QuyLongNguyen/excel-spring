package com.example.springworksheetdemo.dto.mapper;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.entities.Candidate;

public interface CandidateMapper{

    Candidate toEntity(CandidateDTO candidateDTO);

    CandidateDTO toDTO(Candidate candidate);

}
