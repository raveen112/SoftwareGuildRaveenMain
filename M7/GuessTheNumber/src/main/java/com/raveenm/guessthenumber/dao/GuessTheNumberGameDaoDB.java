/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import com.raveenm.guessthenumber.dao.GuessTheNumberRoundsDaoDB.RoundMapper;
import com.raveenm.guessthenumber.model.Game;
import com.raveenm.guessthenumber.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ravee
 */
@Repository
public class GuessTheNumberGameDaoDB implements GuessTheNumberGameDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String sql = "INSERT INTO game(status, answer) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getStatus());
            statement.setString(2, game.getAnswer());
            return statement;

        }, keyHolder);

        game.setGame_id(keyHolder.getKey().intValue());
        return game;
    }

    // add a private helper method to help populate the round list for a game
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    // add a private helper method to help populate the round list for a game
    @Override
    public Game getGameById(int id) {

        final String sql = "SELECT * FROM game WHERE game_id = ?;";
        Game game = jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        game.setRounds(getRoundsForGame(game.getGame_id()));
        return game;

    }

    //helper method
    private List<Round> getRoundsForGame(int id) {

        final String sql = "SELECT * FROM round WHERE game_id = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), id);

    }

    @Override
    public boolean updateGame(Game game) {

        final String sql = "UPDATE game SET "
                + "status = ?, "
                + "answer = ? "
                + "WHERE game_id = ?;";

        return jdbcTemplate.update(sql,
                game.getStatus(),
                game.getAnswer(),
                game.getGame_id()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteGame(int id) {
        final String sqlFK = "DELETE FROM round WHERE game_id = ?;";
        jdbcTemplate.update(sqlFK, id);
        final String sql = "DELETE FROM game WHERE game_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game gm = new Game();
            gm.setGame_id(rs.getInt("game_id"));
            gm.setAnswer(rs.getString("answer"));
            gm.setStatus(rs.getString("status"));
            return gm;
        }

    }
}
