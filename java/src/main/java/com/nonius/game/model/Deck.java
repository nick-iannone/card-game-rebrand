package com.nonius.game.model;

import java.util.List;

public class Deck {
	
	private List<Card> cards;
	private List<Contract> contracts;
	private String ownerUsername;
	private int deckID;
	private String name;
	private String imageName;
	private Boolean isFavorite;
	private Boolean isSelected;
	
	
	public Deck() {
		this.isSelected = false;
	}
	
	
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Boolean getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public List<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	public int getDeckID() {
		return deckID;
	}
	public void setDeckID(int deckID) {
		this.deckID = deckID;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
}
