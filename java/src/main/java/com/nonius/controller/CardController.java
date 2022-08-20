package com.nonius.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonius.dao.CardSqlDAO;
import com.nonius.game.model.Card;
import com.nonius.game.model.Contract;
import com.nonius.game.model.Deck;


@RestController
@CrossOrigin
public class CardController {
	
	private CardSqlDAO cardDAO;
	
	public CardController(CardSqlDAO cardDAO) {
		this.cardDAO = cardDAO;
	}
	
	@RequestMapping (path = "/cards", method = RequestMethod.GET)
	public List<Card> getAllCards() {
		return cardDAO.getAllCards();
	}
	
	@RequestMapping (path = "/decks/{username}", method = RequestMethod.GET)
	public List<Deck> getDecksByUsername(@PathVariable String username) {
		return cardDAO.getDecksByUsername(username);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/decks", method = RequestMethod.POST)
	public void addNewDeck(@RequestBody Deck newDeck) {
		cardDAO.addNewDeck(newDeck);
	}
	
	@RequestMapping (path = "/contracts", method = RequestMethod.GET)
	public List<Contract> getAllContracts() {
		return cardDAO.getAllContracts();
	}
	
	@ResponseStatus (HttpStatus.OK)
	@RequestMapping (path = "/decks/{deckID}/{isFavorite}", method = RequestMethod.POST)
	public void updateFavorite(@PathVariable int deckID, @PathVariable boolean isFavorite) {
		cardDAO.updateFavorite(deckID, isFavorite);
	}
}
