package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BladeOpportunist extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BLADE_OPPORTUNIST;
	}

	@Override
	public String getName() {
		return "Blade Opportunist";
	}

	@Override
	public String getBenefit() {
		return "+2 to opportunity attacks with heavy blade or light blade";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getDexterity() >= 13) && (pc.getStrength() >= 13)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
