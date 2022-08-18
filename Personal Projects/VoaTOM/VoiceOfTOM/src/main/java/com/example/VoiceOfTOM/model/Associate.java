/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.model;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author ravee
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Associate {
   int associate_id;
   String login;
   String name;
   List<Issue> issues;
}
