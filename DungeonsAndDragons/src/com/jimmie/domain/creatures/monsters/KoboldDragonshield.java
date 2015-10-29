package com.jimmie.domain.creatures.monsters;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.Dice;
import com.jimmie.util.MinorAction;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class KoboldDragonshield extends Kobold {
	private boolean usedDragonshieldTactics;

	@Override
	public void startOfTurn() {
		super.startOfTurn();
		Utils.print("In KoboldDragonshield.startOfTurn");
		usedDragonshieldTactics = false;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SHORT_SWORD = "Short Sword Attack";
	private static final String SHIFTY = "Shifty";
	
	public KoboldDragonshield() {
		setInitiative(4);
		setMaxHitPoints(36);
		setCurrentHitPoints(36);
		setArmorClass(18);
		setFortitude(14);
		setReflex(13);
		setWill(13);
		setSpeed(6);
		setStrength(14);
		setConstitution(12);
		setDexterity(13);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(SHORT_SWORD);
		addPower(SHIFTY);
		usedDragonshieldTactics = false;
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldDragonshield.JPG");
	}

	public boolean isUsedDragonshieldTactics() {
		return usedDragonshieldTactics;
	}

	public void setUsedDragonshieldTactics(boolean usedDragonshieldTactics) {
		this.usedDragonshieldTactics = usedDragonshieldTactics;
	}

	public int getStrengthModifier() {
		return 3;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 2;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 2;
	}
	
	public int getCharismaModifier() {
		return 1;
	}

	@StandardAction(menuName = SHORT_SWORD, isBasicAttack = true, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = false, weaponTag = false, arcaneTag = false, primalTag = false, psionicTag = false)
	@AtWillPower
	public void shortSword(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(this, 1);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(this, target, encounter, getCurrentPosition());
		int roll = diceRoll + 7 + getOtherAttackModifier(targets, encounter);
		
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
			DiceType damageDiceType = DiceType.SIX_SIDED;
			
			int weaponBonus = 3;
			
			int attributeBonus = 0;

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, null), DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@MinorAction(menuName = SHIFTY)
	@AtWillPower
	public void shifty(Encounter encounter) {
		shift(1, true, encounter);
	}

	public void useDragonshieldTactics(Encounter encounter) {
		Utils.print(getName() + " can use dragonshield tactics to shift 1 square.  Do you want to?");
		String choice = Utils.getYesOrNoInput();
		if ("Y".equalsIgnoreCase(choice)) {
			shift(1, true, encounter);
			usedDragonshieldTactics = true;
		}
		
	}
}
