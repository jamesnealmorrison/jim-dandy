package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.classes.BardicVirtue;
import com.jimmie.domain.creatures.PlayerCharacter;

public class StrengthOfValor extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.STRENGTH_OF_VALOR;
	}

	@Override
	public String getName() {
		return "Strength Of Valor";
	}

	@Override
	public String getBenefit() {
		return "Virtue of valor also grants +2 damage";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Bard.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Bard) pc.getDndClass()).getBardicVirtue() == BardicVirtue.VIRTUE_OF_VALOR) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
