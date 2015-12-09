package com.jimmie.domain.feats;

import com.jimmie.domain.classes.MonasticTradition;
import com.jimmie.domain.classes.Monk;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PointedStepStyle extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POINTED_STEP_STYLE;
	}

	@Override
	public String getName() {
		return "Pointed Step Style";
	}

	@Override
	public String getBenefit() {
		return "Attack from 2 squares away when using spear with Flurry of Blows";
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
