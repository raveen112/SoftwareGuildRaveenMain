/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.model;

import java.util.List;

/**
 *
 * @author ravee
 */

public class Associate {
   int associate_id;
   String login;
   String name;
   List<Issue> issues;

    public int getAssociate_id() {
        return associate_id;
    }

    public void setAssociate_id(int associate_id) {
        this.associate_id = associate_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
   
   
}
