package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorSpecializationScale extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_SPECIALIZATION_SCALE;
	}

	@Override
	public String getName() {
		return "Armor Specialization (Scale)";
	}

	@Override
	public String getBenefit() {
		return "Ignore speed penalty of scale armor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if ((pc.getDexterity() >= 15) && (pc.getArmorGroupProficiencies().contains(ArmorGroup.SCALE))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
