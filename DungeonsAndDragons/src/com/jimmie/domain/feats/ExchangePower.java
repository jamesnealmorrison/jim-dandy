package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.classes.PsionDiscipline;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ExchangePower extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.EXCHANGE_POWER;
	}

	@Override
	public String getName() {
		return "Exchange Power";
	}

	@Override
	public String getBenefit() {
		return "Transfer 1 power point to ally when using send thoughts";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Psion.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Psion) pc.getDndClass()).getDisciplineFocus() == PsionDiscipline.TELEPATHY_FOCUS) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
