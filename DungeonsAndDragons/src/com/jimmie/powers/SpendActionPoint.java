package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class SpendActionPoint extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Spend Action Point";
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
	public void process(Encounter encounter, Creature user) {
		/* Only let them do this if they have already used a standard action. */
		if (user.canTakeStandardAction()) {
			Utils.print("You really shouldn't spend an action point until you've already used your normal standard action.");
			return;
		}
		if (timesUsed == 0) {
			timesUsed++;
			if (user.getActionPoints() > 0) {
				user.setActionPoints(user.getActionPoints()-1);
			}
			user.setUsedStandardAction(false);
		} else {
			Utils.print("You have already spent an action point this encounter.  I know it would have been nice if I mentioned that already.  Sorry!");
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
		return null; // This power is automatically inserted, so it doesn't need the classes that can use power method to be implemented.
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
