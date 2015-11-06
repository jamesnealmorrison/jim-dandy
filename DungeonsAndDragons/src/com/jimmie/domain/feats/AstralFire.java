package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class AstralFire extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ASTRAL_FIRE;
	}

	@Override
	public String getName() {
		return "Astral Fire";
	}

	@Override
	public String getBenefit() {
		return "+1 damage with fire or radiant power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getDexterity() >= 13) && (pc.getCharisma() >= 13)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
