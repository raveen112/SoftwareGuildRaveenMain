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
import java.sql.SQLClientInfoException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ravee
 */
@Component
public class GuessTheNumberService {

    @Autowired
    GuessTheNumberRoundsDao roundDao;

    @Autowired
    GuessTheNumberGameDao gameDao;

    public GuessTheNumberService(GuessTheNumberRoundsDao roundDao, GuessTheNumberGameDao gameDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    // update game status
    // generate answer and starts game
    // boxed Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
    // helper for addGame
    private String answerGenerator() {
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

            boolean isPartialMatch = false;

            for (int j = 0; j < answer.length(); j++) {
                if (ansToken[j].equals(guessToken[i]) && j != i) {
                    isPartialMatch = true;
                }
            }
            if (isPartialMatch) {
                partialMatch++;
            }

        }

        return "e:" + exactMatch + " p:" + partialMatch;
    }
    
//    // update game status
//    public void gameStatusUpdate(Round round, Game game) throws Exception{
//        if(round.getGame_id() != game.getGame_id()){
//            throw new Exception("Game and round dont match");
//        }
//        if(round.getResult().equals(answerGenerator())){
//            game.setStatus("Finished!");
//        }
//    }

    // take guess and update status
    // game pass through methods
    public Game addGame() {
        Game game = new Game();
        game.setAnswer(answerGenerator());
        game.setStatus("In Progress");
        game = gameDao.addGame(game);
        game.setAnswer("Hidden");
        return game;
    }

    public Round addRound(Round round) {
        Game game = gameDao.getGameById(round.getGame_id());

        if (game == null) {
            return null;
        }
        if (game.getStatus().equalsIgnoreCase("Finished!")) {
            return null;
        }

        String answer = game.getAnswer();
        String guess = round.getGuess();

        if (answer.equals(guess)) {
            game.setStatus("Finished!");
            gameDao.updateGame(game);
        }

        String result = calculateResult(answer, guess);
        round.setResult(result);

        return roundDao.addRound(round);

    }

    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames() ;
        for(Game game : games){
            if(game.getStatus().equals("In Progress")){
                game.setAnswer("Game is still in progress. Keep guessing.");
            }
        }
        return games;
    }

    public Game getGameById(int id) {
        return gameDao.getGameById(id);
    }

    public List<Round> getAllRoundsForGame(int id){
        return roundDao.getAllRoundsForGame(id);
    }
}
