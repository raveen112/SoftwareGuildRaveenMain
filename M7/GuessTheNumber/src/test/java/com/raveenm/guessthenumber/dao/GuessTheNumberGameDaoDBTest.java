/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.model.Game;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            roundDao.deleteRoundByGameId(game.getGame_id());
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

        assertEquals(game, fromDao, "Game could not be retrieved :/");

        System.out.println(fromDao.getGame_id());
    }

    /**
     * Test of getAllGames method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testGetAllGames() {
        Game game1 = new Game();
        game1.setAnswer("9312");
        game1.setStatus("In progress");
        game1 = gameDao.addGame(game1);

        Game game2 = new Game();
        game2.setAnswer("9332");
        game2.setStatus("In progress");
        game2 = gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGames();
        assertEquals(games.size(), 2);

        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of updateGame method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testUpdateGame() {
        Game game1 = new Game();
        game1.setAnswer("9312");
        game1.setStatus("In progress");
        game1 = gameDao.addGame(game1);

        game1.setAnswer("8212");
        gameDao.updateGame(game1);

        Game fromDao = gameDao.getGameById(game1.getGame_id());

        assertEquals(fromDao, game1);
        assertNotEquals(fromDao.getAnswer(), "9312");

    }

    /**
     * Test of deleteGame method, of class GuessTheNumberGameDaoDB.
     */
    @Test
    public void testDeleteGame() {
        Game game1 = new Game();
        game1.setAnswer("9312");
        game1.setStatus("In progress");
        game1 = gameDao.addGame(game1);

        Game game2 = new Game();
        game2.setAnswer("9332");
        game2.setStatus("In progress");
        game2 = gameDao.addGame(game2);

        gameDao.deleteGame(game1.getGame_id());

        List<Game> games = gameDao.getAllGames();
        assertEquals(1, games.size());
        assertTrue(games.contains(game2));
    }

}
