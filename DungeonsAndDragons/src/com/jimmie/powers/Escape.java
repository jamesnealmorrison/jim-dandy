package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.TemporaryCondition;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.monsters.Zombie;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.Utils;

public class Escape extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Escape";
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
		return ActionType.MOVE;
	}

	@Override
	public int getRangeNumber1() {
		return 1;
	}

	@Override
	public int getRangeNumber2() {
		return 1;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		Utils.print("Would you like to do an Acrobatics check vs. Reflex or an Athletics check vs Fortitude?");
		Utils.print("1. Acrobatics");
		Utils.print("2. Athletics");
		
		int targetNumber = 0;
		SkillCheck skillCheck = new SkillCheck();
		// Who is the grabber?
		Creature grabber = null;
		TemporaryCondition condition = null;
		if (user.getTemporaryEffects() != null) {
			for (TemporaryEffect effect : user.getTemporaryEffects()) {
				if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
					condition = (TemporaryCondition) effect;
					if ((condition.getConditionType() == CreatureConditionType.IMMOBILIZED) && (condition.getReason() == TemporaryEffectReason.GRABBED)) {
						grabber = condition.getSource();
					}
				}
			}
		}
		
		if (grabber == null) {
			Utils.print("Apparently you aren't really grabbed.");
			return false;
		}
		
		if (Utils.getValidIntInputInRange(1, 2) == 1) {
			targetNumber = grabber.getReflex(user);
			skillCheck.setDifficultyClass(targetNumber);
			skillCheck.setSkillType(SkillType.ACROBATICS);
		} else {
			targetNumber = grabber.getFortitude(user);
			skillCheck.setDifficultyClass(targetNumber);
			skillCheck.setSkillType(SkillType.ATHLETICS);
		}
		
		if (Zombie.class.isInstance(grabber)) {
			Utils.print(grabber.getName() + " used Zombie Grab to grab you.  You take a -5 penalty to the skill check.");
			Utils.print("Instead of reducing the roll by 5, I'm adding 5 to the difficulty of the test.  It should be the same thing, though.");
			skillCheck.setDifficultyClass(skillCheck.getDifficultyClass()+5);
		}
		
		if (user.performSkillCheck(skillCheck) == true) {
			Utils.print("You successfully escaped.");
			user.getTemporaryEffects().remove(condition);
			grabber.setGrabbedCreature(null);
			// You can also shift after an escape.
			user.shift(1, true);
		} else {
			Utils.print("You failed.  Still grabbed by " + grabber.getName());
		}
		
		return true;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// See if they are grabbed.
		if (user.getTemporaryEffects() != null) {
			for (TemporaryEffect effect : user.getTemporaryEffects()) {
				if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
					TemporaryCondition condition = (TemporaryCondition) effect;
					if ((condition.getConditionType() == CreatureConditionType.IMMOBILIZED) && (condition.getReason() == TemporaryEffectReason.GRABBED)) {
						return true;
					}
				}
			}
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
