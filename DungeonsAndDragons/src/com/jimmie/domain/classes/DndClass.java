package com.jimmie.domain.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.PlayerCharacter;

public abstract class DndClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> myPowers;
	private List<String> myOptions;
	
	public List<String> getMyPowers() {
		return myPowers;
	}
	public void setMyPowers(List<String> myPowers) {
		this.myPowers = myPowers;
	}
	public List<String> getMyOptions() {
		return myOptions;
	}
	public void setMyOptions(List<String> myOptions) {
		this.myOptions = myOptions;
	}
	public Character getOwner() {
		return owner;
	}
	protected Character owner; // Pointer back to the player character
	public void setOwner(Character owner) {
		this.owner = owner;
	}
	public DndClass() {
		myPowers = new ArrayList<String>();
		myOptions = new ArrayList<String>();
	}
	public void addPower(String powerId) {
		myPowers.add(powerId);
	}
	public void addOption(String optionId) {
		myOptions.add(optionId);
	}
	public abstract void initializeForEncounter();
	public abstract void initializeForNewDay();
	public abstract List<String> selectInitialSkills();
	public abstract void makeClassChoices(PlayerCharacter pc);
}
