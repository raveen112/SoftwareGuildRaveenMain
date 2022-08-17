/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.model;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ravee
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Setter
public class Issue {

    int issue_id;
    LocalDate date;
    String complaint;
    Boolean status;

}
