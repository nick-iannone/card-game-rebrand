package com.nonius.websocket.model;

import com.nonius.game.model.Attack;
import com.nonius.game.model.Card;
import com.nonius.game.model.CombatZone;
import com.nonius.game.model.ContractsContainer;
import com.nonius.game.model.Turn;

public class GameActionMessage {
	
	private String username;
	private Boolean readyForBattle;
	private String lastEvent;
	private Card directive;
	private Boolean concede;
	private Boolean rollPhase;
	private int rollForFirst;
	private Boolean goesFirst;
	private Boolean mulliganPhase;
	private Boolean keepHand;
	private Boolean keepContracts;
	private int deckCount;
	private int handCount;
	private Boolean turnSwitch;
	private Turn turn;
	private Attack attack;
	private String targetInitiator;
	private int kill;
	private int fort;
	private int cache;
	private int rep;
	private CombatZone combatZone;
	private ContractsContainer contracts;
	private int repGainMod;
	private int repLossMod;
	
	
	public GameActionMessage(String username, Boolean readyForBattle, String lastEvent, Card directive, Boolean concede,
			Boolean rollPhase, int rollForFirst, Boolean goesFirst, Boolean mulliganPhase, Boolean keepHand,
			Boolean keepContracts, int deckCount, int handCount, Boolean turnSwitch, Turn turn, Attack attack,
			String targetInitiator, int kill, int fort, int cache, int rep, CombatZone combatZone,
			ContractsContainer contracts, int repGainMod, int repLossMod) {
		super();
		this.username = username;
		this.readyForBattle = readyForBattle;
		this.lastEvent = lastEvent;
		this.directive = directive;
		this.concede = concede;
		this.rollPhase = rollPhase;
		this.rollForFirst = rollForFirst;
		this.goesFirst = goesFirst;
		this.mulliganPhase = mulliganPhase;
		this.keepHand = keepHand;
		this.keepContracts = keepContracts;
		this.deckCount = deckCount;
		this.handCount = handCount;
		this.turnSwitch = turnSwitch;
		this.turn = turn;
		this.attack = attack;
		this.targetInitiator = targetInitiator;
		this.kill = kill;
		this.fort = fort;
		this.cache = cache;
		this.rep = rep;
		this.combatZone = combatZone;
		this.contracts = contracts;
		this.repGainMod = repGainMod;
		this.repLossMod = repLossMod;
	}
	
	
	public int getRepGainMod() {
		return repGainMod;
	}
	public void setRepGainMod(int repGainMod) {
		this.repGainMod = repGainMod;
	}
	public int getRepLossMod() {
		return repLossMod;
	}
	public void setRepLossMod(int repLossMod) {
		this.repLossMod = repLossMod;
	}
	public String getTargetInitiator() {
		return targetInitiator;
	}
	public void setTargetInitiator(String targetInitiator) {
		this.targetInitiator = targetInitiator;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getDeckCount() {
		return deckCount;
	}
	public void setDeckCount(int deckCount) {
		this.deckCount = deckCount;
	}
	public int getHandCount() {
		return handCount;
	}
	public void setHandCount(int handCount) {
		this.handCount = handCount;
	}
	public CombatZone getCombatZone() {
		return combatZone;
	}
	public void setCombatZone(CombatZone combatZone) {
		this.combatZone = combatZone;
	}
	public int getFort() {
		return fort;
	}
	public void setFort(int fort) {
		this.fort = fort;
	}
	public int getCache() {
		return cache;
	}
	public void setCache(int cache) {
		this.cache = cache;
	}
	public int getRep() {
		return rep;
	}
	public void setRep(int rep) {
		this.rep = rep;
	}
	public ContractsContainer getContracts() {
		return contracts;
	}
	public void setContracts(ContractsContainer contracts) {
		this.contracts = contracts;
	}
	public Attack getAttack() {
		return attack;
	}
	public void setAttack(Attack attack) {
		this.attack = attack;
	}
	public String getLastEvent() {
		return lastEvent;
	}
	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
	public Card getDirective() {
		return directive;
	}
	public void setDirective(Card directive) {
		this.directive = directive;
	}
	public int getRollForFirst() {
		return rollForFirst;
	}
	public void setRollForFirst(int rollForFirst) {
		this.rollForFirst = rollForFirst;
	}
	public Boolean getTurnSwitch() {
		return turnSwitch;
	}
	public void setTurnSwitch(Boolean turnSwitch) {
		this.turnSwitch = turnSwitch;
	}
	public Boolean getRollPhase() {
		return rollPhase;
	}
	public void setRollPhase(Boolean rollPhase) {
		this.rollPhase = rollPhase;
	}
	public Boolean getMulliganPhase() {
		return mulliganPhase;
	}
	public void setMulliganPhase(Boolean mulliganPhase) {
		this.mulliganPhase = mulliganPhase;
	}
	public Boolean getConcede() {
		return concede;
	}
	public void setConcede(Boolean concede) {
		this.concede = concede;
	}
	public Boolean getKeepContracts() {
		return keepContracts;
	}
	public void setKeepContracts(Boolean keepContracts) {
		this.keepContracts = keepContracts;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getReadyForBattle() {
		return readyForBattle;
	}
	public void setReadyForBattle(Boolean readyForBattle) {
		this.readyForBattle = readyForBattle;
	}
	public Boolean getKeepHand() {
		return keepHand;
	}
	public void setKeepHand(Boolean keepHand) {
		this.keepHand = keepHand;
	}
	public Boolean getGoesFirst() {
		return goesFirst;
	}
	public void setGoesFirst(Boolean goesFirst) {
		this.goesFirst = goesFirst;
	}
	public Turn getTurn() {
		return turn;
	}
	public void setTurn(Turn turn) {
		this.turn = turn;
	}
}
