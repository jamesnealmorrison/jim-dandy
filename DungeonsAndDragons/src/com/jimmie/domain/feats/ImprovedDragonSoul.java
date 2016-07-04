package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.SorcererSpellSource;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedDragonSoul extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_DRAGON_SOUL;
	}

	@Override
	public String getName() {
		return "Improved Dragon Soul";
	}

	@Override
	public String getBenefit() {
		return "Increase Dragon Soul resistance by 2";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Sorcerer.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Sorcerer) pc.getDndClass()).getSpellSource() == SorcererSpellSource.DRAGON_MAGIC) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
