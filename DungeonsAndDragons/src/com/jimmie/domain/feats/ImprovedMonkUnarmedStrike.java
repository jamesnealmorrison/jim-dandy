package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Monk;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedMonkUnarmedStrike extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_MONK_UNARMED_STRIKE;
	}

	@Override
	public String getName() {
		return "Improved Monk Unarmed Strike";
	}

	@Override
	public String getBenefit() {
		return "Damage die of your unarmed strike improves to 1d10";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Monk.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
