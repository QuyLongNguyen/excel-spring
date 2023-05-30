package com.example.springworksheetdemo.repository;

import com.example.springworksheetdemo.entities.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Integer> {

    List<Candidate> findAll();

}
