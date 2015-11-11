package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Eladrin;
import com.jimmie.domain.creatures.PlayerCharacter;

public class FeywildProtection extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FEYWILD_PROTECTION;
	}

	@Override
	public String getName() {
		return "Feywild Protection";
	}

	@Override
	public String getBenefit() {
		return "+2 to defenses when you use fey step";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if (Eladrin.class.isInstance(pc.getRace())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
