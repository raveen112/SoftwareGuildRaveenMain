/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.model.Game;
import com.raveenm.guessthenumber.model.Round;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



/**
 *
 * @author ravee
 */
@SpringBootTest
public class GuessTheNumberGameDaoDBTest {

    @Autowired
    GuessTheNumberGameDao gameDao;

    @Autowired
    GuessTheNumberRoundsDao roundDao;

    @BeforeEach
    public void setUp() {

        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getRound_id());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getGame_id());
        }
    }

    /**
     * Test of addGame method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testAddGetGame() {

        Game game = new Game();
        game.setAnswer("2142");
        game.setStatus("In progress");

        game = gameDao.addGame(game);
        Game fromDao = gameDao.getGameById(game.getGame_id());

        // assertEquals(game, fromDao, "Game could not be retrieved :/");
        assertEquals(game, fromDao);

        System.out.println(fromDao.getGame_id());
    }

    /**
     * Test of getAllGames method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testGetAllGames() {

    }

    /**
     * Test of getGameById method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testGetGameById() {
    }

    /**
     * Test of updateGame method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testUpdateGame() {
    }

    /**
     * Test of deleteGame method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testDeleteGame() {
    }

}
