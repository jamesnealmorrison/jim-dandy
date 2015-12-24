package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.MovementType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class MajesticWord extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BURST;
	}

	@Override
	public String getName() {
		return "Majestic Word";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER_SPECIAL;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.ARCANE;
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
		return 5;
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
	public void process(Creature user) {
		if (timesUsed < 2) {
			timesUsed++;
			Utils.print("Remember you can only uses this once per round, even if you can use it twice in an encounter.  But I didn't code that so you are on the honor system.");
			int range = 0;
			
			if (user.getLevel() < 11) {
				range = 5;
			} else if (user.getLevel() < 21) {
				range = 10;
			} else {
				range = 15;
			}

			DndCharacter target = (DndCharacter) Encounter.getEncounter().chooseAllyWithinRangeOf(user, user.getCurrentPosition(), range);
			
			target.useHealingSurge();
			int extraRolls = 0;
			
			int extraHitPoints = user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA);
			if (user.getLevel() < 6) {
				/* Don't add anything else. */
				extraRolls = 0;
			} else if (user.getLevel() < 11) {
				extraRolls = 1;
			} else if (user.getLevel() < 16) {
				extraRolls = 2;
			} else if (user.getLevel() < 21) {
				extraRolls = 3;
			} else if (user.getLevel() < 26) {
				extraRolls = 4;
			} else {
				extraRolls = 5;
			}

			for (int i = 0; i < extraRolls; i++) {
			    Dice d = new Dice(DiceType.SIX_SIDED);
     			extraHitPoints = extraHitPoints + d.roll(DiceRollType.HIT_POINT_MODIFICATION);
			}		
			
			target.heal(extraHitPoints);
			Utils.print("Gave them an extra " + extraHitPoints + " extra hit points.");
			
			Utils.print("You can also slide " + target.getName() + ".  Do you want to?");
			
			String choice = Utils.getYesOrNoInput();
			
			if ("Y".equalsIgnoreCase(choice)) {
				Utils.print("What direction do you want to slide them (N, E, S, W, NE, NW, SE, SW)?");
				List<String> validDirections = new ArrayList<String>();

				validDirections.add("N");
				validDirections.add("E");
				validDirections.add("S");
				validDirections.add("W");
				validDirections.add("NE");
				validDirections.add("NW");
				validDirections.add("SE");
				validDirections.add("SW");

				Utils.print("Your choice?");
				String direction = Utils.getValidInput(validDirections);
				target.moveCreature(direction, MovementType.SLIDE);
			}
			
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Majestic Word twice in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedMinorAction(false);
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
		classes.add(Bard.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// This can be used twice per encounter.
		// TODO - but I didn't implement "once per round" yet.
		if (timesUsed > 1) {
			return false;
		}
		return true;
	}

}
