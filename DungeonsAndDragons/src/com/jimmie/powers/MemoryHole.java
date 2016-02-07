package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.Position;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class MemoryHole extends AttackPower {
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
		return "Memory Hole";
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
		effectTypes.add(EffectType.AUGMENTABLE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		/* See if they want to augment. */
		int augment = 0;
		int range = 0;
		
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
		
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		if (augment == 2) {
			Encounter.showCoordinateSystem(true);
			Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int x = Utils.getValidIntInputInRange(1, 50);
			
			Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int y = Utils.getValidIntInputInRange(1, 50);
			Encounter.showCoordinateSystem(false);
			
			/* Got to do this wierd conversion between creatures and attack targets. */
			List<Creature> creatureTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(new Position(x, y), 1);
			for (Creature creature : creatureTargets) {
				targets.add(creature);
			}
		} else {
			Creature target = (Creature) Encounter.getEncounter().chooseRangedTarget(user, 10, 10);
			Utils.print("UMMMMM....." + target.getName() + " laughs at you because I forgot to finish programming this attack.  DORK!");
		}
		
		int damageRolls = 1;
		
		if (augment == 2) {
			damageRolls = 2;
		}
				
		Utils.print("Since this might affect multiple targets, rolling for damage first.");
		Dice damageDice = new Dice(DiceType.SIX_SIDED);
 		int damage = 0;
 		
 		for (int rolls = 0; rolls < damageRolls; rolls++) {
 		    damage = damage + damageDice.roll(DiceRollType.DAMAGE_ROLL_MODIFICATION);
 		}
 		damage = damage + user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE);
		
 		List<Creature> hitTargets = new ArrayList<Creature>();
		DurationType durationType = DurationType.START_OF_NEXT_TURN;
		if (augment == 1) {
			durationType = DurationType.END_OF_NEXT_TURN;
		}
		for (AttackTarget target : targets) {
			int targetWill = target.getWill(user);
			Utils.print("Your target has a Will of " + targetWill);
			
			int attackRoll = user.attackRoll(AbilityType.INTELLIGENCE, getAccessoryType(), target);
			
			if (attackRoll >= targetWill) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				target.hurt(damage, DamageType.PSYCHIC, true, user);
				
				hitTargets.add((Creature) target);
				Utils.print("You just became invisible to " + target.getName());
			} else {
			    Utils.print("Sorry.  You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user, this);
			}
		}
		user.setTemporaryInvisibility(user, durationType, hitTargets, TemporaryEffectReason.MEMORY_HOLE);
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
