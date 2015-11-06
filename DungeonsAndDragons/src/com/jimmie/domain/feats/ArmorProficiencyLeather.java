package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorProficiencyLeather extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_PROFICIENCY_LEATHER;
	}

	@Override
	public String getName() {
		return "Armor Proficiency Leather";
	}

	@Override
	public String getBenefit() {
		return "Training with leather armor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.LEATHER)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
