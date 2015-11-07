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

public class KoboldWyrmpriest extends Kobold {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldWyrmpriest() {
		setInitiative(4);
		setMaxHitPoints(36);
		setCurrentHitPoints(36);
		setArmorClass(17);
		setFortitude(13);
		setReflex(15);
		setWill(15);
		setSpeed(6);
		setStrength(9);
		setConstitution(12);
		setDexterity(16);
		setIntelligence(9);
		setWisdom(17);
		setCharisma(12);
		addPower(PowerId.SPEAR);
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldWyrmpriest.JPG");
	}

	public int getStrengthModifier() {
		return 0;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 4;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 4;
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
		int roll = diceRoll + 7 + getOtherAttackModifier(targets, encounter);
		
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

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, null), DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}
}
