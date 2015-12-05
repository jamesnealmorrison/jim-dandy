package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Dragonborn;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class DragonBreath extends AttackPower {
	private AbilityType abilityType;
	private List<DamageType> damageTypes;

	public DragonBreath() {
		super();
		
		Utils.print("Creating Dragon Breath power.  You have to make a few choices.");
		Utils.print("Choose Strength, Constitution, or Dexterity as the ability score you use with this power.");
		Utils.print("1. Strength");
		Utils.print("2. Constitution");
		Utils.print("3. Dexterity");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 3);
		switch (choice) {
		case 1 :
			abilityType = AbilityType.STRENGTH;
			break;
		case 2 :
			abilityType = AbilityType.CONSTITUTION;
			break;
		case 3 :
			abilityType = AbilityType.DEXTERITY;
			break;
		}
		
		DamageType damageType = null;
		Utils.print("Now choose which damage type you want your Dragon Breath to inflict.");
		Utils.print("1. Acid");
		Utils.print("2. Cold");
		Utils.print("3. Fire");
		Utils.print("4. Lightning");
		Utils.print("5. Poison");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 5);
		switch (choice) {
		case 1 :
			damageType = DamageType.ACID;
			break;
		case 2 :
			damageType = DamageType.COLD;
			break;
		case 3 :
			damageType = DamageType.FIRE;
			break;
		case 4 :
			damageType = DamageType.LIGHTNING;
			break;
		case 5 :
			damageType = DamageType.POISON;
			break;
		}
		damageTypes = new ArrayList<DamageType>();
		damageTypes.add(damageType);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BLAST;
	}

	@Override
	public String getName() {
		return "Dragon Breath";
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
		return PowerSource.NONE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.NONE;
	}

	@Override
	public List<DamageType> getDamageType() {
		return damageTypes;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.MINOR;
	}

	@Override
	public int getRangeNumber1() {
		return 3;
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
	public void process(Encounter encounter, Creature user) {
		Utils.print("Sorry, but I haven't implemented this power yet.");
	}

	@Override
	public boolean isBasicAttack() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getRacesThatCanUsePower() {
		List<Class> races = new ArrayList<Class>();
		races.add(Dragonborn.class);
		return races;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getClassesThatCanUsePower() {
		return null;
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