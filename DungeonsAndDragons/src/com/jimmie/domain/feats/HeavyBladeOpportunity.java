package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HeavyBladeOpportunity extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HEAVY_BLADE_OPPORTUNITY;
	}

	@Override
	public String getName() {
		return "Heavy Blade Opportunity";
	}

	@Override
	public String getBenefit() {
		return "Use at-will power with opportunity attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if ((pc.getDexterity() >= 15) && (pc.getStrength() >= 15)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
