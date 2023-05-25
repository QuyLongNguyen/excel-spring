package com.example.springworksheetdemo;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.dto.mapper.CandidateMapper;
import com.example.springworksheetdemo.entities.Candidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringWorksheetDemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringWorksheetDemoApplication.class, args);

    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
