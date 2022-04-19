///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.raveenm.guessthenumber.dao;
//
//import com.raveenm.guessthenumber.model.Game;
//import com.raveenm.guessthenumber.model.Round;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// *
// * @author ravee
// */
//@SpringBootTest
//public class GuessTheNumberRoundsDaoDBTest {
//
//    @Autowired
//    GuessTheNumberGameDao gameDao;
//
//    @Autowired
//    GuessTheNumberRoundsDao roundDao;
//
//    @BeforeEach
//    public void setUp() {
//        List<Round> rounds = roundDao.getAllRounds();
//        for (Round round : rounds) {
//            roundDao.deleteRoundById(round.getRound_id());
//        }
//
//        List<Game> games = gameDao.getAllGames();
//        for (Game game : games) {
//            gameDao.deleteGame(game.getGame_id());
//        }
//    }
//
//    /**
//     * Test of addRound method, of class GuessTheNumberRoundsDaoDB.
//     */
//    @Test
//    public void testAddGetRound() {
//        
//        Game game = new Game();
//        game.setGame_id(1);
//        game.setAnswer("3741");
//        game.setStatus("In Progress");
//        game = gameDao.addGame(game);
//        
//        Round round = new Round();
//        round.setGuess("3124");
//        round.setTimeLog(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
//        round.setResult("e:1:p:2");
//        round.setExactGuess(1);
//        round.setPartialGuess(2);
//        round = roundDao.getRoundById(round.getRound_id());
//        
//        Round fromDao = roundDao.getRoundById(round.getRound_id());
//        
//        assertEquals(round, fromDao);
//    }
//
//    /**
//     * Test of getAllRounds method, of class GuessTheNumberRoundsDaoDB.
//     */
//    @Test
//    public void testGetAllRounds() {
//    }
//
//    /**
//     * Test of getRoundById method, of class GuessTheNumberRoundsDaoDB.
//     */
//
//    /**
//     * Test of updateRoundById method, of class GuessTheNumberRoundsDaoDB.
//     */
//    @Test
//    public void testUpdateRoundById() {
//    }
//
//    /**
//     * Test of deleteRoundById method, of class GuessTheNumberRoundsDaoDB.
//     */
//    @Test
//    public void testDeleteRoundById() {
//    }
//
//}
