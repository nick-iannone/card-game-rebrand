package com.nonius.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.nonius.game.model.Game;
import com.nonius.game.model.PrivateGameState;
import com.nonius.game.model.PublicGameState;

@Component
public class GameSqlDAO implements GameDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public GameSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void createNewGame(Game game) {
//		game.setGameID(getNextIdForGame());
//		String sql = "INSERT INTO games (game_id, player_1, player_2, turn_count, timestamp, is_active) VALUES (?, ?, ?, ?, ?, ?)";
//		jdbcTemplate.update(sql, game.getGameID(), game.getPlayer1(), game.getPlayer2(), 0, game.getTimestamp(), true);
	}

	@Override
	public void updateGame(PrivateGameState privateGame, PublicGameState publicGame) {
		// TODO Auto-generated method stub
	}
	
	private int getNextIdForGame() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_game_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new game...");
		}
	}
}
