package com.nonius.game.model;

public class Turn {
	
	private Boolean begin;
	private Boolean end;
	private Boolean isTurn;
	private int count;
	
	
	public Boolean getIsTurn() {
		return isTurn;
	}
	public void setIsTurn(Boolean isTurn) {
		this.isTurn = isTurn;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Boolean getBegin() {
		return begin;
	}
	public void setBegin(Boolean begin) {
		this.begin = begin;
	}
	public Boolean getEnd() {
		return end;
	}
	public void setEnd(Boolean end) {
		this.end = end;
	}
}
