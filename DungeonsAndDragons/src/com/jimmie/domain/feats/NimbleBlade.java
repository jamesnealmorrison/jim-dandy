package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class NimbleBlade extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.NIMBLE_BLADE;
	}

	@Override
	public String getName() {
		return "Nimble Blade";
	}

	@Override
	public String getBenefit() {
		return "+1 to attacks with light blade and combat advantage";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 15) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
