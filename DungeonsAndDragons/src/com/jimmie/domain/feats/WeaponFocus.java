package com.jimmie.domain.feats;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.util.Utils;

public class WeaponFocus extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String focusedWeapon;

	@Override
	public FeatType getType() {
		return FeatType.WEAPON_FOCUS;
	}

	@Override
	public String getName() {
		return "Weapon Focus";
	}

	@Override
	public String getBenefit() {
		return "+1 damage with chosen weapon group";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		Utils.print("Choose a weapon group to focus on:");
		List<String> choices = new ArrayList<String>();
		
		choices.add("Mace");
		choices.add("Light Blade");
		choices.add("Spear");
		choices.add("Staff");
		choices.add("Heavy Blade");
		choices.add("Axe");
		choices.add("Flail");
		choices.add("Hammer");
		choices.add("Pick");
		choices.add("Polearm");
		choices.add("Crossbow");
		choices.add("Sling");
		choices.add("Bow");
		
		Utils.print("Your choice:");
		focusedWeapon = Utils.getValidInput(choices);
	}

}
