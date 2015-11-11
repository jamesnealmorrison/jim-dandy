package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class RagingStorm extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RAGING_STORM;
	}

	@Override
	public String getName() {
		return "Raging Storm";
	}

	@Override
	public String getBenefit() {
		return "+1 damage with lightning or thunder power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getConstitution() >= 13) && (pc.getDexterity() >= 13)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
