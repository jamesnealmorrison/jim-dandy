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
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class KoboldMinionJavelin extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int attackModifier = 4; // Some kobold Minions have 4 and others have 5.

	@Override
	public String getName() {
		return "Javelin";
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
		return DamageType.NORMAL;
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
		return 20;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		// Are there any rounds left?
		if (KoboldMinion.class.isAssignableFrom(user.getClass())) {
			KoboldMinion minion = (KoboldMinion) user;
			if (minion.getJavelins() == 0) {
				Utils.print("Sorry, but " + minion.getName() + " has no more javelins.");
				return false;
			}

			List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 20, getAttackType());

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				int diceRoll = user.attackRoll(KoboldMinionJavelin.getAttackModifier() + user.getOtherAttackModifier(target));

				Utils.print("You rolled a total of " + diceRoll);

				int targetArmorClass = target.getArmorClass(user);
				Utils.print("Your target has an AC of " + targetArmorClass);

				if (diceRoll >= targetArmorClass) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					target.hurt(4, DamageType.NORMAL, true, user, getAttackType());
				} else {
					Utils.print("You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user, this);
				}
				minion.setJavelins(minion.getJavelins()-1);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isBasicAttack() {
		return true;
	}

	@Override
	public AttackType getAttackType() {
		return AttackType.RANGED_NUMBER;
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
		if (KoboldMinion.class.isAssignableFrom(user.getClass())) {
			KoboldMinion minion = (KoboldMinion) user;
			// Are there any rounds left?
			if (minion.getJavelins() == 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static void setAttackModifier(int attackModifier) {
		KoboldMinionJavelin.attackModifier = attackModifier;
	}

	private static int getAttackModifier() {
		return attackModifier ;
	}

}
