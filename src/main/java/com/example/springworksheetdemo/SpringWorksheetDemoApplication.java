package com.example.springworksheetdemo;

import com.example.springworksheetdemo.entities.Candidate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.ArrayList;

@SpringBootApplication
public class SpringWorksheetDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWorksheetDemoApplication.class, args);
    }

}
