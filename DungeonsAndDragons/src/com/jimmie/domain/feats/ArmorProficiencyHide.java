package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorProficiencyHide extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_PROFICIENCY_HIDE;
	}

	@Override
	public String getName() {
		return "Armor Proficiency Hide";
	}

	@Override
	public String getBenefit() {
		return "Training with hide armor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 13) && (pc.getConstitution() >= 13) && (pc.getArmorGroupProficiencies().contains(ArmorGroup.HIDE))) {
			if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.HIDE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
