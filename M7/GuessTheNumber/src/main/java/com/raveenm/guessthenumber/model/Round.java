/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author ravee
 */
public class Round {
    int roundId;
    String guess;
    LocalDateTime timeOfGuess;
    int exactGuess;
    int partialGuess;
    String result;
    int gameId;

    public String getGuess() {
        return guess;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.roundId;
        hash = 97 * hash + Objects.hashCode(this.guess);
        hash = 97 * hash + Objects.hashCode(this.timeOfGuess);
        hash = 97 * hash + this.exactGuess;
        hash = 97 * hash + this.partialGuess;
        hash = 97 * hash + Objects.hashCode(this.result);
        hash = 97 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.exactGuess != other.exactGuess) {
            return false;
        }
        if (this.partialGuess != other.partialGuess) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.timeOfGuess, other.timeOfGuess)) {
            return false;
        }
        return true;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getTimeOfGuess() {
        return timeOfGuess;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setTimeOfGuess(LocalDateTime timeOfGuess) {
        this.timeOfGuess = timeOfGuess;
    }

    public int getExactGuess() {
        return exactGuess;
    }

    public void setExactGuess(int exactGuess) {
        this.exactGuess = exactGuess;
    }

    public int getPartialGuess() {
        return partialGuess;
    }

    public void setPartialGuess(int partialGuess) {
        this.partialGuess = partialGuess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
