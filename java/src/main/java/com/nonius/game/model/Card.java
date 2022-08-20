package com.nonius.game.model;

public class Card {
	
	private int id;
	private String name;
	private String cost;
	private String imageName;
	private String abilities;
	private String flavor;
	private int attack;
	private int resilience;
	private int loyalty;
	private Boolean onEnter;
	private Boolean onLeave;
	private Boolean onAttack;
	private Boolean onDamage;
	private Boolean onTurnStart;
	private Boolean onTurnEnd;
	private Boolean isAttacking;
	private Boolean isTarget;
	private Boolean canAttack;
	private Boolean isHeroic;
	private Boolean isFabled;
	private Boolean isHallowed;
	private Boolean isVile;
	private Boolean isFeared;
	private Boolean isDreaded;
	private int count;
	

	public Card() {
		
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Boolean getIsHeroic() {
		return isHeroic;
	}
	public void setIsHeroic(Boolean isHeroic) {
		this.isHeroic = isHeroic;
	}
	public Boolean getIsFabled() {
		return isFabled;
	}
	public void setIsFabled(Boolean isFabled) {
		this.isFabled = isFabled;
	}
	public Boolean getIsHallowed() {
		return isHallowed;
	}
	public void setIsHallowed(Boolean isHallowed) {
		this.isHallowed = isHallowed;
	}
	public Boolean getOnEnter() {
		return onEnter;
	}
	public void setOnEnter(Boolean onEnter) {
		this.onEnter = onEnter;
	}
	public Boolean getOnLeave() {
		return onLeave;
	}
	public void setOnLeave(Boolean onLeave) {
		this.onLeave = onLeave;
	}
	public Boolean getOnAttack() {
		return onAttack;
	}
	public void setOnAttack(Boolean onAttack) {
		this.onAttack = onAttack;
	}
	public Boolean getOnDamage() {
		return onDamage;
	}
	public void setOnDamage(Boolean onDamage) {
		this.onDamage = onDamage;
	}
	public Boolean getOnTurnStart() {
		return onTurnStart;
	}
	public void setOnTurnStart(Boolean onTurnStart) {
		this.onTurnStart = onTurnStart;
	}
	public Boolean getOnTurnEnd() {
		return onTurnEnd;
	}
	public void setOnTurnEnd(Boolean onTurnEnd) {
		this.onTurnEnd = onTurnEnd;
	}
	public Boolean getCanAttack() {
		return canAttack;
	}
	public void setCanAttack(Boolean canAttack) {
		this.canAttack = canAttack;
	}
	public Boolean getIsAttacking() {
		return isAttacking;
	}
	public void setIsAttacking(Boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	public Boolean getIsTarget() {
		return isTarget;
	}
	public void setIsTarget(Boolean isTarget) {
		this.isTarget = isTarget;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getAbilities() {
		return abilities;
	}
	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getResilience() {
		return resilience;
	}
	public void setResilience(int resilience) {
		this.resilience = resilience;
	}
	public int getLoyalty() {
		return loyalty;
	}
	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}
	public Boolean getIsVile() {
		return isVile;
	}
	public void setIsVile(Boolean isVile) {
		this.isVile = isVile;
	}
	public Boolean getIsFeared() {
		return isFeared;
	}
	public void setIsFeared(Boolean isFeared) {
		this.isFeared = isFeared;
	}
	public Boolean getIsDreaded() {
		return isDreaded;
	}
	public void setIsDreaded(Boolean isDreaded) {
		this.isDreaded = isDreaded;
	}
}
