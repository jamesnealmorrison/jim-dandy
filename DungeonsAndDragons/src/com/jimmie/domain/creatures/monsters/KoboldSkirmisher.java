package com.jimmie.domain.creatures.monsters;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.Dice;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class KoboldSkirmisher extends Kobold {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldSkirmisher() {
		setInitiative(5);
		setMaxHitPoints(27);
		setCurrentHitPoints(27);
		setArmorClass(15);
		setFortitude(11);
		setReflex(14);
		setWill(13);
		setSpeed(6);
		setStrength(8);
		setConstitution(11);
		setDexterity(16);
		setIntelligence(6);
		setWisdom(10);
		setCharisma(15);
		addPower(PowerId.SPEAR);
		addPower(PowerId.SHIFTY);
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldSkirmisher.JPG");
	}
	
	public int getStrengthModifier() {
		return -1;
	}

	public int getConstitutionModifier() {
		return 0;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return -2;
	}
	
	public int getWisdomModifier() {
		return 0;
	}
	
	public int getCharismaModifier() {
		return 2;
	}

	@StandardAction(powerId = PowerId.SPEAR, isBasicAttack = true, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.MELEE)
	@AtWillPower
	public void spear(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(this, 1);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(this, target, encounter, getCurrentPosition());
		int roll = diceRoll + 6 + getOtherAttackModifier(targets, encounter);
		
		/* Kobold Skirmishers have "Mob Attack" which gives them a +1 bonus to attack rolls for every kobold ally
		 * adjacent to the target.
		 */
		List<Creature> adjacentCreatures = encounter.getAllAdjacentCreatures((Creature)target);
		
		/* Count how many kobolds are in the list (not myself though). */
		int count = 0;
		for (Creature adjacentCreature : adjacentCreatures) {
			if ((adjacentCreature != this) && (Kobold.class.isAssignableFrom(adjacentCreature.getClass()))) {
				count++;
			}
		}
		if (count > 0) {
		Utils.print("There are " + count + " kobolds adjacent to " + target.getName() +" so BONUS!");
		}
		roll = roll + count;
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(this);
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
			   if (Utils.hasCombatAdvantage(this, (Creature) target, encounter)) {
				   Dice dice = new Dice(DiceType.SIX_SIDED);
				   int combatAdvantageRoll = dice.basicRoll();
	      		   Utils.print("Adding " + combatAdvantageRoll + " to the damage because the Kobold Skirmisher had combat advantage against " + target.getName());
			       rollForDamage = rollForDamage + combatAdvantageRoll;
			   }
			}
			target.hurt(rollForDamage, DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}
}
