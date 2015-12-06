package com.jimmie.domain.feats;

import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.PlayerCharacter;

public class WildbloodCunning extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WILDBLOOD_CUNNING;
	}

	@Override
	public String getName() {
		return "Wildblood Cunning";
	}

	@Override
	public String getBenefit() {
		return "Shift when you use second wind";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Warden.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (GuardianMight.WILDBLOOD == ((Warden) pc.getDndClass()).getGuardianMight()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
