/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tomvoa.model;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Builder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ravee
 */
@Getter
@Setter
@Data
@Builder
@Entity
public class Issue {

    @Id
    private int issue_id;
    private LocalDate date;
    private String complaint;
    private Boolean status;

    @OneToMany
    private Associate associate;

}
