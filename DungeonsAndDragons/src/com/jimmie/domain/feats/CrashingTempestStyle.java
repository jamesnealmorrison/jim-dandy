package com.jimmie.domain.feats;

import com.jimmie.domain.classes.MonasticTradition;
import com.jimmie.domain.classes.Monk;
import com.jimmie.domain.creatures.PlayerCharacter;

public class CrashingTempestStyle extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CRASHING_TEMPEST_STYLE;
	}

	@Override
	public String getName() {
		return "Crashing Tempest Style";
	}

	@Override
	public String getBenefit() {
		return "+2 to Flurry of Blows damage when wielding club";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Monk.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Monk) pc.getDndClass()).getMonasticTradition() == MonasticTradition.CENTERED_BREATH) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
