package com.jimmie.powers;

import java.io.Serializable;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;

public abstract class Power implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int timesUsed = 0;
	public abstract String getName();
	public abstract int getLevel();
	public abstract PowerUsage getPowerUsage();
	public abstract PowerSource getPowerSource();
	public abstract AccessoryType getAccessoryType();
	public abstract List<DamageType> getDamageType();
	public abstract ActionType getActionType();
	public abstract int getRangeNumber1(); // This is for ranged/close/area.  The first number.  e.g. close burst 3
	public abstract int getRangeNumber2(); // This is for area.  The second number.  e.g. area burst 2 within 5 squares
	public abstract List<EffectType> getEffectTypes();
	public abstract void process(Encounter encounter, Creature user);
	public abstract boolean meetsPrerequisitesToChoosePower(Creature user);
	public abstract boolean meetsRequirementsToUsePower(Creature user);
	public abstract boolean isBasicAttack();
	public void initializeForEncounter() {
		timesUsed  = 0;
	}
	public void initializeForStartOfTurn() {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean canBeSelected(PlayerCharacter pc) {
		// First check if they are a high enough level.
		if (pc.getLevel() < getLevel()) {
			return false;
		}
		
		if (!meetsPrerequisitesToChoosePower(pc)) {
			return false;
		}
		if (getClassesThatCanUsePower() != null) {
			for (Class dndClass : getClassesThatCanUsePower()) {
				if (dndClass.isAssignableFrom(pc.getDndClass().getClass())) {
					return true;
				}
			}
		}
		if (getRacesThatCanUsePower() != null) {
			for (Class race : getRacesThatCanUsePower()) {
				if (race.isAssignableFrom(pc.getRace().getClass())) {
					return true;
				}
			}
		}
		// TODO: Half-Elf Dilettante.  At first level ONLY, you can pick any class at will power.  BUT you can only use it as an encounter power.
		return false;
	}
	@SuppressWarnings("rawtypes")
	public abstract List<Class> getRacesThatCanUsePower();
	@SuppressWarnings("rawtypes")
	public abstract List<Class> getClassesThatCanUsePower();
	public void setActionType(ActionType actionType) {
		// Doesn't do anything.  Just meant to be overridden.		
	}
}
