package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorSpecializationChainmail extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_SPECIALIZATION_CHAINMAIL;
	}

	@Override
	public String getName() {
		return "Armor Specialization (Chainmail)";
	}

	@Override
	public String getBenefit() {
		return "+1 to AC with chainmail, reduce check penalty by 1";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if ((pc.getDexterity() >= 15) && (pc.getArmorGroupProficiencies().contains(ArmorGroup.CHAINMAIL))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
