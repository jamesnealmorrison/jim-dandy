package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.MarkType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class DivineChallenge extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean takenOneTimePenalty = false;
	private Creature markedCreature;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BURST;
	}

	@Override
	public String getName() {
		return "Divine Challenge";
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
		return PowerSource.DIVINE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.NONE;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.RADIANT;
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 5, 5, getAttackType());
		
		for (AttackTarget target : targets) {
			if (Creature.class.isAssignableFrom(target.getClass())) {
				Creature c = (Creature) target;
				c.mark(user, DurationType.SPECIAL, MarkType.DIVINE_CHALLENGE, user.getCurrentTurn());
				Utils.print(target.getName() + " is now marked by " + getName() + " until " + getName() + " marks another target or fails to engage " + target.getName());
				markedCreature = c;
			}
		}
		takenOneTimePenalty = false;
		timesUsed++;
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
		classes.add(Paladin.class);
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

	public boolean hasTakenOneTimePenalty() {
		return takenOneTimePenalty;
	}

	public void setTakenOneTimePenalty(boolean takenOneTimePenalty) {
		this.takenOneTimePenalty = takenOneTimePenalty;
	}

	public Creature getMarkedCreature() {
		return markedCreature;
	}

	public void setMarkedCreature(Creature markedCreature) {
		this.markedCreature = markedCreature;
	}

}
