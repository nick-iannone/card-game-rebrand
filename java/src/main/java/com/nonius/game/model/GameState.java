package com.nonius.game.model;

public class GameState {
	
	private PublicGameState publicState;
	private PrivateGameState privateState;
	
	
	public PublicGameState getPublicState() {
		return publicState;
	}
	public void setPublicState(PublicGameState publicState) {
		this.publicState = publicState;
	}
	public PrivateGameState getPrivateState() {
		return privateState;
	}
	public void setPrivateState(PrivateGameState privateState) {
		this.privateState = privateState;
	}
}
