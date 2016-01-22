package com.jimmie.powers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.Hand;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.domain.items.weapons.WeaponHandType;
import com.jimmie.util.Utils;

public class ReadyWeapon extends Power {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Ready a Weapon";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.AT_WILL;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.NONE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.NONE;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NONE);
		return damageTypes;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.MINOR;
	}

	@Override
	public int getRangeNumber1() {
		return 1;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			ReadiedWeapon readiedWeapon = new ReadiedWeapon();
			Utils.print(user.getName() + " currently has a " + user.getReadiedWeapon().getWeapon().getName() + " readied.");
			Utils.print("Pick a weapon to ready.");
			DndCharacter character = (DndCharacter) user;
			int i = 0; 
			HashMap<Integer, Weapon> validChoices = new HashMap<Integer, Weapon>();
			for (Weapon weapon : character.getWeapons()) {
				i++;
				Utils.print(i + ". " + weapon.getName());
			}
			Utils.print("Your choice:");
			int choice = Utils.getValidIntInputInRange(1, i);
			Weapon weapon = validChoices.get(choice);
			readiedWeapon.setWeapon(weapon);
			// TODO: see if they need to put down something else, or carry it in the off hand, etc.
			if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
				readiedWeapon.setHand(Hand.BOTH_HANDS);
			} else {
				readiedWeapon.setHand(Hand.MAIN_HAND);
			}
			character.addReadiedWeapon(readiedWeapon);
		}
		
		
		
		return true;
	}

	@Override
	public boolean isBasicAttack() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getRacesThatCanUsePower() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getClassesThatCanUsePower() {
		return null;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		return true;
	}
}
