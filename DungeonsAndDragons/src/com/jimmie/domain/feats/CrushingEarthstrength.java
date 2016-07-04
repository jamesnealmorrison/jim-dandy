package com.jimmie.domain.feats;

import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.PlayerCharacter;

public class CrushingEarthstrength extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CRUSHING_EARTHSTRENGTH;
	}

	@Override
	public String getName() {
		return "Crushing Earthstrength";
	}

	@Override
	public String getBenefit() {
		return "Add Constitution modifier to damage after second wind";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Warden.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (GuardianMight.EARTHSTRENGTH == ((Warden) pc.getDndClass()).getGuardianMight()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
