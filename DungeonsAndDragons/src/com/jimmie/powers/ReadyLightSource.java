package com.jimmie.powers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.LightSource;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.Gear;
import com.jimmie.domain.items.weapons.Hand;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.util.Utils;

public class ReadyLightSource extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Ready Light Source";
	}

	@Override
	public int getLevel() {
		return 0;
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
	public DamageType getDamageType() {
		return DamageType.NONE;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 0;
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
		// See if they have a free hand.
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			DndCharacter character = (DndCharacter) user;
			if (character.getReadiedWeapons() != null) {
				int handsFree = 2;
				for (ReadiedWeapon weapon : character.getReadiedWeapons().values()) {
					if (weapon.getHand() == Hand.BOTH_HANDS) {
						handsFree = 0;
					} else if (weapon.getWeapon().getWeaponType() != WeaponType.UNARMED_ATTACK) {
						handsFree--;
					}
				}
				if (handsFree > 0) {
					HashMap<Integer, LightSource> choices = new HashMap<Integer, LightSource>();
					int i = 0;
					Utils.print("Which lightsource do you want to use?");
					// See if they have a lightsource in their gear.
					for (Gear gear : character.getGear()) {						
						if (LightSource.class.isAssignableFrom(gear.getClass())) {
							i++;
							Utils.print(i + ". " + gear.getName());
							choices.put(i, (LightSource) gear);
						}
					}
					Utils.print("Your choice:");
					int choice = Utils.getValidIntInputInRange(1, i);
					user.setReadiedLightSource(choices.get(choice));
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (user.getReadiedLightSource() == null) {
			// See if they have a free hand.
			if (DndCharacter.class.isAssignableFrom(user.getClass())) {
				DndCharacter character = (DndCharacter) user;
				if (character.getReadiedWeapons() != null) {
					int handsFree = 2;
					for (ReadiedWeapon weapon : character.getReadiedWeapons().values()) {
						if (weapon.getHand() == Hand.BOTH_HANDS) {
							handsFree = 0;
						} else if (weapon.getWeapon().getWeaponType() != WeaponType.UNARMED_ATTACK) {
							handsFree--;
						}
					}
					if (handsFree > 0) {
						// See if they have a lightsource in their gear.
						for (Gear gear : character.getGear()) {
							if (LightSource.class.isAssignableFrom(gear.getClass())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
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

}
