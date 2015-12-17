package com.jimmie.domain.creatures;

import java.io.Serializable;

import com.jimmie.domain.classes.DndClass;

public abstract class Race implements Serializable {
	private static final long serialVersionUID = 1L;
	private int fortitudeBonus;
	private int willBonus;
	private int reflexBonus;
	private int strengthBonus;
	private int constitutionBonus;
	private int dexterityBonus;
	private int intelligenceBonus;
	private int wisdomBonus;
	private int charismaBonus;

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
	
	public Race() {
		fortitudeBonus = 0;
		willBonus = 0;
		reflexBonus = 0;
		strengthBonus = 0;
		constitutionBonus = 0;
		dexterityBonus = 0;
		intelligenceBonus = 0;
		wisdomBonus = 0;
		charismaBonus = 0;
	}
}
