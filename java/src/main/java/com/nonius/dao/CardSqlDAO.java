package com.nonius.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.nonius.game.model.Card;
import com.nonius.game.model.Contract;
import com.nonius.game.model.Deck;

@Component
public class CardSqlDAO implements CardDAO {

	private JdbcTemplate jdbcTemplate;

	public CardSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Card> getAllCards() {

		String sqlGetCards = "SELECT * FROM cards";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCards);
		List<Card> allCards = new ArrayList<Card>();
		while (results.next()) {
			allCards.add(mapRowToCard(results));
		}
		return allCards;
	}

	@Override
	public Card getCardById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deck> getDecksByUsername(String username) {

		List<Deck> decks = new ArrayList<Deck>();

		int userID = getOwnerIdByUsername(username);

		String sql = "SELECT deck_id, name, image_name, is_favorite FROM decks WHERE owner_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);
		while (results.next()) {
			decks.add(mapRowToDeck(results));
		}

		for (Deck d : decks) {

			List<Card> cards = new ArrayList<Card>();
			sql = "SELECT * FROM cards JOIN decks_cards ON decks_cards.card_id = cards.id WHERE deck_id = ?";
			results = jdbcTemplate.queryForRowSet(sql, d.getDeckID());
			while (results.next()) {
				cards.add(mapRowToCard(results));
			}

			List<Contract> contracts = new ArrayList<Contract>();
			sql = "SELECT * FROM contracts JOIN decks_contracts ON decks_contracts.contract_id = contracts.id WHERE deck_id = ?";
			results = jdbcTemplate.queryForRowSet(sql, d.getDeckID());
			while (results.next()) {
				contracts.add(mapRowToContract(results));
			}

			d.setCards(cards);
			d.setContracts(contracts);
			d.setOwnerUsername(username);
		}

		for (Deck z : decks) {
			z.setCards(serializeCards(z.getCards()));
			z.setContracts(serializeContracts(z.getContracts()));
		}

		return decks;
	}

	@Override
	public List<Contract> getAllContracts() {
		String sqlGetContracts = "SELECT * FROM contracts";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetContracts);
		List<Contract> allContracts = new ArrayList<Contract>();
		while (results.next()) {
			allContracts.add(mapRowToContract(results));
		}
		return allContracts;
	}

	@Override
	public void addNewDeck(Deck newDeck) {

		if (checkDeck(newDeck)) {
			updateDeck(newDeck);
		} else {
			newDeck.setDeckID(getNextIdForDeck());
			String sql = "INSERT INTO decks (deck_id, owner_id, name, image_name) VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, newDeck.getDeckID(), getOwnerIdByUsername(newDeck.getOwnerUsername()),
					newDeck.getName(), newDeck.getImageName());
			newDeck.setCards(revertCardId(newDeck.getCards()));
			newDeck.setContracts(revertContractId(newDeck.getContracts()));
			for (Card c : newDeck.getCards()) {
				sql = "INSERT INTO decks_cards (deck_id, card_id) VALUES (?, ?)";
				jdbcTemplate.update(sql, newDeck.getDeckID(), c.getId());
			}
			for (Contract c : newDeck.getContracts()) {
				sql = "INSERT INTO decks_contracts (deck_id, contract_id) VALUES (?, ?)";
				jdbcTemplate.update(sql, newDeck.getDeckID(), c.getId());
			}
		}
	}

	@Override
	public void updateFavorite(int deckID, boolean isFavorite) {
		String sql = "UPDATE decks SET is_favorite = ? WHERE deck_id = ?";
		jdbcTemplate.update(sql, isFavorite, deckID);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////   HELPER METHODS   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////   HELPER METHODS   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////   HELPER METHODS   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void updateDeck(Deck deck) {
		String sql = "UPDATE decks SET is_favorite = ?, name = ?, image_name = ? WHERE deck_id = ?";
		jdbcTemplate.update(sql, deck.getIsFavorite(), deck.getName(), deck.getImageName(), deck.getDeckID());
		String sqlDelete = "DELETE FROM decks_cards WHERE deck_id = ?";
		jdbcTemplate.update(sqlDelete, deck.getDeckID());
		sqlDelete = "DELETE FROM decks_contracts WHERE deck_id = ?";
		jdbcTemplate.update(sqlDelete, deck.getDeckID());
		for (Card c : deck.getCards()) {
			sql = "INSERT INTO decks_cards (deck_id, card_id) VALUES (?, ?)";
			jdbcTemplate.update(sql, deck.getDeckID(), c.getId());
		}
		for (Contract c : deck.getContracts()) {
			sql = "INSERT INTO decks_contracts (deck_id, contract_id) VALUES (?, ?)";
			jdbcTemplate.update(sql, deck.getDeckID(), c.getId());
		}
	}
	
	public Boolean checkDeck(Deck newDeck) {
		for (Deck d : getDecksByUsername(newDeck.getOwnerUsername())) {
			if (d.getName().equalsIgnoreCase(newDeck.getName())) {
				return true;
			}
		}
		return false;
	}

	public Card mapRowToCard(SqlRowSet results) {

		Card card = new Card();
		card.setId(results.getInt("id"));
		card.setName(results.getString("name"));
		card.setCost(results.getString("cost"));
		card.setImageName(results.getString("image"));
		card.setAbilities(results.getString("abilities"));
		card.setFlavor(results.getString("flavor"));
		card.setAttack(results.getInt("attack"));
		card.setResilience(results.getInt("resilience"));
		card.setLoyalty(results.getInt("loyalty"));
		card.setIsAttacking(results.getBoolean("is_attacking"));
		card.setIsTarget(results.getBoolean("is_target"));
		card.setCanAttack(results.getBoolean("can_attack"));
		card.setOnEnter(results.getBoolean("on_enter"));
		card.setOnLeave(results.getBoolean("on_leave"));
		card.setOnAttack(results.getBoolean("on_attack"));
		card.setOnDamage(results.getBoolean("on_damage"));
		card.setOnTurnStart(results.getBoolean("on_turn_start"));
		card.setOnTurnEnd(results.getBoolean("on_turn_end"));
		card.setIsHeroic(results.getBoolean("is_heroic"));
		card.setIsFabled(results.getBoolean("is_fabled"));
		card.setIsHallowed(results.getBoolean("is_hallowed"));
		card.setIsVile(results.getBoolean("is_vile"));
		card.setIsFeared(results.getBoolean("is_feared"));
		card.setIsDreaded(results.getBoolean("is_dreaded"));
		card.setCount(results.getInt("count"));

		return card;
	}

	private Contract mapRowToContract(SqlRowSet results) {

		Contract contract = new Contract();
		contract.setId(results.getInt("id"));
		contract.setName(results.getString("name"));
		contract.setCost(results.getString("cost"));
		contract.setDesc(results.getString("text"));
		contract.setDura(results.getInt("duration"));
		contract.setActive(results.getBoolean("is_active"));
		contract.setHasCounters(results.getBoolean("has_counters"));
		contract.setCount(results.getInt("count"));

		return contract;
	}

	private int getNextIdForDeck() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_deck_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new deck...");
		}
	}

	private Deck mapRowToDeck(SqlRowSet results) {

		Deck deck = new Deck();
		deck.setDeckID(results.getInt("deck_id"));
		deck.setName(results.getString("name"));
		deck.setImageName(results.getString("image_name"));
		deck.setIsFavorite(results.getBoolean("is_favorite"));

		return deck;
	}

	private List<Card> serializeCards(List<Card> cards) {

		int increment = 2431;

		for (int i = 0; i < cards.size(); i++) {
			int id = cards.get(i).getId();
			for (int x = i + 1; x < cards.size(); x++) {
				if (id == cards.get(x).getId()) {
					cards.get(x).setId((cards.get(x).getId() + increment));
					increment += 719;
				}
			}
		}
		return cards;
	}

	private List<Contract> serializeContracts(List<Contract> contracts) {

		int increment = 5749;

		for (int i = 0; i < contracts.size(); i++) {
			int id = contracts.get(i).getId();
			for (int x = i + 1; x < contracts.size(); x++) {
				if (id == contracts.get(x).getId()) {
					contracts.get(x).setId((contracts.get(x).getId() + increment));
					increment += 719;
				}
			}
		}
		return contracts;
	}

	private List<Card> revertCardId(List<Card> cards) {
		for (Card c : cards) {
			String sql = "SELECT id FROM cards WHERE name = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, c.getName());
			if (results.next()) {
				c.setId(results.getInt("id"));
			}
		}
		return cards;
	}

	private List<Contract> revertContractId(List<Contract> contracts) {
		for (Contract c : contracts) {
			String sql = "SELECT id FROM contracts WHERE name = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, c.getName());
			if (results.next()) {
				c.setId(results.getInt("id"));
			}
		}
		return contracts;
	}

	private int getOwnerIdByUsername(String username) {
		String sql = "SELECT user_id FROM users WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
		if (results.next()) {
			return results.getInt("user_id");
		}
		return 0;
	}
}
