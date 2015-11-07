package com.jimmie.domain.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.PowerId;
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.PlayerCharacter;

public abstract class DndClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PowerId> myPowers;
	
	public List<PowerId> getMyPowers() {
		return myPowers;
	}
	public void setMyPowers(List<PowerId> myPowers) {
		this.myPowers = myPowers;
	}
	public Character getOwner() {
		return owner;
	}
	protected Character owner; // Pointer back to the player character
	public void setOwner(Character owner) {
		this.owner = owner;
	}
	public DndClass() {
		myPowers = new ArrayList<PowerId>();
	}
	public void addPower(PowerId powerId) {
		myPowers.add(powerId);
	}
	public abstract void initializeForEncounter();
	public abstract void initializeForNewDay();
	public abstract List<String> selectInitialSkills();
	public abstract void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc);
	public abstract void makeClassChoicesAfterAbilityScores(PlayerCharacter pc);
}
