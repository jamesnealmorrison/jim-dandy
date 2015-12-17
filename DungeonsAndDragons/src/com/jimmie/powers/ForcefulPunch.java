package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class ForcefulPunch extends AttackPower {
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
		return "Forceful Punch";
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
		return PowerSource.PSIONIC;
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
		return ActionType.FREE;
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
		if (timesUsed == 0) {
			timesUsed++;
			List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10);

			int targetSlideDistance = 0;
			if (user.getLevel() < 11) {
				targetSlideDistance = 1;
			} else if (user.getLevel() < 21) {
				targetSlideDistance = 2;
			} else {
				targetSlideDistance = 3;
			}

			for (int i = 0; i < targetSlideDistance; i++) {
				Utils.print("What direction do you want to slide them (N, E, S, W, NE, NW, SE, SW, STOP)?");
				List<String> validDirections = new ArrayList<String>();

				validDirections.add("N");
				validDirections.add("E");
				validDirections.add("S");
				validDirections.add("W");
				validDirections.add("NE");
				validDirections.add("NW");
				validDirections.add("SE");
				validDirections.add("SW");
				validDirections.add("STOP");

				Utils.print("Your choice?");
				String direction = Utils.getValidInput(validDirections);
				if ("STOP".equalsIgnoreCase(direction)) {
					break;
				}

				targets.get(0).slide(direction);
			}
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Forceful Push in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
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
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}
		return true;
	}

}
