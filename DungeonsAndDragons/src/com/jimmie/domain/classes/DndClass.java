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

	public String getClassFeaturesText1() {
		return null;
	}

	public String getClassFeaturesText2() {
		return null;
	}

	public String getClassFeaturesText3() {
		return null;
	}

	public String getClassFeaturesText4() {
		return null;
	}

	public String getClassFeaturesText5() {
		return null;
	}

	public String getClassFeaturesText6() {
		return null;
	}

	public String getClassFeaturesText7() {
		return null;
	}

	public String getClassFeaturesText8() {
		return null;
	}

	public String getClassFeaturesText9() {
		return null;
	}

	public String getClassFeaturesText10() {
		return null;
	}

	public String getClassFeaturesText11() {
		return null;
	}

	public String getClassFeaturesText12() {
		return null;
	}

	public String getClassFeaturesText13() {
		return null;
	}

	public String getClassFeaturesText14() {
		return null;
	}
	
}
