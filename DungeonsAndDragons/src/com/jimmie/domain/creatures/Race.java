package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.classes.DndClass;

public abstract class Race implements Serializable {
	private List<String> myPowers;
	
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
	public int getStrengthBonus() {
		return 0;
	}
	public int getConstitutionBonus() {
		return 0;
	}
	public int getDexterityBonus() {
		return 0;
	}
	public int getIntelligenceBonus() {
		return 0;
	}
	public int getWisdomBonus() {
		return 0;
	}
	public int getCharismaBonus() {
		return 0;
	}
	public abstract int getRacialDamageBonus();
	public abstract void initializeForEncounter();
	public abstract void initializeForNewDay();
	public abstract void processAfterHurtEffects(Creature creature);
	public abstract void makeRaceChoices(PlayerCharacter pc, DndClass dndClass);
}
