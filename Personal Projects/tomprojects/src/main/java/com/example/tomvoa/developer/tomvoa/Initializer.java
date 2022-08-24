/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tomvoa.developer.tomvoa;

import com.example.tomvoa.model.Associate;
import com.example.tomvoa.model.Issue;
import java.util.Collections;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import com.example.tomvoa.model.AssociateRepository;
import static com.fasterxml.jackson.databind.util.ClassUtil.name;

/**
 *
 * @author ravee
 */
@Component
class Initializer implements CommandLineRunner {

    private final AssociateRepository repository;

    public Initializer(AssociateRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Stream.of("Nomaan", "Mitva", "Pinki",
                "Omar").forEach(login
                        -> repository.save(new Associate())
                );

        Associate tomvoa = repository.findByLogin("Nomaan");
        Issue e = Issue.builder().complaint("Need new Safety shoes")
                .date(LocalDate.parse("2022-09-13"))
                .status(Boolean.FALSE)
                .build();
        tomvoa.setIssues(Collections.singleton(e));
        repository.save(tomvoa);

        repository.findAll().forEach(System.out::println);
    }

}
