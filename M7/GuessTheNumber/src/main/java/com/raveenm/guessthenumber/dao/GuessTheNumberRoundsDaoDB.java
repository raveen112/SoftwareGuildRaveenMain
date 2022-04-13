/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.model.Round;
import static java.lang.Math.round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 *
 * @author ravee
 */
public class GuessTheNumberRoundsDaoDB implements GuessTheNumberRoundsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessTheNumberRoundsDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(guess,game_id,result,time,result) VALUES (?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, round.getGuess());
            statement.setInt(2, round.getGameId());
            statement.setTimestamp(3, Timestamp.valueOf(round.getTime()));
            statement.setString(4, round.getResult());
            return statement;

        }, keyHolder);
        round.setRoundId(keyHolder.getKey().intValue());
        return round;
    }

    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT * FROM round; ";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) {
        final String sql = "SELECT * FROM round WHERE round_id = ?;";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
    }

    @Override
    public boolean updateRoundById(Round round) {
        final String sql = "UPDATE round SET "
                + "game_id = ?, "
                + "guess = ?, "
                + "result = ?, "
                + "time = ? "
                + "WHERE round_id =?;";

        return jdbcTemplate.update(sql,
                round.getGameId(),
                round.getGuess(),
                round.getResult(),
                Timestamp.valueOf(round.getTime()),
                round.getRoundId()) > 0;

    }

    @Override
    public boolean deleteRoundById(int id) {
       final String sql = "DELETE FROM round WHERE round_id = ?;";
       return jdbcTemplate.update(sql, id) >0;
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round rm = new Round();
            rm.setRoundId(rs.getInt("round_id"));
            rm.setGameId(rs.getInt("game_id"));
            rm.setGuess(rs.getString("guess"));
            rm.setTimeOfGuess(rs.getTimestamp("timeLog").toLocalDateTime());
            rm.setResult(rs.getString("result"));

            return rm;
        }

    }
}
