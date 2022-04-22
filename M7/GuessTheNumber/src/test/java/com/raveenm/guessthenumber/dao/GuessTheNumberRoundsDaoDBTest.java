/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.model.Game;
import com.raveenm.guessthenumber.model.Round;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class GuessTheNumberRoundsDaoDBTest {

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
     * Test of addRound method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testAddGetRound() {
        Game game = new Game();
        game.setAnswer("9312");
        game.setStatus("In progress");
        game = gameDao.addGame(game);
        
        
        Round round = new Round();
        round.setGuess("2389");
        round.setTimeLog(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        round.setResult("e:1:p:2");
        round.setGame_id(game.getGame_id());
        round = roundDao.addRound(round);
        
        List<Round> fromDao = roundDao.getAllRoundsForGame(round.getGame_id());
        
        assertEquals(1, fromDao.size());
    }

    /**
     * Test of deleteRoundByGameId method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testDeleteRoundByGameId() {
        Game game1 = new Game();
        game1.setAnswer("9312");
        game1.setStatus("In progress");
        game1 = gameDao.addGame(game1);
        
        
        Round round1 = new Round();
        round1.setGuess("2389");
        round1.setTimeLog(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        round1.setResult("e:1:p:2");
        round1.setGame_id(game1.getGame_id());
        round1 = roundDao.addRound(round1);
        
        Game game2 = new Game();
        game2.setAnswer("9313");
        game2.setStatus("In progress");
        game2 = gameDao.addGame(game1);
        
        
        Round round2 = new Round();
        round2.setGuess("2348");
        round2.setTimeLog(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        round2.setResult("e:1:p:0");
        round2.setGame_id(game2.getGame_id());
        round2 = roundDao.addRound(round2);
        
        roundDao.deleteRoundByGameId(round1.getGame_id());
        
        List<Round> rounds = roundDao.getAllRoundsForGame(round2.getGame_id());
        
        assertEquals(1, rounds.size());
        assertTrue(rounds.contains(round2));
    }

}
