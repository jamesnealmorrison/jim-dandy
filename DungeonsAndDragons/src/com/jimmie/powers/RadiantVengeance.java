package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class RadiantVengeance extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.RANGED_NUMBER;
	}

	@Override
	public String getName() {
		return "Radiant Vengeance";
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
		return PowerSource.DIVINE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.RADIANT;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 10;
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
		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			int targetReflex = target.getReflex(user);
			Utils.print("Your target has a reflex of " + targetReflex);

			int attackRoll = user.attackRoll(AbilityType.WISDOM, getAccessoryType(), target);

			if (attackRoll >= targetReflex) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 1;
				DiceType damageDiceType = DiceType.EIGHT_SIDED;

				/* Book says at level 21 increase damage to 2[W]. */
				if (user.getLevel() >= 21) {
					damageRolls = 2;
				}

				boolean aspectOfMightEncounterBonus = false;
				if (Avenger.class.isAssignableFrom(user.getDndClass().getClass())) {
					aspectOfMightEncounterBonus = ((Avenger) user.getDndClass()).isAspectOfMightEncounterBonus();
				}

				if (aspectOfMightEncounterBonus == false) {
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, 0, user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user), DamageType.NORMAL, true, user);
				} else {
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, 2, user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user), DamageType.NORMAL, true, user);
					Utils.print("You got an aspect of might bonus of two to this damage roll.");
				}

				Utils.print(user.getName() + " gets " + user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + " temporary HP");
				/* Only do this if they have < user.getWisdomModifier() already.  Otherwise we are removing temp hit points they already had. */
				if (user.getTemporaryHitPoints() < user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM)) {
					user.setTemporaryHitPoints(user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM));
				}

			} else {
				Utils.print("You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user, this);
			}
			return true;
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
		List<Class> classes = new ArrayList<Class>();
		classes.add(Avenger.class);
		return classes;
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
