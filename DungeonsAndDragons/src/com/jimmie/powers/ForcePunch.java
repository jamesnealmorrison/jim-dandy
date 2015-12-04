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
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class ForcePunch extends AttackPower {
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
		return "Force Punch";
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
		return 1;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.AUGMENTABLE);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {
		/* See if they want to augment. */
		int augment = 0;
		int range = 0;
		
		Character c = null;
		if (Character.class.isAssignableFrom(user.getClass())) {
			c = (Character) user;
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
		
		AttackTarget target = encounter.chooseMeleeTargetInRange(user, 1);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(user, target, encounter, user.getCurrentPosition());
		int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + c.getImplementAttackBonus() + user.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetFortitude = target.getFortitude();
		Utils.print("Your target has an Fortitude of " + targetFortitude);
		
		if (roll >= targetFortitude) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				user.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.EIGHT_SIDED;

			if (augment == 2) {
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user.getRace()), DamageType.FORCE, encounter, true);
			} else {
			    target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE), user.getRace()), DamageType.FORCE, encounter, true);
			}
			
			int targetPushDistance = 1;
			
			if (augment == 1) {
				targetPushDistance = user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM);
			}
			
			if (augment == 2) {
				/* TODO: Nothing is really implemented yet for being prone. */
				target.knockProne();
				Utils.print("JIM!!!!!!! YOU HAVE NOT IMPLEMENTED BEING PRONE YET!!!!!");
			}
			
			String pushDirection = encounter.getPushDirection(user.getCurrentPosition(), target.getCurrentPosition());
			for (int i = 0; i < targetPushDistance; i++) {
			    target.push(pushDirection);
			}
			
			/* Push each adjacent enemy 1. */
			List<Creature> adjacentEnemies = encounter.getAdjacentEnemies(user);
			
			if (adjacentEnemies != null) {
				for (Creature adjacentEnemy : adjacentEnemies) {
					pushDirection = encounter.getPushDirection(user.getCurrentPosition(), adjacentEnemy.getCurrentPosition());
					adjacentEnemy.push(pushDirection);
				}
			}
		} else {
			Utils.print("You missed " + target.getName());
		}
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
