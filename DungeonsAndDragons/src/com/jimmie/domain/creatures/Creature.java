package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.List;

public abstract class Creature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int level;
	protected Origin origin;
	protected CreatureType type;
	/* 
	 * TODO - D&D Rules Compendium page 61 mentions an attribute called Race.
	 */
	
	/*
	 * TODO - Class needs to be done also.  Will it be a Creature attribute or PlayerCharacter?
	 */
	
	protected List<Keyword> keywords;
	protected Size size;
	protected List<Role> roles;
	protected AbilityScore strength;
	protected AbilityScore constitution;
	protected AbilityScore dexterity;
	protected AbilityScore intelligence;
	protected AbilityScore wisdom;
	protected AbilityScore charisma;
	protected int maximumHitPoints;
	protected int currentHitPoints;
	
	public abstract int getArmorClass();
	public abstract int getFortitude();
	public abstract int getReflex();
	public abstract int getWill();
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public CreatureType getType() {
		return type;
	}
	public void setType(CreatureType type) {
		this.type = type;
	}
	public List<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public AbilityScore getStrength() {
		return strength;
	}
	public void setStrength(AbilityScore strength) {
		this.strength = strength;
	}
	public AbilityScore getConstitution() {
		return constitution;
	}
	public void setConstitution(AbilityScore constitution) {
		this.constitution = constitution;
	}
	public AbilityScore getDexterity() {
		return dexterity;
	}
	public void setDexterity(AbilityScore dexterity) {
		this.dexterity = dexterity;
	}
	public AbilityScore getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(AbilityScore intelligence) {
		this.intelligence = intelligence;
	}
	public AbilityScore getWisdom() {
		return wisdom;
	}
	public void setWisdom(AbilityScore wisdom) {
		this.wisdom = wisdom;
	}
	public AbilityScore getCharisma() {
		return charisma;
	}
	public void setCharisma(AbilityScore charisma) {
		this.charisma = charisma;
	}
	public int getMaximumHitPoints() {
		return maximumHitPoints;
	}
	public void setMaximumHitPoints(int maximumHitPoints) {
		this.maximumHitPoints = maximumHitPoints;
	}
	public int getCurrentHitPoints() {
		return currentHitPoints;
	}
	public void setCurrentHitPoints(int currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}
	
}
