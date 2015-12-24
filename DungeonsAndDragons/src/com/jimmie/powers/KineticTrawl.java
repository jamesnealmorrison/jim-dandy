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
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class KineticTrawl extends AttackPower {
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
		return "Kinetic Trawl";
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
		return PowerSource.PSIONIC;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.FORCE);
		return damageTypes;
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
	public void process(Creature user) {
		/* See if they want to augment. */
		int augment = 0;
		int range = 0;

		DndCharacter c = null;
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			c = (DndCharacter) user;
		}		

		Psion psion = null;
		int powerPoints = 0;
		if (Psion.class.isAssignableFrom(user.getDndClass().getClass())) {
			psion = (Psion) user.getDndClass();
			powerPoints = psion.getPowerPoints();
		}

		if (powerPoints > 0) {
			Utils.print("You have " + powerPoints + " power points left.  How many to you want to spend?");
			if (powerPoints > 2) {
				range = 2;
			} else {
				range = powerPoints;
			}
			augment = Utils.getValidIntInputInRange(0, range);
			powerPoints = powerPoints - augment;

			psion.setPowerPoints(powerPoints);
		}

		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			int targetReflex = target.getReflex(user);
			Utils.print("Your target has a reflex of " + targetReflex);

			int attackRoll = user.attackRoll(AbilityType.INTELLIGENCE, getAccessoryType(), targets);

			if (attackRoll >= targetReflex) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 1;

				if (augment == 2) {
					damageRolls = 2;
				}

				DiceType damageDiceType = DiceType.EIGHT_SIDED;

				if (augment > 0) {
					damageDiceType = DiceType.TEN_SIDED;
				}

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE), user), DamageType.FORCE, true, user);

				int targetPullDistance = 1;

				if (augment > 0) {
					targetPullDistance = user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM);
				}

				for (int i = 0; i < targetPullDistance; i++) {
					String pullDirection = Encounter.getEncounter().getPullDirection(user.getCurrentPosition(), target.getCurrentPosition());
					target.pull(pullDirection);
				}

			} else {
				Utils.print("You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user);
			}
		}
	}

	@Override
	public boolean isBasicAttack() {
		// TODO: Only when unaugmented, though.
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
		List<Class> classes = new ArrayList<Class>();
		classes.add(Psion.class);
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
