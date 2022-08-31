/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.model;

import java.time.LocalDate;
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
public class Issue {

    int issue_id;
    LocalDate date;
    String complaint;
    Boolean status;
    Associate associate;

    public Issue(int issue_id, LocalDate date, String complaint, Boolean status, Associate associate) {
        this.issue_id = issue_id;
        this.date = date;
        this.complaint = complaint;
        this.status = status;
        this.associate = associate;
    }

    public Issue() {
    }
   

}
