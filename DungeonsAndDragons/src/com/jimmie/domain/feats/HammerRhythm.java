package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HammerRhythm extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HAMMER_RHYTHM;
	}

	@Override
	public String getName() {
		return "Hammer Rhythm";
	}

	@Override
	public String getBenefit() {
		return "Damage with hammer or mace on a miss";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if ((pc.getStrength() >= 15) && (pc.getConstitution() >= 17)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}
}
