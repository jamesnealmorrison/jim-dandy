package com.jimmie.domain.feats;

import java.io.Serializable;

import com.jimmie.domain.creatures.PlayerCharacter;

public abstract class Feat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract FeatType getType();
	public abstract String getName();
	public abstract String getBenefit();
	public abstract boolean meetsPrerequisites(PlayerCharacter pc);
	public abstract void makeFeatChoices(PlayerCharacter pc);
	public int getArmorClassBonus() {
		// TODO: The expectation is that I'll override this in any feat that has an armor class bonus.
		return 0;
	}
}
