package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;
import com.jimmie.domain.creatures.DndCharacter;


public class LayOnHands extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_TOUCH;
	}

	@Override
	public String getName() {
		return "Lay on Hands";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		// TODO: I don't think there is really a need to implement an "At-Will (Special)"
		return PowerUsage.AT_WILL;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.DIVINE;
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
		return 0;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.HEALING);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {
		Utils.print("Sorry, but I haven't implemented this power yet.");
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
		List<Class> classes = new ArrayList<Class>();
		classes.add(Paladin.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			if (((DndCharacter) user).getCurrentSurgeUses() < ((DndCharacter) user).getHealingSurgesPerDay()) {
				return true;
			}
		}
		return false;
	}
}
