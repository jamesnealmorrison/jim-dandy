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
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.classes.PrimalAspect;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class FrostFlash extends AttackPower {
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
		return "Frost Flash";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.PRIMAL;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.COLD);
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
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;

			DndCharacter c = null;
			if (DndCharacter.class.isAssignableFrom(user.getClass())) {
				c = (DndCharacter) user;
			}

			List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10);

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				int targetFortitude = target.getFortitude(user);
				Utils.print("Your target has a Fortitude of " + targetFortitude);

				int attackRoll = user.attackRoll(AbilityType.WISDOM, AccessoryType.IMPLEMENT, targets);

				if (attackRoll >= targetFortitude) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = 1;
					DiceType damageDiceType = DiceType.SIX_SIDED;
					
					int wisdomModifier = user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM);
					int constitutionModifier = 0;
					if (Druid.class.isAssignableFrom(c.getDndClass().getClass())) {
						Druid druid = (Druid) c.getDndClass();
						if (druid.getPrimalAspect() == PrimalAspect.PRIMAL_GUARDIAN) {
							constitutionModifier = user.getAbilityModifier(AbilityType.CONSTITUTION);
							Utils.print("Because " + user.getName() + " is a Primal Guardian, adding " + constitutionModifier + " to the damage.");
						}
					}

					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), wisdomModifier+constitutionModifier, user), DamageType.NORMAL, true, user);
					
					if (Creature.class.isAssignableFrom(target.getClass())) {
						Creature cTarget = (Creature) target;
						Utils.print(target.getName() + " is also immobilized until the end of the next turn.");
						cTarget.setTemporaryCondition(user, DurationType.END_OF_NEXT_TURN, CreatureConditionType.IMMOBILIZED, TemporaryEffectReason.FROST_FLASH, user.getCurrentTurn());						
					}
					

				} else {
					Utils.print("You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user);
				}
			}
			return true;
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Frost Flash in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedStandardAction(false);
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
		classes.add(Druid.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}

		if (user.getDndClass() != null) {
			if (Druid.class.isAssignableFrom(user.getDndClass().getClass())) {
				// A druid must be in humanoid form to use this.
				Druid druid = (Druid) user.getDndClass();
				if (druid.isInBeastForm()) {
					return false;
				}
			}
		}
		
		return true;
	}

}
