package com.nonius.dao;

import com.nonius.game.model.Game;
import com.nonius.game.model.PrivateGameState;
import com.nonius.game.model.PublicGameState;

public interface GameDAO {
	
	void createNewGame(Game game);
	void updateGame(PrivateGameState privateGame, PublicGameState publicGame);
}
