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
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class StormWalk extends AttackPower {
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
		return "Storm Walk";
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
		return PowerSource.ARCANE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.THUNDER);
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
		Utils.print("With this power you can shift before or after the attack.");
		Utils.print("1. Before");
		Utils.print("2. After");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			user.shift(1, true);
		}
		
		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10);
		
		DndCharacter c = null;
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			c = (DndCharacter) user;
		}
		
		Sorcerer sorcerer = null;
		if (Sorcerer.class.isAssignableFrom(c.getDndClass().getClass())) {
			sorcerer = (Sorcerer) c.getDndClass();
		}

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			int targetFortitude = target.getFortitude(user);
			Utils.print("Your target has a fortitude of " + targetFortitude);

			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), targets);

			// Check for unfettered power.
			if (sorcerer.getUnfetteredPower() == 1) {
				Utils.print("Because of your unfettered power, you push all creatures within 5 squares.");
				List<Creature> pushTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(user.getCurrentPosition(), 5);
				user.pushTargets(pushTargets, 1);
			}

			if (attackRoll >= targetFortitude) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				// Check for Arcane Spellfury feat.
				if (PlayerCharacter.class.isAssignableFrom(user.getClass())) {
					if (((PlayerCharacter) user).hasFeat(FeatType.ARCANE_SPELLFURY)) {
						Utils.print("Because " + user.getName() + " has the Arcane Spellfury feat, they will get a +1 attack bonus against " + target.getName() + " until the end of the next turn.");
						if (Creature.class.isAssignableFrom(target.getClass())) {
							Creature cTarget = (Creature) target;
							c.setTargetedTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.ARCANE_SPELLFURY, cTarget);
						}
					}
				}

				int damageRolls = 1;

				DiceType damageDiceType = DiceType.EIGHT_SIDED;

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.THUNDER, true, user);

				if (sorcerer.getUnfetteredPower() == 20) {
					Utils.print("Because of your unfettered power, you get to slide " + target.getName() + " 1 square and knock them prone.");
					user.slideTargets(targets, 1);
					if (Creature.class.isAssignableFrom(target.getClass())) {
						Creature cTarget = (Creature) target;
						// TODO: I don't think I've implemented standing up from prone yet.
						cTarget.setTemporaryCondition(user, DurationType.SPECIAL, CreatureConditionType.PRONE, TemporaryEffectReason.STORM_WALK, user.getCurrentTurn());
					}
				}
			} else {
				Utils.print("You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user);
			}
		}
		if (choice == 2) {
			user.shift(1, true);
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
		List<Class> classes = new ArrayList<Class>();
		classes.add(Sorcerer.class);
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
