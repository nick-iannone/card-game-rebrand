package com.nonius.game.model;

public class Attack {
	
	private Card target;
	private Card attacker;
	private String type;
	private int value;
	private Boolean isModded;
	private int modValue;
	
	
	public Boolean getIsModded() {
		return isModded;
	}
	public void setIsModded(Boolean isModded) {
		this.isModded = isModded;
	}
	public int getModValue() {
		return modValue;
	}
	public void setModValue(int modValue) {
		this.modValue = modValue;
	}
	public Card getTarget() {
		return target;
	}
	public void setTarget(Card target) {
		this.target = target;
	}
	public Card getAttacker() {
		return attacker;
	}
	public void setAttacker(Card attacker) {
		this.attacker = attacker;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
