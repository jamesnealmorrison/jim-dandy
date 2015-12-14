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
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.MarkType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class MisdirectedMark extends AttackPower {

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
		return "Misdirected Mark";
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
		return PowerSource.ARCANE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NORMAL);
		return damageTypes;
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {
		DndCharacter c = null;
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			c = (DndCharacter) user;
		}
		List<AttackTarget> targets = encounter.chooseRangedTarget(user, 10, 10);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.roll();
			int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA) + user.getOtherAttackModifier(targets, encounter);
			if (c != null) {
				roll += c.getImplementAttackBonus();
			}

			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

			int targetReflex = target.getReflex(user);
			Utils.print("Your target has a reflex of " + targetReflex);

			if (roll >= targetReflex) {
				// A HIT! 
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 1;
				DiceType damageDiceType = DiceType.EIGHT_SIDED;

				//* Book says at level 21 increase damage to 2d8.
				if (user.getLevel() >= 21) {
					damageRolls = damageRolls * 2;
				}
				if (c != null) {
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user.getRace()), DamageType.NORMAL, encounter, true, user);
				} else {
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, 0, user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user.getRace()), DamageType.NORMAL, encounter, true, user);
				}

				// The target is marked by an ally within 5 squares.
				Creature misdirectedMarker = encounter.chooseAllyWithinRangeOf(user, user.getCurrentPosition(), 5);
				if (Creature.class.isAssignableFrom(target.getClass())) {
					((Creature)target).mark(user, DurationType.END_OF_NEXT_TURN, MarkType.MISDIRECTED_MARK, user.getCurrentTurn(), misdirectedMarker);
					Utils.print(target.getName() + " is now marked by " + misdirectedMarker.getName() + " until the end of my next turn..");
				}
				Utils.print(target.getName() + " is now marked by " + misdirectedMarker.getName() + " until the end of my next turn because I have Combat Challenge.");
			} else {
				Utils.print("You missed " + target.getName());
			}
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
		return true;
	}
}
