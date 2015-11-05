package com.jimmie.domain.classes;

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
import com.jimmie.util.FreeAction;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class Psion extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KINETIC_TRAWL = "Kinetic Trawl";
	public static final String FORCE_PUNCH = "Force Punch";
	public static final String MEMORY_HOLE = "Memory Hole";
	public static final String FAR_HAND = "Far Hand";
	public static final String FORCEFUL_PUSH = "Forceful Push";
	public static final String TELEKINETIC_ANCHOR = "Telekinetic Anchor";
	private int powerPoints;
	private boolean usedForcefulPush;
	private boolean usedTelekineticAnchor;
	private PsionDiscipline disciplineFocus;

	@Override
	public void initializeForEncounter() {
		/* TODO: More power points at higher levels. */
		setPowerPoints(2);
		usedForcefulPush = false;
	}

	@Override
	public void initializeForNewDay() {
		usedTelekineticAnchor = false;
	}

	@StandardAction(menuName = FORCE_PUNCH, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = false, weaponTag = false, arcaneTag = false, primalTag = false, psionicTag = true)
	@AtWillPower
	public void forcePunch(Encounter encounter) {
		/* See if they want to augment. */
		int augment = 0;
		int range = 0;
		if (powerPoints > 0) {
			Utils.print("You have " + powerPoints + " power points left.  How many to you want to spend?");
			if (powerPoints > 2) {
				range = 2;
			} else {
				range = powerPoints;
			}
			augment = Utils.getValidIntInputInRange(0, range);
			powerPoints = powerPoints - augment;
		}
		
		AttackTarget target = encounter.chooseMeleeTarget(owner, 1);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetFortitude = target.getFortitude(encounter, owner);
		Utils.print("Your target has an Fortitude of " + targetFortitude);
		
		if (roll >= targetFortitude) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.EIGHT_SIDED;

			if (augment == 2) {
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.FORCE_DAMAGE, encounter, true);
			} else {
			    target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE), owner.getRace()), DamageType.FORCE_DAMAGE, encounter, true);
			}
			
			int targetPushDistance = 1;
			
			if (augment == 1) {
				targetPushDistance = owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM);
			}
			
			if (augment == 2) {
				/* TODO: Nothing is really implemented yet for being prone. */
				target.knockProne();
				Utils.print("JIM!!!!!!! YOU HAVE NOT IMPLEMENTED BEING PRONE YET!!!!!");
			}
			
			String pushDirection = encounter.getPushDirection(owner.getCurrentPosition(), target.getCurrentPosition());
			for (int i = 0; i < targetPushDistance; i++) {
			    target.push(pushDirection);
			}
			
			/* Push each adjacent enemy 1. */
			List<Creature> adjacentEnemies = encounter.getAdjacentEnemies(owner);
			
			if (adjacentEnemies != null) {
				for (Creature adjacentEnemy : adjacentEnemies) {
					pushDirection = encounter.getPushDirection(owner.getCurrentPosition(), adjacentEnemy.getCurrentPosition());
					adjacentEnemy.push(pushDirection);
				}
			}
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = KINETIC_TRAWL, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, weaponTag = false, arcaneTag = false, primalTag = false, psionicTag = true)
	@AtWillPower
	public void kineticTrawl(Encounter encounter) {
		/* TODO: The book says this power can be used unaugmented as a ranged basic attack. */ 
		
		/* See if they want to augment. */
		int augment = 0;
		int range = 0;
		if (powerPoints > 0) {
			Utils.print("You have " + powerPoints + " power points left.  How many to you want to spend?");
			if (powerPoints > 2) {
				range = 2;
			} else {
				range = powerPoints;
			}
			augment = Utils.getValidIntInputInRange(0, range);
			powerPoints = powerPoints - augment;
		}
		
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetReflex = target.getReflex(owner);
		Utils.print("Your target has a reflex of " + targetReflex);
		
		if (roll >= targetReflex) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 1;
			
			if (augment == 2) {
				damageRolls = 2;
			}
					
			DiceType damageDiceType = DiceType.EIGHT_SIDED;
			
			if (augment > 0) {
				damageDiceType = DiceType.TEN_SIDED;
			}

		    target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE), owner.getRace()), DamageType.FORCE_DAMAGE, encounter, true);
			
			int targetPullDistance = 1;
			
			if (augment > 0) {
				targetPullDistance = owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM);
			}
			
			for (int i = 0; i < targetPullDistance; i++) {
				String pullDirection = encounter.getPullDirection(owner.getCurrentPosition(), target.getCurrentPosition());
			    target.pull(pullDirection);
			}
			
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = MEMORY_HOLE, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, weaponTag = false, arcaneTag = false, primalTag = false, psionicTag = true)
	@AtWillPower
	public void memoryHole(Encounter encounter) {
		/* See if they want to augment. */
		int augment = 0;
		int range = 0;
		if (powerPoints > 0) {
			Utils.print("You have " + powerPoints + " power points left.  How many to you want to spend?");
			if (powerPoints > 2) {
				range = 2;
			} else {
				range = powerPoints;
			}
			augment = Utils.getValidIntInputInRange(0, range);
			powerPoints = powerPoints - augment;
		}
		
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		if (augment == 2) {
			Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int x = Utils.getValidIntInputInRange(1, 50);
			
			Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int y = Utils.getValidIntInputInRange(1, 50);

			/* Got to do this wierd conversion between creatures and attack targets. */
			List<Creature> creatureTargets = encounter.getAllCreaturesInAreaBurst(x, y, 1);
			for (Creature creature : creatureTargets) {
				targets.add(creature);
			}
		} else {
			Creature target = (Creature) encounter.chooseRangedTarget(owner, 10, 10);
			Utils.print("UMMMMM....." + target.getName() + " laughs at you because I forgot to finish programming this attack.  DORK!");
		}
		
		int damageRolls = 1;
		
		if (augment == 2) {
			damageRolls = 2;
		}
				
		Utils.print("Since this might affect multiple targets, rolling for damage first.");
		Dice damageDice = new Dice(DiceType.SIX_SIDED);
 		int damage = 0;
 		
 		for (int rolls = 0; rolls < damageRolls; rolls++) {
 		    damage = damage + damageDice.basicRoll();
 		}
 		damage = damage + owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE);
		
 		List<Creature> hitTargets = new ArrayList<Creature>();
		DurationType durationType = DurationType.START_OF_NEXT_TURN;
		if (augment == 1) {
			durationType = DurationType.END_OF_NEXT_TURN;
		}
		for (AttackTarget target : targets) {
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
			int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
			
			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
			
			int targetWill = target.getWill(owner);
			Utils.print("Your target has a Will of " + targetWill);
			
			if (roll >= targetWill) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				/* See if this target was hit by Stirring Shout. */
				if (target.isHitByStirringShout()) {
					Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
					owner.heal(target.getStirringShoutCharismaModifier());
				}
		
				target.hurt(damage, DamageType.PSYCHIC_DAMAGE, encounter, true);
				
				hitTargets.add((Creature) target);
				Utils.print("You just became invisible to " + target.getName());
			} else {
			    Utils.print("Sorry.  You missed " + target.getName());
			}
		}
		owner.setTemporaryInvisibility(owner, durationType, hitTargets);
	}

	public int getPowerPoints() {
		return powerPoints;
	}

	public void setPowerPoints(int powerPoints) {
		this.powerPoints = powerPoints;
	}

	@FreeAction(menuName = FORCEFUL_PUSH)
	@EncounterPower
	public void forcefulPush(Encounter encounter) {
		if (!usedForcefulPush) {
			usedForcefulPush = true;
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
			
		int targetSlideDistance = 0;
		if (owner.getLevel() < 11) {
			targetSlideDistance = 1;
		} else if (owner.getLevel() < 21) {
			targetSlideDistance = 2;
		} else {
			targetSlideDistance = 3;
		}
			
		for (int i = 0; i < targetSlideDistance; i++) {
			Utils.print("What direction do you want to slide them (N, E, S, W, NE, NW, SE, SW, STOP)?");
		    List<String> validDirections = new ArrayList<String>();

		    validDirections.add("N");
		    validDirections.add("E");
		    validDirections.add("S");
		    validDirections.add("W");
		    validDirections.add("NE");
		    validDirections.add("NW");
		    validDirections.add("SE");
		    validDirections.add("SW");
		    validDirections.add("STOP");

		    Utils.print("Your choice?");
		    String direction = Utils.getValidInput(validDirections);
		    if ("STOP".equalsIgnoreCase(direction)) {
		    	break;
		    }
			
		    target.slide(direction);
		}
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Forceful Push in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
		}
	}

	@StandardAction(menuName = TELEKINETIC_ANCHOR, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, weaponTag = false, arcaneTag = false, primalTag = false, psionicTag = true)
	@DailyPower
	public void telekineticAnchor(Encounter encounter) {
		if (!usedTelekineticAnchor) {
			usedTelekineticAnchor = true;
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
		int x = Utils.getValidIntInputInRange(1, 50);
		
		Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
		int y = Utils.getValidIntInputInRange(1, 50);

		/* Got to do this wierd conversion between creatures and attack targets. */
		List<Creature> creatureTargets = encounter.getAllCreaturesInAreaBurst(x, y, 1);
		for (Creature creature : creatureTargets) {
			targets.add(creature);
		}

		Utils.print("Since this might affect multiple targets, rolling for damage first.");
		int damageRolls = 3;
		
 		Dice damageDice = new Dice(DiceType.SIX_SIDED);
 		int damage = 0;
 		
 		for (int rolls = 0; rolls < damageRolls; rolls++) {
 		    damage = damage + damageDice.basicRoll() ;
 		}
 		damage = damage + owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE);
		
		for (AttackTarget target : targets) {
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
			int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
			
			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
			
			int targetFortitude = target.getFortitude(encounter, owner);
			Utils.print("Your target has an Fortitude of " + targetFortitude);
			
			if (roll >= targetFortitude) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				/* See if this target was hit by Stirring Shout. */
				if (target.isHitByStirringShout()) {
					Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
					owner.heal(target.getStirringShoutCharismaModifier());
				}
		
				target.hurt(damage, DamageType.FORCE_DAMAGE, encounter, true);
			} else {
			    Utils.print("Sorry.  You missed " + target.getName());
			}
			
			/* Whether it hits or not, the target will take 5 force damage if it moves on its next turn. */
			target.hitByTelekineticAnchor();
		}
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Telekinetic Anchor today.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though.");
			owner.setUsedStandardAction(false);			
		}
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Perception");
		
		Utils.print("Choose 4 of the following");
		for (int i = 0; i < 4; i++) {
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
		Utils.print("Setting role to Controller.");
		pc.setRole(Role.CONTROLLER);
		
		Utils.print("Setting power Source to Psionic.");
		pc.setPowerSource(PowerSource.PSIONIC);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +2 Will");
		if (pc.getWillMisc1() == 0) {
			pc.setWillMisc1(2);
		} else {
			pc.setWillMisc2(pc.getWillMisc2() + 2);
		}

		Utils.print("Setting hit points per level gained = 4");
		pc.setHitPointsPerLevelGained(4);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Telekinetic Psion: You prefer to use mental force on creatures and objects.");
		Utils.print("2. Thaneborn Barbarian: Telepathic Psion: You focus on influencing your enemies' minds.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Intelligence your primary score and Wisdom your secondary score.");
			Utils.print("Suggested Class Feature: Telekinesis Focus");
			Utils.print("Suggested Feat: Controlling Advantage");
			Utils.print("Suggested Skills: Arcana, Dungeoneering, Insight, Perception");
			Utils.print("Suggested At-Will Powers: Kinetic Trawl, Force Punch");
			Utils.print("Suggested Daily Power: Telekinetic Anchor");
		} else {
			Utils.print("Make Intelligence your primary score and Charisma your secondary score.");
			Utils.print("Suggested Class Feature: Telepathy Focus");
			Utils.print("Suggested Feat: Precise Mind");
			Utils.print("Suggested Skills: Arcana, Bluff, Diplomacy, Insight");
			Utils.print("Suggested At-Will Powers: Memory Hole, Mind Thrust");
			Utils.print("Suggested Daily Power: Mental Trauma");
		}
		
		Utils.print("Choose between the following DisciplineFocuses:");
		Utils.print("1. Telekinesis Focus.");
		Utils.print("2. Telepathy Focus.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setDisciplineFocus(PsionDiscipline.TELEKINESIS_FOCUS);
		} else {
			setDisciplineFocus(PsionDiscipline.TELEPATHY_FOCUS);
		}
		
		// TODO: Discipline Focus, Psionic Augmentation, Ritual Casting, Implements
		Utils.print("NOTE: I have not yet coded Discipline Focus, Psionic Augmentation, Ritual Casting, Implements.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 6 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public PsionDiscipline getDisciplineFocus() {
		return disciplineFocus;
	}

	public void setDisciplineFocus(PsionDiscipline disciplineFocus) {
		this.disciplineFocus = disciplineFocus;
	}
}
