package com.jimmie.domain.feats;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.util.Utils;

public class WeaponProficiency extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String proficientWeapon;

	@Override
	public FeatType getType() {
		return FeatType.WEAPON_PROFICIENCY;
	}

	@Override
	public String getName() {
		return "Weapon Proficiency";
	}

	@Override
	public String getBenefit() {
		return "Gain proficiency with the weapon of your choice";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		Utils.print("Choose a weapon group to focus on:");
		List<String> choices = new ArrayList<String>();
		
		choices.add("Club");
		choices.add("Dagger");
		choices.add("Javelin");
		choices.add("Mace");
		choices.add("Sickle");
		choices.add("Spear");
		choices.add("Greatclub");
		choices.add("Morningstar");
		choices.add("Quarterstaff");
		choices.add("Scythe");
		choices.add("Battleaxe");
		choices.add("Flail");
		choices.add("Handaxe");
		choices.add("Longsword");
		choices.add("Scimitar");
		choices.add("Shortsword");
		choices.add("Throwing Hammer");
		choices.add("Warhammer");
		choices.add("War Pick");
		choices.add("Falchion");
		choices.add("Glaive");
		choices.add("Greataxe");
		choices.add("Greatsword");
		choices.add("Halberd");
		choices.add("Heavyflail");
		choices.add("Longspear");
		choices.add("Maul");
		choices.add("Bastard Sword");
		choices.add("Katar");
		choices.add("Rapier");
		choices.add("Spiked Chain");
		choices.add("Hand Crossbow");
		choices.add("Sling");
		choices.add("Longbow");
		choices.add("Shortbow");
		choices.add("Shuriken");
		
		Utils.print("Your choice:");
		proficientWeapon = Utils.getValidInput(choices);
	}

}
