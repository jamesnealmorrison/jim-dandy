package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.monsters.Kobold;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class KoboldSkirmisherSpear extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Spear";
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
		AttackTarget target = encounter.chooseMeleeTargetInRange(user, 1);
		
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(user, target, encounter, user.getCurrentPosition());
		int roll = diceRoll + 6 + user.getOtherAttackModifier(targets, encounter);
		
		/* Kobold Skirmishers have "Mob Attack" which gives them a +1 bonus to attack rolls for every kobold ally
		 * adjacent to the target.
		 */
		List<Creature> adjacentCreatures = encounter.getAllAdjacentCreatures((Creature)target);
		
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
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(user);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.EIGHT_SIDED;

			int weaponBonus = 0;
			
			int attributeBonus = 0;

			int rollForDamage = Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, null);
			
			/* Combat Advantage power adds 1d6 damage when the kobold skirmisher has combat advantage against the target. */
			if (Creature.class.isInstance(target)) {
			   if (Utils.hasCombatAdvantage(user, (Creature) target, encounter)) {
				   Dice dice = new Dice(DiceType.SIX_SIDED);
				   int combatAdvantageRoll = dice.basicRoll();
	      		   Utils.print("Adding " + combatAdvantageRoll + " to the damage because the Kobold Skirmisher had combat advantage against " + target.getName());
			       rollForDamage = rollForDamage + combatAdvantageRoll;
			   }
			}
			target.hurt(rollForDamage, DamageType.NORMAL, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@Override
	public boolean isBasicAttack() {
		return true;
	}

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_1;
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
