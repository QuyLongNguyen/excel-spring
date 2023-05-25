package com.example.springworksheetdemo.service.impl;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.dto.mapper.CandidateMapper;
import com.example.springworksheetdemo.entities.Candidate;
import com.example.springworksheetdemo.repository.CandidateRepository;
import com.example.springworksheetdemo.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public Iterable<Candidate> saveAll(List<CandidateDTO> candidates) {
        return candidateRepository.saveAll(candidates.stream()
                .map(candidate -> candidateMapper.toEntity(candidate))
                .collect(Collectors.toList()));
    }


}
