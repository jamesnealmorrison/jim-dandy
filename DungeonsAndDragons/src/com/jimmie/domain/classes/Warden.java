package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;

import com.jimmie.powers.NaturesWrath;
import com.jimmie.powers.WardensFury;
import com.jimmie.powers.WardensGrasp;
import com.jimmie.util.Utils;

public class Warden extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean usedFormOfTheWillowSentinelAttack;
	private boolean usingFormOfTheWillowSentinel;
	private String willowSentinelImagePath;
	private String willowSentinelBloodiedImagePath;
	private GuardianMight guardianMight;

	@Override
	public void initializeForEncounter() {
	}

	@Override
	public void initializeForNewDay(DndCharacter dndCharacter) {
	}

	/* This does not have annotation.  It gets called directly. */
	public int formOfTheWillowSentinelAttack(AttackTarget target) {
		setUsedFormOfTheWillowSentinelAttack(true);
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		int attackRoll = owner.attackRoll(AbilityType.STRENGTH, AccessoryType.NONE, targets);
		
		if (attackRoll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getWeapon().getDamageDice();

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner), DamageType.NORMAL, true, owner);
			
			Utils.print(target.getName() + " gets a -4 penalty to the attack roll.");
			return -4;
		} else {
			Utils.print("You missed " + target.getName() + ".  Doing half damage.");
			int damageRolls = owner.getReadiedWeapon().getWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getWeapon().getDamageDice();

			target.hurt(Utils.rollForHalfDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner), DamageType.NORMAL, false, owner);
			
			Utils.print(target.getName() + " gets a -2 penalty to the attack roll.");
			
			// Some targets have powers/effects that happen when they are missed.
			target.miss(owner);
			return -2;
		}
	}

	public boolean isUsingFormOfTheWillowSentinel() {
		return usingFormOfTheWillowSentinel;
	}

	public void setUsingFormOfTheWillowSentinel(boolean usingFormOfTheWillowSentinel) {
		this.usingFormOfTheWillowSentinel = usingFormOfTheWillowSentinel;
	}

	public boolean isUsedFormOfTheWillowSentinelAttack() {
		return usedFormOfTheWillowSentinelAttack;
	}

	public void setUsedFormOfTheWillowSentinelAttack(
			boolean usedFormOfTheWillowSentinelAttack) {
		this.usedFormOfTheWillowSentinelAttack = usedFormOfTheWillowSentinelAttack;
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Nature");
		Utils.print("Automatically trained in Nature.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Dungeoneering");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
		choices.add("Perception");
		
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
		
		Utils.print("Setting power Source to Primal.");
		pc.setPowerSource(PowerSource.PRIMAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Light Shield, Heavy Shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		pc.addArmorGroupProficiency(ArmorGroup.HEAVY_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 7");
		pc.setHitPointsPerLevelGained(7);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Earth Warden: You draw primal power through a link with the land, and the power of the earth strengthens and sustains you.");
		Utils.print("2. Wild Warden: Your blood is a medium of primal power; nature's vigor pulses in your veins.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Constitution your secondary score.");
			Utils.print("Suggested Class Feature: Earthstrength");
			Utils.print("Suggested Feat: Crushing Earthstrength");
			Utils.print("Suggested Skills: Athletics, Heal, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Earth Shield Strike, Strength of Stone");
			Utils.print("Suggested Encounter Power: Thunder Ram Assault");
			Utils.print("Suggested Daily Power: Form of the Willow Sentinel");
		} else {
			Utils.print("Make Strength your primary score and Wisdom your secondary score.");
			Utils.print("Suggested Class Feature: Wildblood");
			Utils.print("Suggested Feat: Wildblood Cunning");
			Utils.print("Suggested Skills: Athletics, Intimidate, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Thorn Strike, Weight of Earth");
			Utils.print("Suggested Encounter Power: Wildblood Frenzy");
			Utils.print("Suggested Daily Power: Form of the Relentless Panther");
		}
		
		Utils.print("Choose between the following Guardian Mights:");
		Utils.print("1. Earthstrength.");
		Utils.print("2. Wildblood.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setGuardianMight(GuardianMight.EARTHSTRENGTH);
		} else {
			setGuardianMight(GuardianMight.WILDBLOOD);
		}
		
		pc.addPower(new WardensFury());
		pc.addPower(new WardensGrasp());
		
		// Implementing "Nature's Wrath" as a power.
		pc.addPower(new NaturesWrath());
		
		// TODO: Font of Life, Guardian Might, Nature's Wrath 
		Utils.print("NOTE: I have not yet coded Font of Life, Guardian Might, Nature's Wrath.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 17 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 9 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public GuardianMight getGuardianMight() {
		return guardianMight;
	}

	public void setGuardianMight(GuardianMight guardianMight) {
		this.guardianMight = guardianMight;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getWillowSentinelImagePath() {
		return willowSentinelImagePath;
	}

	public void setWillowSentinelImagePath(String willowSentinelImagePath) {
		this.willowSentinelImagePath = willowSentinelImagePath;
	}

	public String getWillowSentinelBloodiedImagePath() {
		return willowSentinelBloodiedImagePath;
	}

	public void setWillowSentinelBloodiedImagePath(String willowSentinelBloodiedImagePath) {
		this.willowSentinelBloodiedImagePath = willowSentinelBloodiedImagePath;
	}
}
