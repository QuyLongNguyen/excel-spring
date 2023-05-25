package com.example.springworksheetdemo.dto.mapper.impl;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.dto.mapper.CandidateMapper;
import com.example.springworksheetdemo.entities.Candidate;
import com.example.springworksheetdemo.enums.Gender;
import com.example.springworksheetdemo.repository.CandidateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CandidateDTO toDTO(Candidate entity) {
        return modelMapper.map(entity, CandidateDTO.class);
    }

    @Override
    public Candidate toEntity(CandidateDTO candidateDTO) {
        return modelMapper.map(candidateDTO, Candidate.class);
    }
}
