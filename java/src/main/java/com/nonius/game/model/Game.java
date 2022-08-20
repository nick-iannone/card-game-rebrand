package com.nonius.game.model;

public class Game {
	
	private int gameID;
	private int p1ID;
	private int p2ID;
	private int activePlayer;
	private String winner;
	private String loser;
	private String timestamp;
	private Boolean isActive;
	private GameState p1State;
	private GameState p2State;
	
	
	public GameState getP1State() {
		return p1State;
	}
	public void setP1State(GameState p1State) {
		this.p1State = p1State;
	}
	public GameState getP2State() {
		return p2State;
	}
	public void setP2State(GameState p2State) {
		this.p2State = p2State;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getP1ID() {
		return p1ID;
	}
	public void setP1ID(int p1id) {
		p1ID = p1id;
	}
	public int getP2ID() {
		return p2ID;
	}
	public void setP2ID(int p2id) {
		p2ID = p2id;
	}
	public int getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getLoser() {
		return loser;
	}
	public void setLoser(String loser) {
		this.loser = loser;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
