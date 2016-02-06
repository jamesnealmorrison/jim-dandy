package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DefenseType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class GuardDrakeBite extends GenericAttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_NUMBER;
	}

	@Override
	public String getName() {
		return "Bite";
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
		return DamageType.NORMAL;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
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
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		return true;
	}

	@Override
	public boolean isBasicAttack() {
		return true;
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
	public AmmunitionType getAmmunitionType() {
		return AmmunitionType.NONE;
	}

	@Override
	public boolean missCausesHalfDamage() {
		return false;
	}

	@Override
	public int getDamageBonus() {
		// If within 2 squares of an ally, it should be 1d10 + 9.
		List<Creature> allies = Encounter.getEncounter().getAlliesWithinRangeOf(user, user.getCurrentPosition(), 2);
		if ((allies != null) && (allies.size() > 0)) {
			Utils.print(getName() + " is within two squares of an ally, so doing 1d10+9 damage.");
			return 9;
		}
		return 3;
	}

	@Override
	public DiceType getDamageDiceType() {
		return DiceType.TEN_SIDED;
	}

	@Override
	public int getDamageRolls() {
		return 1;
	}

	@Override
	public DefenseType getTargetDefense() {
		return DefenseType.ARMOR_CLASS;
	}

	@Override
	public int getAttackModifier() {
		return 6;
	}

}
