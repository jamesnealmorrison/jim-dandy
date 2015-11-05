package com.jimmie.domain.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.DailyPower;
import com.jimmie.util.Dice;
import com.jimmie.util.EncounterPower;
import com.jimmie.util.RequiresShield;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class Fighter extends DndClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String SURE_STRIKE = "Sure Strike";
	public static final String TIDE_OF_IRON = "Tide Of Iron";
	public static final String COVERING_ATTACK = "Covering Attack";
	public static final String COMEBACK_STRIKE = "Comeback Strike";
	public static final String ONE_HANDED_WEAPON = "OneHandedWeapon";
	public static final String TWO_HANDED_WEAPON = "TwoHandedWeapon";
	private String fighterWeaponTalent;
	private boolean usedCoveringAttack;
	private boolean usedComebackStrike;

	@StandardAction(menuName = SURE_STRIKE, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = true, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@AtWillPower
	public void sureStrike(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter) + 2;
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			/* Book says at level 21 increase damage to 2[W]. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), 0, owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
		} else {
			Utils.print("You missed " + target.getName());
		}
		
		/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
		 * I can't think of a reason I wouldn't WANT to mark the target.
		 */
		target.markByCombatChallenge(this.owner, DurationType.END_OF_NEXT_TURN);
		Utils.print(target.getName() + " is now marked by " + owner.getName() + " until the end of my next turn because I have Combat Challenge.");
		
	}

	@StandardAction(menuName = TIDE_OF_IRON, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = true, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@RequiresShield
	@AtWillPower
	public void tideOfIron(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			/* Book says at level 21 increase damage to 2[W]. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			/* Push the target. */
			String pushDirection = encounter.getPushDirection(owner.getCurrentPosition(), target.getCurrentPosition());
			target.push(pushDirection);
			Utils.print("I think I am also supposed to be able to occupy the space the creature was in, but I haven't implemented that yet.  SORRY!");
			
			/* I get an AC/REF bonus of +1 until the end of my next turn. */
			owner.setTemporaryArmorClassBonus(1, owner.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, owner);
			owner.setTemporaryReflexBonus(1, owner.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, owner);
		} else {
			Utils.print("You missed " + target.getName());
		}
		/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
		 * I can't think of a reason I wouldn't WANT to mark the target.
		 */
		target.markByCombatChallenge(this.owner, DurationType.END_OF_NEXT_TURN);
		Utils.print(target.getName() + " is now marked by " + owner.getName() + " until the end of my next turn because I have Combat Challenge.");
	}

	@StandardAction(menuName = COVERING_ATTACK, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = true, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@EncounterPower
	public void coveringAttack(Encounter encounter) {
		if (!usedCoveringAttack) {
			usedCoveringAttack = true;
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls() * 2;
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			/* An ally adjacent to the target can shift two squares. */
			Creature ally = encounter.chooseAllyAdjacentTo(owner, target.getCurrentPosition());
			
			if (ally != null) {
				ally.shift(2, true, encounter);
			}
			
		} else {
			Utils.print("You missed " + target.getName());
		}
		/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
		 * I can't think of a reason I wouldn't WANT to mark the target.
		 */
		target.markByCombatChallenge(this.owner, DurationType.END_OF_NEXT_TURN);
		Utils.print(target.getName() + " is now marked by " + owner.getName() + " until the end of my next turn because I have Combat Challenge.");
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Covering Attack in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);
		}
	}

	@StandardAction(menuName = COMEBACK_STRIKE, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = true, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@DailyPower
	public void comebackStrike(Encounter encounter) {
		if (!usedComebackStrike) {
			usedComebackStrike = true;
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls() * 2;
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			Utils.print("I get to spend a healing surge.");
			owner.useHealingSurge();
			
		} else {
			Utils.print("You missed " + target.getName());
		}
		/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
		 * I can't think of a reason I wouldn't WANT to mark the target.
		 */
		target.markByCombatChallenge(this.owner, DurationType.END_OF_NEXT_TURN);
		Utils.print(target.getName() + " is now marked by " + owner.getName() + " until the end of my next turn because I have Combat Challenge.");
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Covering Attack in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);
		}
	}

	public String getFighterWeaponTalent() {
		return fighterWeaponTalent;
	}

	public String setFighterWeaponTalent(String fighterWeaponTalent) {
		this.fighterWeaponTalent = fighterWeaponTalent;
		return fighterWeaponTalent;
	}

	@Override
	public void initializeForEncounter() {
		usedCoveringAttack = false;
	}

	@Override
	public void initializeForNewDay() {
		usedComebackStrike = false;
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
		choices.add("Streetwise");
		
		Utils.print("Choose 3 of the following");
		for (int i = 0; i < 3; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			String choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Defender.");
		pc.setRole(Role.DEFENDER);
		
		Utils.print("Setting power Source to Martial.");
		pc.setPowerSource(PowerSource.MARTIAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail, Scale, Light shield, Heavy shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		pc.addArmorGroupProficiency(ArmorGroup.SCALE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		pc.addArmorGroupProficiency(ArmorGroup.HEAVY_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple melee, simple ranged, military melee, military ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_RANGED);
		
		Utils.print("Adding bonus of +2 Fortitude");
		if (pc.getFortitudeMisc1() == 0) {
			pc.setFortitudeMisc1(2);
		} else {
			pc.setFortitudeMisc2(pc.getFortitudeMisc2() + 2);
		}
		
		Utils.print("Setting hit points per level gained = 6");
		pc.setHitPointsPerLevelGained(6);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Great Weapon Fighter: You're interested in dealing out the most damage you can.  You prefer big two-handed weapons.");
		Utils.print("2. Guardian Fighter: You fight smarter.  You're willing to trade offense for superior defenses and better ability to control the battlefield.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Constitution your secondary score.  Choose powers that work with two handed weapons.");
			Utils.print("Suggested Feat: Power Attack (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Athletics, Endurance, Intimidate");
			Utils.print("Suggested At-Will Powers: Cleave, Reaping Strike");
			Utils.print("Suggested Encounter Power: Spinning Sweep");
			Utils.print("Suggested Daily Power: Brute Strike");
		} else {
			Utils.print("Make Strength your primary score and Dexterity or Wisdom your secondary score.  Choose powers that take advantage of your one handed weapon and shield.");
			Utils.print("Suggested Feat: Weapon Focus (Human feat: Human Perseverance)");
			Utils.print("Suggested Skills: Heal, Intimidate, Streetwise");
			Utils.print("Suggested At-Will Powers: Sure Strike, Tide of Iron");
			Utils.print("Suggested Encounter Power: Covering Attack");
			Utils.print("Suggested Daily Power: Comeback Strike");
		}
		
		// TODO: Combat Superiority
		Utils.print("NOTE: Looks like I may have implemented Combat Challenge and Fighter Weapon Talent already.  I'm not sure about Combat Superiority.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 15 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 9 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}
}
