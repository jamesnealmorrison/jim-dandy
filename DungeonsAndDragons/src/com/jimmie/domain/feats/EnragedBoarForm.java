package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.PlayerCharacter;

public class EnragedBoarForm extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ENRAGED_BOAR_FORM;
	}

	@Override
	public String getName() {
		return "Enraged Boar Form";
	}

	@Override
	public String getBenefit() {
		return "+1 attack, +2 damage when charging in beast form";
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
