package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.classes.DndClass;

public abstract class Race implements Serializable {
	private List<String> myPowers;
	private int fortitudeBonus = 0;
	private int willBonus = 0;
	private int reflexBonus = 0;
	
	public Race() {
		myPowers = new ArrayList<String>();
	}
	public void addPower(String powerId) {
		myPowers.add(powerId);
	}

	public List<String> getMyPowers() {
		return myPowers;
	}
	public void setMyPowers(List<String> myPowers) {
		this.myPowers = myPowers;
	}
	public Character getOwner() {
		return owner;
	}
	protected Character owner; // Pointer back to the player character
	public void setOwner(Character owner) {
		this.owner = owner;
	}
	private static final long serialVersionUID = 1L;
	public abstract int getRacialDamageBonus();
	public abstract void initializeForEncounter();
	public abstract void initializeForNewDay();
	public abstract void makeRaceChoices(PlayerCharacter pc, DndClass dndClass);
	public abstract void makeRacialAbilityScoreAdjustments(PlayerCharacter pc, DndClass dndClass);

	public int getFortitudeBonus() {
		return fortitudeBonus;
	}
	public void setFortitudeBonus(int fortitudeBonus) {
		this.fortitudeBonus = fortitudeBonus;
	}
	public int getWillBonus() {
		return willBonus;
	}
	public void setWillBonus(int willBonus) {
		this.willBonus = willBonus;
	}
	public int getReflexBonus() {
		return reflexBonus;
	}
	public void setReflexBonus(int reflexBonus) {
		this.reflexBonus = reflexBonus;
	}
	public void setStrengthBonus(int strengthBonus) {
		this.strengthBonus = strengthBonus;
	}
	public void setConstitutionBonus(int constitutionBonus) {
		this.constitutionBonus = constitutionBonus;
	}
	public void setDexterityBonus(int dexterityBonus) {
		this.dexterityBonus = dexterityBonus;
	}
	public void setIntelligenceBonus(int intelligenceBonus) {
		this.intelligenceBonus = intelligenceBonus;
	}
	public void setWisdomBonus(int wisdomBonus) {
		this.wisdomBonus = wisdomBonus;
	}
	public void setCharismaBonus(int charismaBonus) {
		this.charismaBonus = charismaBonus;
	}
	public int getStrengthBonus() {
		return strengthBonus;
	}
	public int getConstitutionBonus() {
		return constitutionBonus;
	}
	public int getDexterityBonus() {
		return dexterityBonus;
	}
	public int getIntelligenceBonus() {
		return intelligenceBonus;
	}
	public int getWisdomBonus() {
		return wisdomBonus;
	}
	public int getCharismaBonus() {
		return charismaBonus;
	}
	private int strengthBonus = 0;
	private int constitutionBonus = 0;
	private int dexterityBonus = 0;
	private int intelligenceBonus = 0;
	private int wisdomBonus = 0;
	private int charismaBonus = 0;}
