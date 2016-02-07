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
import com.jimmie.domain.items.Gear;
import com.jimmie.domain.items.Potion;
import com.jimmie.util.Utils;

public class ReadyPotion extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Ready a Potion";
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
		return ActionType.MINOR;
	}

	@Override
	public int getRangeNumber1() {
		return 1;
	}

	@Override
	public int getRangeNumber2() {
		return 1;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		HashMap<Integer, Potion> choices = new HashMap<Integer, Potion>();
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			DndCharacter dndChar = (DndCharacter) user;
			Utils.print("Which potion do you want to ready?");
			int i = 0;
			for (Gear gear : dndChar.getGear()) {
				if (Potion.class.isAssignableFrom(gear.getClass())) {
					Potion potion = (Potion) gear;
					i++;
					Utils.print(i + ". " + potion.getName());
					choices.put(i, potion);
				}
			}
			Utils.print("Your choice:" );
			int choice = Utils.getValidIntInputInRange(1, i);
			user.setReadiedPotion(choices.get(choice));
		}
		return true;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (user.getReadiedPotion() == null) {
			if (DndCharacter.class.isAssignableFrom(user.getClass())) {
				DndCharacter dndChar = (DndCharacter) user;
				for (Gear gear : dndChar.getGear()) {
					if (Potion.class.isAssignableFrom(gear.getClass())) {
						return true;
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
