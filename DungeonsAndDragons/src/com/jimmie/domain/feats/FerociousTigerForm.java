package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.PlayerCharacter;

public class FerociousTigerForm extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FEROCIOUS_TIGER_FORM;
	}

	@Override
	public String getName() {
		return "Ferocious Tiger Form";
	}

	@Override
	public String getBenefit() {
		return "+2 damage with combat advantage in beast form";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Druid.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
