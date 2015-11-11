package com.jimmie.domain.creatures.monsters;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.Dice;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class KoboldSlinger extends Kobold {
	@Override
	public void startOfTurn() {
		super.startOfTurn();
		Utils.print("In KoboldSlinger.startOfTurn");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldSlinger() {
		setInitiative(3);
		setMaxHitPoints(24);
		setCurrentHitPoints(24);
		setArmorClass(13);
		setFortitude(12);
		setReflex(14);
		setWill(12);
		setSpeed(6);
		setStrength(9);
		setConstitution(12);
		setDexterity(17);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(PowerId.DAGGER);
		addPower(PowerId.SLING);
		addPower(PowerId.SHIFTY);
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldSlinger.JPG");
	}

	public int getStrengthModifier() {
		return -1;
	}

	public int getConstitutionModifier() {
		return 1;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return -1;
	}
	
	public int getWisdomModifier() {
		return 1;
	}
	
	public int getCharismaModifier() {
		return 0;
	}

	@StandardAction(powerId = PowerId.DAGGER, isBasicAttack = true, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.MELEE)
	@AtWillPower
	public void dagger(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(this, 1);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(this, target, encounter, getCurrentPosition());
		int roll = diceRoll + 5 + getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(this);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.FOUR_SIDED;
			
			int weaponBonus = 3;
			
			int attributeBonus = 0;

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, null), DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(powerId = PowerId.SLING, isBasicAttack = true, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.RANGED)
	@AtWillPower
	public void sling(Encounter encounter) {
		AttackTarget target = encounter.chooseRangedTarget(this, 10, 20);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(this, target, encounter, getCurrentPosition());
		int roll = diceRoll + 6 + getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(this);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.SIX_SIDED;
			
			int weaponBonus = 3;
			
			int attributeBonus = 0;

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, null), DamageType.NORMAL_DAMAGE, encounter, true);
			
System.out.println("STILL NEED TO IMPLEMENT SPECIAL SHOT and general rounds!!!!!!!");			
		} else {
			Utils.print("You missed " + target.getName());
		}
	}
}
