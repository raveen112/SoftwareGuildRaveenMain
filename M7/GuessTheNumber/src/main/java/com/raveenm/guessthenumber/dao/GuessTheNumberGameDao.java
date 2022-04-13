/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.model.Game;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface GuessTheNumberGameDao {
    //Basic CRUD functionality 
    List<Game> getAllGames();
    Game getGameById(int id);
    Game addGame(Game game);
    boolean updateGame(Game game);
    boolean deleteGame(int id);
}
