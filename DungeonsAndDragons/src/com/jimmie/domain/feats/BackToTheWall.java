package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BackToTheWall extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BACK_TO_THE_WALL;
	}

	@Override
	public String getName() {
		return "Back To The Wall";
	}

	@Override
	public String getBenefit() {
		return "+1 to melee attack, damage, AC when adjacent to a wall";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
