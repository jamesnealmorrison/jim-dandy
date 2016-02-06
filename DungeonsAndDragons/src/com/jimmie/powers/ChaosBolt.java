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
import com.jimmie.domain.Position;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.SorcererSpellSource;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class ChaosBolt extends AttackPower {

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
		return "Chaos Bolt";
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
	public DamageType getDamageType() {
		return DamageType.PSYCHIC;
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
		List<AttackTarget> attackedTargets = new ArrayList<AttackTarget>();
		Position lastTargetPosition = null;

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
			int targetWill = target.getWill(user);
			attackedTargets.add(target);
			Utils.print("Your target has a will of " + targetWill);

			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), targets);

			// Check for unfettered power.
			if (sorcerer.getUnfetteredPower() == 1) {
				Utils.print("Because of your unfettered power, you push all creatures within 5 squares.");
				List<Creature> pushTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(user.getCurrentPosition(), 5);
				user.pushTargets(pushTargets, 1);
			}

			if (attackRoll >= targetWill) {
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

				DiceType damageDiceType = DiceType.TEN_SIDED;

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.PSYCHIC, true, user);
				lastTargetPosition = target.getCurrentPosition();

				if (sorcerer.getUnfetteredPower() == 20) {
					Utils.print("Because of your unfettered power, you get to slide " + target.getName() + " 1 square and knock them prone.");
					user.slideTargets(targets, 1);
					if (Creature.class.isAssignableFrom(target.getClass())) {
						Creature cTarget = (Creature) target;
						// TODO: I don't think I've implemented standing up from prone yet.
						cTarget.setTemporaryCondition(user, DurationType.SPECIAL, CreatureConditionType.PRONE, TemporaryEffectReason.CHAOS_BOLT, user.getCurrentTurn());
					}
				}
				
				if (sorcerer != null) {
					if (sorcerer.getSpellSource() == SorcererSpellSource.WILD_MAGIC) {
						while (sorcerer.getLastAttackRollEven()) {
							Utils.print("You rolled an even number.  As a sorcerer with Wild Magic, you get to make a secondary attack.");
							Utils.print("Make sure you pick a target you haven't attacked already.");
							List<AttackTarget> secondaryTargets = Encounter.getEncounter().chooseRangedTargetFromPosition(user, lastTargetPosition, 5, 5);

							if ((secondaryTargets != null) && !(secondaryTargets.isEmpty())) {
								target = secondaryTargets.get(0);
								
								if (attackedTargets.contains(target)) {
									Utils.print("Sorry, you already attacked this target.  Stopping Chaos Bolt.");
									sorcerer.setLastAttackRollEven(false);
									break;
								}
								attackedTargets.add(target);
								lastTargetPosition = target.getCurrentPosition();
								targetWill = target.getWill(user);
								Utils.print("Your target has a reflex of " + targetWill);

								attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), targets);

								if (sorcerer.getUnfetteredPower() == 1) {
									Utils.print("Because of your unfettered power, you push all creatures within 5 squares.");
									List<Creature> pushTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(user.getCurrentPosition(), 5);
									user.pushTargets(pushTargets, 1);
								}
								
								if (attackRoll >= targetWill) {
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
									
									damageRolls = 1;

									damageDiceType = DiceType.SIX_SIDED;

									target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.PSYCHIC, true, user);
									
									// Check for unfettered power.
									if (sorcerer.getUnfetteredPower() == 20) {
										Utils.print("Because of your unfettered power, you get to slide " + target.getName() + " 1 square and knock them prone.");
										user.slideTargets(secondaryTargets, 1);
										if (Creature.class.isAssignableFrom(target.getClass())) {
											Creature cTarget = (Creature) target;
											// TODO: I don't think I've implemented standing up from prone yet.
											cTarget.setTemporaryCondition(user, DurationType.SPECIAL, CreatureConditionType.PRONE, TemporaryEffectReason.CHAOS_BOLT, user.getCurrentTurn());
										}
									}
									
								} else {
									Utils.print("You missed " + target.getName());
									// Some targets have powers/effects that happen when they are missed.
									target.miss(user, this);
								}
							}
						}
					}
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
