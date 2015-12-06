package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.SorcererSpellSource;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DisciplinedWildSoul extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DISCIPLINED_WILD_SOUL;
	}

	@Override
	public String getName() {
		return "Disciplined Wild Soul";
	}

	@Override
	public String getBenefit() {
		return "Roll twice to determine Wild Soul damage type";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Sorcerer.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Sorcerer) pc.getDndClass()).getSpellSource() == SorcererSpellSource.WILD_MAGIC) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
