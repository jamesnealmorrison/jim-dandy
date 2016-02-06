package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Rogue;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.domain.items.weapons.WeaponGroup;
import com.jimmie.util.Utils;

public class DeftStrike extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_OR_RANGED_WEAPON;
	}

	@Override
	public String getName() {
		return "Deft Strike";
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
		return PowerSource.MARTIAL;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.WEAPON;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.NORMAL;
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
		Utils.print("Sorry, but I haven't implemented this power yet.");
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
		List<Class> classes = new ArrayList<Class>();
		classes.add(Rogue.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		for (ReadiedWeapon readiedWeapon : user.getReadiedWeapons().values()) {
			if ((readiedWeapon.getWeapon().getWeaponGroups().contains(WeaponGroup.CROSSBOW)) ||
					(readiedWeapon.getWeapon().getWeaponGroups().contains(WeaponGroup.LIGHT_BLADE)) || 
					(readiedWeapon.getWeapon().getWeaponGroups().contains(WeaponGroup.SLING))) {
				return true;
			}
		}
		return false;
	}

}
