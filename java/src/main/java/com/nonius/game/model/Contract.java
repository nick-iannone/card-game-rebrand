package com.nonius.game.model;

public class Contract {
	
	private int id;
	private String name;
	private String cost;
	private String desc;
	private int dura;
	private Boolean isActive;
	private Boolean hasCounters;
	private int counters;
	private int count;
	
	public Contract() {
		this.counters = 0;
	}
	
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Boolean getHasCounters() {
		return hasCounters;
	}
	public void setHasCounters(Boolean hasCounters) {
		this.hasCounters = hasCounters;
	}
	public int getCounters() {
		return counters;
	}
	public void setCounters(int counters) {
		this.counters = counters;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getDura() {
		return dura;
	}
	public void setDura(int dura) {
		this.dura = dura;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
