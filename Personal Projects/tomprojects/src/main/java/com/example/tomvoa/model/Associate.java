package com.example.tomvoa.model;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ravee
 */
@Data
@NoArgsConstructor
@Entity
@Setter
@Builder
@Table(name = "associate")
public class Associate {

    @Id
    @GeneratedValue
    int associate_id;
    
    @NonNull
    String login;
    
    String name;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Issue> issues; 

    public Associate(int associate_id, String login, String name, Set<Issue> issues) {
        this.associate_id = associate_id;
        this.login = login;
        this.name = name;
        this.issues = issues;
    }
    
    
}
