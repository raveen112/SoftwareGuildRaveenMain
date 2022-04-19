/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface GuessTheNumberRoundsDao {
    // Basic CRUD functionality 
    List<Round> getAllRoundsForGame(int id);
    Round addRound(Round round);
    boolean deleteRoundByGameId(int id);
}
