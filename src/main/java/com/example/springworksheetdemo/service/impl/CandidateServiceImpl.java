package com.example.springworksheetdemo.service.impl;

import com.example.springworksheetdemo.entities.Candidate;
import com.example.springworksheetdemo.repository.CandidateRepository;
import com.example.springworksheetdemo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Iterable<Candidate> saveAll(Iterable<Candidate> candidates) {
        return candidateRepository.saveAll(candidates);
    }
}
