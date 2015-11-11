package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorProficiencyChainmail extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_PROFICIENCY_CHAINMAIL;
	}

	@Override
	public String getName() {
		return "Armor Proficiency Chainmail";
	}

	@Override
	public String getBenefit() {
		return "Training with chanimail armor.";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 13) && (pc.getConstitution() >= 13) && ((pc.getArmorGroupProficiencies().contains(ArmorGroup.LEATHER) || (pc.getArmorGroupProficiencies().contains(ArmorGroup.HIDE))))) {
			if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.CHAINMAIL)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
