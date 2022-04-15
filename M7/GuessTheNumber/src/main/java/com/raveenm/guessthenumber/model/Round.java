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
    int round_id;
    String guess;
    LocalDateTime timeLog;
    int exactGuess;
    int partialGuess;
    String result;
    //will affect persistence
    int game_id;  

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(LocalDateTime timeLog) {
        this.timeLog = timeLog;
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

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.round_id;
        hash = 37 * hash + Objects.hashCode(this.guess);
        hash = 37 * hash + Objects.hashCode(this.timeLog);
        hash = 37 * hash + this.exactGuess;
        hash = 37 * hash + this.partialGuess;
        hash = 37 * hash + Objects.hashCode(this.result);
        hash = 37 * hash + this.game_id;
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
        if (this.round_id != other.round_id) {
            return false;
        }
        if (this.exactGuess != other.exactGuess) {
            return false;
        }
        if (this.partialGuess != other.partialGuess) {
            return false;
        }
        if (this.game_id != other.game_id) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.timeLog, other.timeLog)) {
            return false;
        }
        return true;
    }
    
    


}
