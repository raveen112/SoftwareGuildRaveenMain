/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.service;

import com.raveenm.guessthenumber.dao.GuessTheNumberGameDao;
import com.raveenm.guessthenumber.dao.GuessTheNumberRoundsDao;
import com.raveenm.guessthenumber.model.Game;
import com.raveenm.guessthenumber.model.Round;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ravee
 */
public class GuessTheNumberService {

    @Autowired
    GuessTheNumberRoundsDao roundDao;

    @Autowired
    GuessTheNumberGameDao gameDao;


    // update game status
    
    // generate answer and starts game
    // boxed Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
    public String answerGenerator() {
        List<Integer> intPool = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Random rSeed = new Random();

        String out = "";
        for (int i = 1; i <= 4; i++) {
            out += intPool.remove(rSeed.nextInt(intPool.size()));
        }
        return out;
    }

    // calculate answer
    public String calculateResult(String answer, String guess) {

        int exactMatch = 0;
        int partialMatch = 0;

        String[] ansToken = answer.split("");
        String[] guessToken = guess.split("");

        for (int i = 0; i < answer.length(); i++) {
            if (ansToken[i].equals(guessToken[i])) {
                exactMatch += 1;
            }
            if (ansToken[i].contains(guessToken[i])) {
                partialMatch += 1;
            }

        }
        return "e:" + exactMatch + "p:" + partialMatch;
    }
    
    // update game status
    public String gameStatusUpdate(Round round, Game game){
        
        
    }
    
    
    
    
    
    
    // game pass through methods
    
     public Game addGame(Game game) {
//        validateGame(game);
        return gameDao.addGame(game);
    }

    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    public Game getGameById(int id) {
        return gameDao.getGameById(id);
    }

    public void deleteGameById(int id) {
        gameDao.deleteGame(id);
    }

    public void editGame(Game game) {
//        validateGame(game);
        gameDao.updateGame(game);
    }
    
}
