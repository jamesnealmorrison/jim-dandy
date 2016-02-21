package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.Position;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;

public class OpenDoor extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Open Door";
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.AT_WILL;
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
	public DamageType getDamageType() {
		return DamageType.NONE;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 0;
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
		// If there is only one closed door next to the user, we know that is the one they want to open. TODO: Otherwise you have to ask them which door.
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX(),user.getCurrentPosition().getY()+1))) {
			Encounter.getEncounter().openDoor("N",user.getCurrentPosition());
		}
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX(),user.getCurrentPosition().getY()-1))) {
			Encounter.getEncounter().openDoor("S",user.getCurrentPosition());
		}
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX()+1,user.getCurrentPosition().getY()))) {
			Encounter.getEncounter().openDoor("E",user.getCurrentPosition());
		}
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX()-1,user.getCurrentPosition().getY()))) {
			Encounter.getEncounter().openDoor("W",user.getCurrentPosition());
		}
		return true;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// Is there a closed door near you?  Check all four directions.
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX(),user.getCurrentPosition().getY()+1))) {
			return true;
		}
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX(),user.getCurrentPosition().getY()-1))) {
			return true;
		}
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX()+1,user.getCurrentPosition().getY()))) {
			return true;
		}
		if (Encounter.getEncounter().isClosedDoorBetween(user.getCurrentPosition(), new Position(user.getCurrentPosition().getX()-1,user.getCurrentPosition().getY()))) {
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
		return null;
	}
}
