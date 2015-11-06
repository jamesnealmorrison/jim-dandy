package com.jimmie.domain.feats;

import com.jimmie.domain.classes.EldritchPact;
import com.jimmie.domain.classes.Warlock;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedMistyStep extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_MISTY_STEP;
	}

	@Override
	public String getName() {
		return "Improved Misty Step";
	}

	@Override
	public String getBenefit() {
		return "Pact boon grants additional 2 squares of teleport";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getIntelligence() >= 13)  && (Warlock.class.isInstance(pc.getDndClass()))) {
			Warlock warlock = (Warlock) pc.getDndClass();
			if (warlock.getEldritchPact() == EldritchPact.FEY_PACT) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
