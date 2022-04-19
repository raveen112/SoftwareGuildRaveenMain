/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.service;

import com.raveenm.guessthenumber.dao.GuessTheNumberGameDao;
import com.raveenm.guessthenumber.dao.GuessTheNumberRoundsDao;
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

    // get answer
    // calculate result
    // update game status
    // generate answer
    //boxed Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
    public String answerGenerator() {
        List<Integer> intPool = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Random rSeed = new Random();

        String out = "";
        for (int i = 1; i <= 4; i++) {
            out += intPool.remove(rSeed.nextInt(intPool.size()));
        }
        return out;
    }

    // get answer
}
