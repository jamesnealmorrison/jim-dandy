package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.monsters.Kobold;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class KoboldDragonshieldShortSword extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Short Sword";
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
		return 1;
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
	public void process(Creature user) {
		List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTargetInRange(user, 1);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int roll = d.roll(DiceRollType.ATTACK_ROLL) + 7 + user.getOtherAttackModifier(targets);

			/* Kobold Skirmishers have "Mob Attack" which gives them a +1 bonus to attack rolls for every kobold ally
			 * adjacent to the target.
			 */
			List<Creature> adjacentCreatures = Encounter.getEncounter().getAllAdjacentCreatures((Creature)target);

			/* Count how many kobolds are in the list (not myself though). */
			int count = 0;
			for (Creature adjacentCreature : adjacentCreatures) {
				if ((adjacentCreature != user) && (Kobold.class.isAssignableFrom(adjacentCreature.getClass()))) {
					count++;
				}
			}
			if (count > 0) {
				Utils.print("There are " + count + " kobolds adjacent to " + target.getName() +" so BONUS!");
			}
			roll = roll + count;

			Utils.print("Your total with modifiers is " + roll + ".");

			int targetArmorClass = target.getArmorClass(user);
			Utils.print("Your target has an AC of " + targetArmorClass);

			if (roll >= targetArmorClass) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 1;
				DiceType damageDiceType = DiceType.SIX_SIDED;

				int weaponBonus = 3;

				int attributeBonus = 0;

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, null), DamageType.NORMAL, true, user);
			} else {
				Utils.print("You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user);
			}
		}
	}

	@Override
	public boolean isBasicAttack() {
		return true;
	}

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_NUMBER;
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
		return true;
	}
}
