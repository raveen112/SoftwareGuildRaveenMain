/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.TestApplicationConfiguration;
import com.raveenm.guessthenumber.model.Game;
import com.raveenm.guessthenumber.model.Round;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ravee
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessTheNumberGameDaoDBTest {

    @Autowired
    GuessTheNumberGameDao gameDao;

    @Autowired
    GuessTheNumberRoundsDao roundDao;

    @Before
    public void setUp() {

        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getRoundId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getGameId());
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
        Game fromDao = gameDao.getGameById(game.getGameId());

        // assertEquals(game, fromDao, "Game could not be retrieved :/");
        assertEquals(game, fromDao);

        System.out.println(fromDao.getGameId());

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
