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
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class StirringShout extends AttackPower {
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
		return "Stirring Shout";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.DAILY;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.ARCANE;
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
		effectTypes.add(EffectType.HEALING);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;

			DndCharacter c = null;
			if (DndCharacter.class.isAssignableFrom(user.getClass())) {
				c = (DndCharacter) user;
			}


			List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10, getAttackType());

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				int targetWill = target.getWill(user);
				Utils.print("Your target has a Will of " + targetWill);

				int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), target, user.getCurrentPosition(), getAttackType());

				if (attackRoll >= targetWill) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = 2;
					DiceType damageDiceType = DiceType.SIX_SIDED;

					/* Book says at level 21 increase damage to 2d8. */
					if (user.getLevel() >= 21) {
						damageRolls = damageRolls * 2;
					}
					/* TODO: Supposed to be psychic damage.  Haven't implemented that yet. */
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.PSYCHIC, true, user, getAttackType());

					target.hitByStirringShout(user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA));
				} else {
					Utils.print("You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user, this);
				}
			}
			return true;
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Stirring Shout in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedStandardAction(false);			
			return false;
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
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}
		return true;
	}

}
