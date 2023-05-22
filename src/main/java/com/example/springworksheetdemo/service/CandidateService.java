package com.example.springworksheetdemo.service;

import com.example.springworksheetdemo.entities.Candidate;

public interface CandidateService {

    Iterable<Candidate> saveAll(Iterable<Candidate> candidates);
}
