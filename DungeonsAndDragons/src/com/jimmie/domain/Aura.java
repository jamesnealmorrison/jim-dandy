package com.jimmie.domain;

import java.io.Serializable;
import java.util.List;

public class Aura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String keyword; // This is the word in parenthesis in the book.
	private int range;
	private List<AuraEffect> effects;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public List<AuraEffect> getEffects() {
		return effects;
	}
	public void setEffects(List<AuraEffect> effects) {
		this.effects = effects;
	}
}
