package com.nonius.game.model;

import java.util.List;

public class PrivateGameState {
	
	private List<Card> hand;
	private List<Card> deck;
	private List<Contract> contDeck;
	private List<Contract> contAvail;
	private List<Card> deadZone;
	
	
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Contract> getContDeck() {
		return contDeck;
	}
	public void setContDeck(List<Contract> contDeck) {
		this.contDeck = contDeck;
	}
	public List<Contract> getContAvail() {
		return contAvail;
	}
	public void setContAvail(List<Contract> contAvail) {
		this.contAvail = contAvail;
	}
	public List<Card> getDeadZone() {
		return deadZone;
	}
	public void setDeadZone(List<Card> deadZone) {
		this.deadZone = deadZone;
	}
}
