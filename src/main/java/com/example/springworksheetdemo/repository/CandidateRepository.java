package com.example.springworksheetdemo.repository;

import com.example.springworksheetdemo.entities.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Integer> {


}
