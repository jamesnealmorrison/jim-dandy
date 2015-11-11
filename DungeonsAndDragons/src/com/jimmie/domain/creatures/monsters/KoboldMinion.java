package com.jimmie.domain.creatures.monsters;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.Dice;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;
import com.jimmie.domain.AttackType;

public class KoboldMinion extends Kobold {
	@Override
	public void startOfTurn() {
		super.startOfTurn();
		Utils.print("In KoboldMinion.startOfTurn");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldMinion() {
		setInitiative(3);
		setMaxHitPoints(1);
		setCurrentHitPoints(1);
		setArmorClass(15);
		setFortitude(11);
		setReflex(13);
		setWill(11);
		setSpeed(6);
		setStrength(8);
		setConstitution(12);
		setDexterity(16);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(PowerId.SPEAR);
		addPower(PowerId.JAVELIN);
		addPower(PowerId.SHIFTY);
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldMinion.JPG");
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

	@StandardAction(powerId = PowerId.SHORT_SWORD, isBasicAttack = true, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.MELEE)
	@AtWillPower
	public void shortSword(Encounter encounter) {
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

			target.hurt(4, DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(powerId = PowerId.JAVELIN, isBasicAttack = true, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.RANGED)
	@AtWillPower
	public void javelin(Encounter encounter) {
		AttackTarget target = encounter.chooseRangedTarget(this, 10, 20);
			
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

			target.hurt(4, DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}
}
