package com.jimmie.domain.feats;

import com.jimmie.domain.classes.EldritchPact;
import com.jimmie.domain.classes.Warlock;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedDarkOnesBlessing extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_DARK_ONES_BLESSING;
	}

	@Override
	public String getName() {
		return "Improved Dark One's Blessing";
	}

	@Override
	public String getBenefit() {
		return "Pact boon grants 3 additional temporary hit points";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getConstitution() >= 15) && (Warlock.class.isInstance(pc.getDndClass()))) {
			Warlock warlock = (Warlock) pc.getDndClass();
			if (warlock.getEldritchPact() == EldritchPact.INFERNAL_PACT) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
