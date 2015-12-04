package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class AidAnother extends Power {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Aid Another";
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
	public void process(Encounter encounter, Creature user) {
		Utils.print("Pick the target that you want to help your ally against.");
		AttackTarget target = encounter.chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

		if (target != null) {
			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			targets.add(target);
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.attackRoll(user, target, encounter, user.getCurrentPosition());
			int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + user.getWeaponProficiencyBonus() + user.getOtherAttackModifier(targets, encounter);

			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

			Utils.print("The 'Aid Another' action is against a DC of 10.");

			if (roll >= 10) {
				/* A HIT! */
				Utils.print("You are successfully aiding against " + target.getName());

				Utils.print("Now choose the ally you are going to aid.");
				Creature ally = encounter.chooseAnyAlly(user);

				Utils.print("Now choose whether to add 2 to " + ally.getName() + "'s next attack (attack), or to their defenses (defense).");
				List<String> validChoices = new ArrayList<String>();
				validChoices.add("attack");
				validChoices.add("defense");
				String choice = Utils.getValidInput(validChoices);
				int bonusType = 0;
				if ("attack".equalsIgnoreCase(choice)) {
					bonusType = TemporaryAidAnotherBonus.ATTACK;				
				} else {
					bonusType = TemporaryAidAnotherBonus.DEFENSE;				
				}
				ally.setTemporaryAidAnotherBonus(2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, target, bonusType);
			} else {
				Utils.print("You missed " + target.getName());
			}

			/* If this is a fighter, then they have "Combat Challenge", and can mark the target. */
			if (Fighter.class.isInstance(user.getDndClass())) {
				/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
				 * I can't think of a reason I wouldn't WANT to mark the target.
				 */
				target.markByCombatChallenge(user, DurationType.END_OF_NEXT_TURN);
				Utils.print(target.getName() + " is now marked by " + getName() + " until the end of my next turn because I have Combat Challenge.");
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
