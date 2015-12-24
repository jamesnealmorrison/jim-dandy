package com.jimmie.domain.classes;

import java.io.Serializable;
import java.util.List;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;

public abstract class DndClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fortitudeBonus;
	private int willBonus;
	private int reflexBonus;
	protected DndCharacter owner; // Pointer back to the player character
	public abstract void initializeForEncounter();
	public abstract void initializeForNewDay(DndCharacter dndCharacter);
	public abstract List<String> selectInitialSkills();
	public abstract void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc);
	public abstract void makeClassChoicesAfterAbilityScores(PlayerCharacter pc);
	public abstract int getArmorClassBonus();
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

	public DndCharacter getOwner() {
		return owner;
	}
	public void setOwner(DndCharacter owner) {
		this.owner = owner;
	}
}
