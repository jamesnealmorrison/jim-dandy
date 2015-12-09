package com.jimmie.domain.creatures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import com.jimmie.domain.AbilityType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AlternativeMovementMode;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.CombatAdvantage;
import com.jimmie.domain.CombatAdvantageType;
import com.jimmie.domain.Condition;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.Effect;
import com.jimmie.domain.Equipment;
import com.jimmie.domain.Mark;
import com.jimmie.domain.MarkType;
import com.jimmie.domain.MovementType;
import com.jimmie.domain.Position;
import com.jimmie.domain.Prone;
import com.jimmie.domain.Sense;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.TemporaryAttackRollModifier;
import com.jimmie.domain.TemporaryBonus;
import com.jimmie.domain.TemporaryDamageResistance;
import com.jimmie.domain.TemporaryInvisibility;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.items.armor.Armor;
import com.jimmie.domain.items.weapons.Hand;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.powers.AttackPower;
import com.jimmie.powers.Power;
import com.jimmie.util.Dice;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.Utils;

public abstract class Creature implements Serializable, TurnTaker, AttackTarget {
	public Creature() {
		damageResistances = new HashMap<DamageType, Integer>();
		damageVulnerabilities = new HashMap<DamageType, Integer>();		
		powers = new ArrayList<Power>();
		/* Initialize skills. */
		addSkill(new Skill(SkillType.ACROBATICS));
		addSkill(new Skill(SkillType.ARCANA));
		addSkill(new Skill(SkillType.ATHLETICS));
		addSkill(new Skill(SkillType.BLUFF));
		addSkill(new Skill(SkillType.DIPLOMACY));
		addSkill(new Skill(SkillType.DUNGEONEERING));
		addSkill(new Skill(SkillType.ENDURANCE));
		addSkill(new Skill(SkillType.HEAL));
		addSkill(new Skill(SkillType.HISTORY));
		addSkill(new Skill(SkillType.INSIGHT));
		addSkill(new Skill(SkillType.INTIMIDATE));
		addSkill(new Skill(SkillType.NATURE));
		addSkill(new Skill(SkillType.PERCEPTION));
		addSkill(new Skill(SkillType.RELIGION));
		addSkill(new Skill(SkillType.STEALTH));
		addSkill(new Skill(SkillType.STREETWISE));
		addSkill(new Skill(SkillType.THIEVERY));
	}

	private void addSkill(Skill skill) {
		if (skills == null) {
			skills = new ArrayList<Skill>();
		}
		skills.add(skill);
	}
	
	protected TemporaryBonus temporaryArmorClassBonus;
	protected TemporaryBonus temporaryReflexBonus;
	protected TemporaryBonus temporaryFortitudeBonus;
	protected TemporaryBonus temporaryWillBonus;
	protected TemporaryDamageResistance temporaryDamageResistance;
	protected TemporaryAidAnotherBonus temporaryAidAnotherBonus;
	private String imagePath;
	protected Role role;
	protected boolean usedSecondWind = false;

	public TemporaryAidAnotherBonus getTemporaryAidAnotherBonus() {
		return temporaryAidAnotherBonus;
	}

	Creature pursuer = null;
	boolean hitByStirringShout = false;
	boolean hitByTelekineticAnchor = false;

	public boolean isHitByStirringShout() {
		return hitByStirringShout;
	}

	int stirringShoutCharismaModifier = 0;

	public int getStirringShoutCharismaModifier() {
		return stirringShoutCharismaModifier;
	}

	@Override
	public void hitByBondOfPursuit(Creature pursuer) {
		this.pursuer = pursuer;		
	}

	@Override
	public void hitByTelekineticAnchor() {
		hitByTelekineticAnchor = true;		
	}

	@Override
	public void hitByStirringShout(int charismaModifier) {
		hitByStirringShout = true;
		stirringShoutCharismaModifier = charismaModifier;
	}

	@Override
	public boolean isStunned() {
		return false;
	}


	@Override
	public boolean isBlinded() {
		return false;
	}

	private static final long serialVersionUID = 1L;
	private Position currentPosition;
	protected String name;
	protected String displayName;

	public String getDisplayName() {
		if (displayName == null) {
			return name;
		}
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isUsedMinorAction() {
		return usedMinorAction;
	}


	public void setUsedMinorAction(boolean usedMinorAction) {
		this.usedMinorAction = usedMinorAction;
	}


	public boolean isUsedMoveAction() {
		return usedMoveAction;
	}


	public void setUsedMoveAction(boolean usedMoveAction) {
		this.usedMoveAction = usedMoveAction;
	}


	public boolean isUsedStandardAction() {
		return usedStandardAction;
	}


	public void setUsedStandardAction(boolean usedStandardAction) {
		this.usedStandardAction = usedStandardAction;
	}

	private boolean usedMinorAction;
	private boolean usedMoveAction;
	private boolean usedStandardAction;
	private int currentTurn;

	public void initializeForEncounter() {
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int roll = d.basicRoll();
		initiativeRoll = roll + initiative;
		setCurrentTurn(0);
		
		// Initialize powers.
		for (Power power : powers) {
			power.initializeForEncounter();
		}
	}

	public void initializeForNewDay() {
		actionPoints = 0;
	}

	private int initiativeRoll;
	private MovementType movementType;

	public int getInitiativeRoll() {
		return initiativeRoll;
	}


	public void setInitiativeRoll(int initiativeRoll) {
		this.initiativeRoll = initiativeRoll;
	}


	public List<Condition> getCurrentConditions() {
		return currentConditions;
	}


	public void setCurrentConditions(List<Condition> currentConditions) {
		this.currentConditions = currentConditions;
	}


	public List<Effect> getCurrentEffects() {
		return currentEffects;
	}


	public void setCurrentEffects(List<Effect> currentEffects) {
		this.currentEffects = currentEffects;
	}

	public boolean canTakeMinorAction() {
		if (!usedMinorAction || !usedMoveAction || !usedStandardAction) {
			return true;
		} else {
			return false;
		}

	}

	public boolean canTakeMoveAction() {
		if (!usedMoveAction || !usedStandardAction) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canTakeStandardAction() {
		if (!usedStandardAction) {
			return true;
		} else {
			return false;
		}
	}

	private int getAbilityModifier(int ability) {
		return (int) (Math.floor(ability/2) - 5);
	}
	
	public abstract boolean isShieldReadied();

	public void useMoveAction(Encounter encounter) {
		if (!canTakeMoveAction()) {
			Utils.print("I don't know how you got in this method (useMoveAction), but you can't!!!");
			return;
		}
		if (!usedMoveAction) {
			usedMoveAction = true;
		} else if (!usedStandardAction) {
			usedStandardAction = true;
		}

		/* Find out if they want to shift or walk. */
		Utils.print("What does " + getName() + " want to do? ");
		Utils.print("Possible choices are:");
		List<String> validActions = new ArrayList<String>();

		Utils.print("Walk");
		validActions.add("Walk");
		Utils.print("Shift");
		validActions.add("Shift");

		Utils.print("Your choice?");
		String choice = Utils.getValidInput(validActions);

		/* Right now, the only difference between walk and shift is the distance.
		 * Later I'll add things like checking for opportunity attacks, etc.
		 */

		int distanceLeft = 0;
		if ("Walk".equalsIgnoreCase(choice)) {
			movementType = MovementType.WALKING;
			distanceLeft = getSpeed();
		} else if ("Shift".equalsIgnoreCase(choice)) {
			movementType = MovementType.SHIFTING;
			distanceLeft = 1;
		}

		while (distanceLeft > 0) {
			Utils.print("What direction do you want to move (N, E, S, W, NE, NW, SE, SW, STOP)?");
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
				distanceLeft = 0;
			} else {
				Position newPosition = encounter.getPositionRelativeTo(getCurrentPosition(), direction);
				/* Do they have to perform a check to enter? */
				if (encounter.requiresCheckToEnter(newPosition, getCurrentPosition())) {
					int costForEntering = encounter.getCostForEnteringSquare();
					if (distanceLeft < costForEntering) {
						Utils.print("Sorry.  Don't have enough movement left to enter that square.");
						continue;
					}
					SkillCheck skillCheck = encounter.getGenericSkillCheck();
					if (performSkillCheck(skillCheck,encounter)) {
						Utils.print("You successfully passed the skill check.");
						/* An extra 1 will be subtracted later. */
						distanceLeft = distanceLeft - (costForEntering - 1);
					} else {
						Utils.print("You failed the skill check.  Staying where you are.");
						continue;
					}
				}/* Are they about to move into difficult terrain? */
				else if (encounter.isDifficultTerrain(encounter.getPositionRelativeTo(getCurrentPosition(), direction))) {
					if (distanceLeft < 2) {
						Utils.print("Sorry.  Can't enter that square.  It's difficult terrain.");
						continue;
					} else {
						Utils.print("Entering difficult terrain.  Costs two movements.");
						distanceLeft--;
					}
				}

				moveCreature(direction, encounter, movementType);
				distanceLeft--;

			}
		}

	}

	public Power getBasicMeleeAttack() {
		HashMap<Integer, Power> basicMeleeAttacks = new HashMap<Integer, Power>();
		int count = 0;
		for (Power power : getPowers()) {
			if (AttackPower.class.isAssignableFrom(power.getClass())) {
				if (power.isBasicAttack() && ((AttackPower)power).isMeleeAttack()) {
					// If it's a melee weapon attack, make sure they have a melee weapon readied.
					AttackType attackType = ((AttackPower)power).getAttackType();
					if ((attackType == AttackType.MELEE_OR_RANGED_WEAPON) || (attackType == AttackType.MELEE_WEAPON)) {
						if (getReadiedWeapon().getWeapon().isMeleeWeapon()) {
							count++;
							basicMeleeAttacks.put(count, power);							
						}
					}
				}
			}
		}
		
		if (count == 1) {
			return basicMeleeAttacks.get(1);
		} else {
			// TODO: Implement choosing from multiples.
		}
		return basicMeleeAttacks.get(1);
	}

	private boolean performSkillCheck(SkillCheck skillCheck, Encounter encounter) {
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.basicRoll();
		int roll = diceRoll + getSkillModifier(skillCheck.getSkillType());

		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

		Utils.print("Your target DC was + " + skillCheck.getDifficultyClass());

		if (roll >= skillCheck.getDifficultyClass()) {
			Utils.print("You successfully completed this skill check.");
			return true;
		} else {
			Utils.print("FAIL!");
			return false;
		}

	}

	public int getSkillModifier(SkillType skillType) {
		int baseSkillLevel = 0;
		
		// Grab the specified skill.
		Skill skill = getSkill(skillType);
		
		if (skill == null) {
			return 0;
		} else {
			baseSkillLevel = getAbilityModifierPlusHalfLevel(skill.getAbilityType());
			if (skill.isTrained()) {
				baseSkillLevel += 5;
			}
			if (skill.hasArmorPenalty()) {
				if (Character.class.isAssignableFrom(this.getClass())) {
					Armor armor = ((Character) this).getReadiedArmor();
					baseSkillLevel += armor.getSkillPenalty(); 
				}
			}
			baseSkillLevel += skill.getMisc();
		}

		return baseSkillLevel;
	}

	public int getAbilityModifierPlusHalfLevel(AbilityType abilityType) {
		return (int) (getAbilityModifier(abilityType) + Math.floor(getLevel()/2));
	}

	public int getAbilityModifier(AbilityType abilityType) {
		if (abilityType == AbilityType.STRENGTH) {
			return getAbilityModifier(getStrength());
		} else if (abilityType == AbilityType.CONSTITUTION) {
			return getAbilityModifier(getConstitution());
		} else if (abilityType == AbilityType.DEXTERITY) {
			return getAbilityModifier(getDexterity());
		} else if (abilityType == AbilityType.INTELLIGENCE) {
			return getAbilityModifier(getIntelligence());
		} else if (abilityType == AbilityType.WISDOM) {
			return getAbilityModifier(getWisdom());
		} else {
			return getAbilityModifier(getCharisma());
		}
	}

	public Skill getSkill(SkillType skillType) {
		for (Skill skill : skills) {
			if (skill.getSkillType() == skillType) {
				return skill;
			}
		}
		return null;
	}

	public void performOpportunityAttack(Creature target, Encounter encounter) {
		HashMap<Integer, Power> validActions = new HashMap<Integer, Power>();
		int index = 0;
		if (this.getPowers() != null) {
			for (Power power : getPowers()) {
				if (power.getActionType() == ActionType.STANDARD) {
					if (power.isBasicAttack()) {
						/* Still might have to meet other requirements to use this power now. */
						if (!power.meetsPrerequisitesToChoosePower(this) || !power.meetsRequirementsToUsePower(this)) {
							/* Skip it. */
							continue;
						}
						index++;
						validActions.put(index, power);
						Utils.print(index + ". " + power.getName() + " (" + power.getPowerUsage() + ")");
					}
				}
			}
		}

		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, index);
		Power chosenPower = validActions.get(choice);

		Utils.print("Make sure you choose " + target.getName() + " in the next question, since I haven't automated that yet.");
		chosenPower.process(encounter, this);
	}


	public void moveCreature(String direction, Encounter encounter, MovementType movementType) {
		/* See if I was hit by Telekinetic Anchor.  If so, I take 5 force damage, but only once. */
		if (hitByTelekineticAnchor) {
			Utils.print(getName() + " was previously hit by telekinetic anchor and takes 5 force damage now.");
			hurt(5, DamageType.FORCE, encounter, true);
			hitByTelekineticAnchor = false;
		}

		/* For now, I'm trusting the user input, and not doing any validation about whether the creature can actually
		 * move that direction or not.
		 */
		if ("N".equalsIgnoreCase(direction)) {
			currentPosition.setY(currentPosition.getY()+1);		
		} else if ("E".equalsIgnoreCase(direction)) {
			currentPosition.setX(currentPosition.getX()+1);
		} else if ("S".equalsIgnoreCase(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
		} else if ("W".equalsIgnoreCase(direction)) {
			currentPosition.setX(currentPosition.getX()-1);
		} else if ("NE".equalsIgnoreCase(direction)) {
			currentPosition.setX(currentPosition.getX()+1);
			currentPosition.setY(currentPosition.getY()+1);
		} else if ("NW".equalsIgnoreCase(direction)) {
			currentPosition.setX(currentPosition.getX()-1);
			currentPosition.setY(currentPosition.getY()+1);
		} else if ("SE".equalsIgnoreCase(direction)) {
			currentPosition.setX(currentPosition.getX()+1);
			currentPosition.setY(currentPosition.getY()-1);
		} else if ("SW".equalsIgnoreCase(direction)) {
			currentPosition.setX(currentPosition.getX()-1);
			currentPosition.setY(currentPosition.getY()-1);
		}
	}

	public void startOfTurn() {
		turnOver = false;
		usedMinorAction = false;
		usedMoveAction = false;
		usedStandardAction = false;
		currentTurn++;
		/* Check for conditions and other effects that are over at the "start of your next turn. */
		if (temporaryInvisibility != null) {
			if ((temporaryInvisibility.getDuration() == DurationType.START_OF_NEXT_TURN) && (temporaryInvisibility.getStartTurn() < currentTurn)) {
				temporaryInvisibility = null;
				Utils.print("Its the start of " + getName() + "'s next turn, so the temporary invisibility is over!");
			}
		}

		if (temporaryArmorClassBonus != null) {
			if ((temporaryArmorClassBonus.getDuration() == DurationType.START_OF_NEXT_TURN) && (temporaryArmorClassBonus.getStartTurn() < currentTurn)) {
				temporaryArmorClassBonus = null;
				Utils.print("Its the start of " + getName() + "'s next turn, so the temporary armor class bonus is over!");
			}
		}
		if (temporaryWillBonus != null) {
			if ((temporaryWillBonus.getDuration() == DurationType.START_OF_NEXT_TURN) && (temporaryWillBonus.getStartTurn() < currentTurn)) {
				temporaryWillBonus = null;
				Utils.print("Its the start of " + getName() + "'s next turn, so the temporary Will bonus is over!");
			}
		}
		if (temporaryFortitudeBonus != null) {
			if ((temporaryFortitudeBonus.getDuration() == DurationType.START_OF_NEXT_TURN) && (temporaryFortitudeBonus.getStartTurn() < currentTurn)) {
				temporaryFortitudeBonus = null;
				Utils.print("Its the start of " + getName() + "'s next turn, so the temporary Fortitude bonus is over!");
			}
		}
		if (temporaryReflexBonus != null) {
			if ((temporaryReflexBonus.getDuration() == DurationType.START_OF_NEXT_TURN) && (temporaryReflexBonus.getStartTurn() < currentTurn)) {
				temporaryReflexBonus = null;
				Utils.print("Its the start of " + getName() + "'s next turn, so the temporary Reflex bonus is over!");
			}
		}

	}

	public void endOfTurn(Encounter encounter) {
		turnOver = true;
		/* Check for bond of pursuit. */
		if (pursuer != null) {
			/* If I didn't end my turn adjacent to my pursuer, they get to shift. */
			if (!(this.isAdjacentTo(pursuer))) {
				Utils.print("Pursuer (" + pursuer.getName() + ") gets to shift " + (pursuer.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)+1) + " squares.");
				Utils.print("Please note: You must end closer to " + this.getName() + ".  This is not enforced in the code though");
				pursuer.shift(pursuer.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)+1, true, encounter);
			}
			/* At the end of turn, the bond of pursuit is over. */
			pursuer = null;
		}
		hitByTelekineticAnchor = false;
	}

	protected int level;
	protected Size size;
	protected int currentHitPoints;
	protected int maxHitPoints;
	protected int reflex;
	protected int will;
	protected int actionPoints;
	protected List<Power> powers;
	protected Alignment alignment;
	protected List<String> languages;
	protected List<Skill> skills;
	protected int strength;
	protected int constitution;
	protected int dexterity;
	protected int intelligence;
	protected int wisdom;
	protected int charisma;
	protected List<Equipment> equipment;
	protected int initiative;
	protected List<Sense> senses;
	protected int baseSpeed;
	public List<AlternativeMovementMode> getAlternativeMovementModes() {
		return alternativeMovementModes;
	}


	public void setAlternativeMovementModes(
			List<AlternativeMovementMode> alternativeMovementModes) {
		this.alternativeMovementModes = alternativeMovementModes;
	}


	protected List<AlternativeMovementMode> alternativeMovementModes;
	protected List<Condition> currentConditions;
	protected List<Effect> currentEffects;
	private HashMap<DamageType, Integer> damageResistances;
	private HashMap<DamageType, Integer> damageVulnerabilities;


	public String getName() {
		if (name == null) {
			name = "I don't have a name. :-(";
		}
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public Size getSize() {
		return size;
	}


	public void setSize(Size size) {
		this.size = size;
	}


	public int getCurrentHitPoints() {
		return currentHitPoints;
	}


	public void setCurrentHitPoints(int currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}


	public int getMaxHitPoints() {
		return maxHitPoints;
	}


	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}


	public int getBloodyValue() {
		return maxHitPoints / 2;
	}


	/*	public void setBloodyValue(int bloodyValue) {
		this.bloodyValue = bloodyValue;
	}
	 */

	public abstract int getArmorClass(Creature attacker);

// This will all be rewritten when I do AOP	
/*
 	public int getFortitude(Encounter encounter, Creature attacker) {

		int bonus = 0;

		// See if I am standing next to a Warden that is using "Form of the Willow Sentinel." 
		List<Creature> adjacentAllies = encounter.getAdjacentAllies(this);
		for (Creature adjacentAlly : adjacentAllies) {
			if (Character.class.isAssignableFrom(adjacentAlly.getClass())) {
				DndClass dndClass = ((Character) adjacentAlly).getDndClass();
				if (Warden.class.isInstance(dndClass)) {
					if (((Warden) dndClass).isUsingFormOfTheWillowSentinel()) {
						Utils.print(getName() + " is currently adjacent to a Warden that is using Form of the Willow Sentinel and gets a +2 bonus to Fortitude.");
						bonus = bonus + 2;
					}
				}				
			}
		}

		// See if there is a temporary bonus to the fortitude.
		if (temporaryFortitudeBonus != null) {
			if (temporaryFortitudeBonus.stillApplies()) {
				Utils.print(name + " is supposed to get a bonus to fortitude until the start of " + temporaryFortitudeBonus.getSource().getName() + "'s next turn.");
				bonus = bonus + temporaryFortitudeBonus.getBonus();
				Utils.print("Bonus still applies.");
			} else {
				// Bonus is over.  Reset the bonus.
				temporaryFortitudeBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		// See if there is a temporary defense bonus due to the "Aid another" bonus.
		if ((temporaryAidAnotherBonus != null) && (temporaryAidAnotherBonus.getType() == TemporaryAidAnotherBonus.DEFENSE)) {
			if (temporaryAidAnotherBonus.stillApplies() && (temporaryAidAnotherBonus.getTarget() == attacker)) {
				Utils.print(name + " is supposed to get a bonus of " + temporaryAidAnotherBonus.getBonus() + " to defense against this attack by " + attacker.getName() + ".");
				bonus = bonus + temporaryAidAnotherBonus.getBonus();
				Utils.print("Bonus still applies.");
				temporaryAidAnotherBonus = null;
				Utils.print("One time bonus so bonus no longer applies.  Resetting bonus.");
			} else {
				// Bonus is over.  Reset the bonus. 
				temporaryAidAnotherBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		return getBaseFortitude() + bonus;
	}
*/
	public int getReflex(Creature attacker) {
		int bonus = 0;
		/* See if there is a temporary bonus to the will. */
		if (temporaryReflexBonus != null) {
			if (temporaryReflexBonus.stillApplies()) {
				Utils.print(name + " is supposed to get a bonus to reflex until the start of " + temporaryReflexBonus.getSource().getName() + "'s next turn.");
				bonus = bonus + temporaryReflexBonus.getBonus();
				Utils.print("Bonus still applies.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryReflexBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		/* See if there is a temporary defense bonus due to the "Aid another" bonus. */
		if ((temporaryAidAnotherBonus != null) && (temporaryAidAnotherBonus.getType() == TemporaryAidAnotherBonus.DEFENSE)) {
			if (temporaryAidAnotherBonus.stillApplies() && (temporaryAidAnotherBonus.getTarget() == attacker)) {
				Utils.print(name + " is supposed to get a bonus of " + temporaryAidAnotherBonus.getBonus() + " to defense against this attack by " + attacker.getName() + ".");
				bonus = bonus + temporaryAidAnotherBonus.getBonus();
				Utils.print("Bonus still applies.");
				temporaryAidAnotherBonus = null;
				Utils.print("One time bonus so bonus no longer applies.  Resetting bonus.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryAidAnotherBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		return reflex + bonus;
	}


	public void setReflex(int reflex) {
		this.reflex = reflex;
	}


	public int getWill(Creature attacker) {
		int currentWill = will;

		/* See if there is a temporary bonus to the will. */
		if (temporaryWillBonus != null) {
			if (temporaryWillBonus.stillApplies()) {
				Utils.print(name + " is supposed to get a bonus to will until the start of " + temporaryWillBonus.getSource().getName() + "'s next turn.");
				currentWill = currentWill + temporaryWillBonus.getBonus();
				Utils.print("Bonus still applies.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryWillBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		/* See if there is a temporary defense bonus due to the "Aid another" bonus. */
		if ((temporaryAidAnotherBonus != null) && (temporaryAidAnotherBonus.getType() == TemporaryAidAnotherBonus.DEFENSE)) {
			if (temporaryAidAnotherBonus.stillApplies() && (temporaryAidAnotherBonus.getTarget() == attacker)) {
				Utils.print(name + " is supposed to get a bonus of " + temporaryAidAnotherBonus.getBonus() + " to defense against this attack by " + attacker.getName() + ".");
				currentWill = currentWill + temporaryAidAnotherBonus.getBonus();
				Utils.print("Bonus still applies.");
				temporaryAidAnotherBonus = null;
				Utils.print("One time bonus so bonus no longer applies.  Resetting bonus.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryAidAnotherBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		return currentWill;
	}


	public void setWill(int will) {
		this.will = will;
	}


	public int getActionPoints() {
		return actionPoints;
	}


	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}


	public List<Power> getPowers() {
		return powers;
	}


	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}

	public void addPower(Power power) {
		if (powers == null) {
			powers = new ArrayList<Power>();
		}
		powers.add(power);
	}

	public void addDamageResistance(DamageType damageType, Integer resistance) {
		if (damageResistances == null) {
			damageResistances = new HashMap<DamageType, Integer>();
		}
		damageResistances.put(damageType, resistance);
	}

	public void addDamageVulnerability(DamageType damageType, Integer vulnerability) {
		if (damageVulnerabilities == null) {
			damageVulnerabilities = new HashMap<DamageType, Integer>();
		}
		damageVulnerabilities.put(damageType, vulnerability);
	}

	public Alignment getAlignment() {
		return alignment;
	}


	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}


	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public void addLanguage(String language) {
		if (languages == null) {
			languages = new ArrayList<String>();
		}
		languages.add(language);
	}

	public List<Skill> getSkills() {
		return skills;
	}


	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	public int getStrength() {
		return strength;
	}


	public void setStrength(int strength) {
		this.strength = strength;
	}


	public int getConstitution() {
		return constitution;
	}


	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}


	public int getDexterity() {
		return dexterity;
	}


	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}


	public int getIntelligence() {
		return intelligence;
	}


	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}


	public int getWisdom() {
		return wisdom;
	}


	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}


	public int getCharisma() {
		return charisma;
	}


	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}


	public List<Equipment> getEquipment() {
		return equipment;
	}


	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}


	public int getInitiative() {
		return initiative;
	}


	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}


	public List<Sense> getSenses() {
		return senses;
	}


	public void setSenses(List<Sense> senses) {
		this.senses = senses;
	}

	public void addSense(Sense sense) {
		if (senses == null) {
			senses = new ArrayList<Sense>();
		}
		senses.add(sense);
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}
	
	public int getSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int speed) {
		this.baseSpeed = speed;
	}

	public boolean isBloodied() {
		return (currentHitPoints <= getBloodyValue());
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void hurt(int damage, DamageType damageType, Encounter encounter, boolean hit) {
		/* Is this a minion? They don't get hurt by missed attacks. */
		if ((getMaxHitPoints() == 1) && (!hit)) {
			return;
		}
		/* Resistance. */
		int resistance = getDamageResistance(damageType);
		if (resistance > 0) {
			Utils.print(getName() + " has Resistance " + resistance + " to this type of damage.");
			damage = damage - resistance;
			if (damage < 0) {
				damage = 0;
			}
			Utils.print("Lowering to " + damage + " damage.");
		}

		/* Vulnerability. */
		int vulnerability = getDamageVulnerability(damageType);
		if (vulnerability > 0) {
			Utils.print(getName() + " has Vulnerability " + vulnerability + " to this type of damage.");
			damage = damage + resistance;
			Utils.print("Increasing to " + damage + " damage.");
		}

		if (temporaryHitPoints > 0) {
			Utils.print(name + " has " + temporaryHitPoints + ". Using those first.");
			if (temporaryHitPoints >= damage) {
				temporaryHitPoints = temporaryHitPoints - damage;
				Utils.print(temporaryHitPoints + " remaining.");
				damage = 0;
			} else {
				damage = damage - temporaryHitPoints;
				temporaryHitPoints = 0;
				Utils.print(temporaryHitPoints + " remaining.");
			}
		}
		Utils.print("Reducing hit points by " + damage + ".");
		currentHitPoints = currentHitPoints - damage;

		/* Are they dead/dying? */
		if (currentHitPoints <= 0) {
			/* If this is a monster, that means they are dead. */
			if (Monster.class.isInstance(this)) {
				Utils.print(name + " is dead.");
				encounter.removeCreature(this);
			} else {
				/* For now just do the same for player characters. */
				Utils.print(name + " is dead.");
				encounter.removeCreature(this);
			}
		}
	}

	private int getDamageResistance(DamageType damageType) {
		int resistance = 0;

		if (damageResistances.containsKey(damageType)) {
			resistance = damageResistances.get(damageType);
		}
		
		// Check for temporary damage resistance.
		if (temporaryDamageResistance != null) {
			if (temporaryDamageResistance.stillApplies()) {
				if ((temporaryDamageResistance.getDamageType() == DamageType.ALL) || (temporaryDamageResistance.getDamageType() == damageType)) {
					resistance += temporaryDamageResistance.getBonus();
				}
			} else {
				temporaryDamageResistance = null;
			}
		}
		return resistance;
	}

	private int getDamageVulnerability(DamageType damageType) {
		int vulnerability = 0;

		if (damageVulnerabilities.containsKey(damageType)) {
			vulnerability = damageVulnerabilities.get(damageType);
		}
		return vulnerability;
	}

	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public DndClass getDndClass() {
		return dndClass;
	}
	public void setDndClass(DndClass dndClass) {
		/* The "Character" class overrides this method.  Any one else setting
		 * this object is a monster or someone who shouldn't have a race/class.
		 */
		this.dndClass = null;
	}
	protected Race race;
	protected DndClass dndClass;
	private boolean turnOver;
	private Mark mark;

	private int temporaryHitPoints;
	private CombatAdvantage combatAdvantage;
	private TemporaryAttackRollModifier temporaryAttackRollModifier;
	private TemporaryInvisibility temporaryInvisibility;
	public int getTemporaryHitPoints() {
		return temporaryHitPoints;
	}


	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}


	public void push(String direction) {
		/* Check for things like "Form of the Willow Sentinel. */
		if (Character.class.isAssignableFrom(this.getClass())) {
			DndClass dndClass = ((Character) this).getDndClass();
			if (Warden.class.isInstance(dndClass)) {
				if (((Warden) dndClass).isUsingFormOfTheWillowSentinel()) {
					/* For now assume they wouldn't want to be pushed. */
					Utils.print(getName() + " is currently using the Form of the Willow Sentinel power and can not be pushed. ");
					return;
				}
			}
		}
		if ("N".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
		}
		if ("S".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
		}
		if ("E".equals(direction)) {
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("W".equals(direction)) {
			currentPosition.setX(currentPosition.getX()-1);
		}
		if ("NE".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("NW".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
			currentPosition.setX(currentPosition.getX()-1);
		}
		if ("SE".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("SW".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
			currentPosition.setX(currentPosition.getX()-1);
		}
		Utils.print("Just pushed " + name + " to (" + currentPosition.getX() + ", " + currentPosition.getY() + ").");
		Utils.print("Please note, The program did not check if this is out of map bounds or the condition of the terrain, or walls, etc.");
	}

	public void pull(String direction) {
		/* Check for things like "Form of the Willow Sentinel. */
		if (Character.class.isAssignableFrom(this.getClass())) {
			DndClass dndClass = ((Character) this).getDndClass();
			if (Warden.class.isInstance(dndClass)) {
				if (((Warden) dndClass).isUsingFormOfTheWillowSentinel()) {
					/* For now assume they wouldn't want to be pushed. */
					Utils.print(getName() + " is currently using the Form of the Willow Sentinel power and can not be pulled. ");
					return;
				}
			}
		}
		if ("N".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
		}
		if ("S".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
		}
		if ("E".equals(direction)) {
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("W".equals(direction)) {
			currentPosition.setX(currentPosition.getX()-1);
		}
		if ("NE".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("NW".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
			currentPosition.setX(currentPosition.getX()-1);
		}
		if ("SE".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("SW".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
			currentPosition.setX(currentPosition.getX()-1);
		}
		Utils.print("Just pulled " + name + " to (" + currentPosition.getX() + ", " + currentPosition.getY() + ").");
		Utils.print("Please note, The program did not check if this is out of map bounds or the condition of the terrain, or walls, etc.");
	}

	public void slide(String direction) {
		/* Check for things like "Form of the Willow Sentinel. */
		if (Character.class.isAssignableFrom(this.getClass())) {
			DndClass dndClass = ((Character) this).getDndClass();
			if (Warden.class.isInstance(dndClass)) {
				if (((Warden) dndClass).isUsingFormOfTheWillowSentinel()) {
					/* For now assume they wouldn't want to be pushed. */
					Utils.print(getName() + " is currently using the Form of the Willow Sentinel power and can not be slid. ");
					return;
				}
			}
		}
		if ("N".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
		}
		if ("S".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
		}
		if ("E".equals(direction)) {
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("W".equals(direction)) {
			currentPosition.setX(currentPosition.getX()-1);
		}
		if ("NE".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("NW".equals(direction)) {
			currentPosition.setY(currentPosition.getY()+1);
			currentPosition.setX(currentPosition.getX()-1);
		}
		if ("SE".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
			currentPosition.setX(currentPosition.getX()+1);
		}
		if ("SW".equals(direction)) {
			currentPosition.setY(currentPosition.getY()-1);
			currentPosition.setX(currentPosition.getX()-1);
		}
		Utils.print("Just slid " + name + " to (" + currentPosition.getX() + ", " + currentPosition.getY() + ").");
		Utils.print("Please note, The program did not check if this is out of map bounds or the condition of the terrain, or walls, etc.");
	}

	public int getCurrentTurn() {
		return currentTurn;
	}


	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public void setArmorClass(int armorClass) {
		Utils.print("You have somehow reached the setArmorClass method of the Creature class.  You should not do this.");	
	}


	public boolean isTurnOver() {
		return turnOver;
	}

	@Override
	public void markByCombatChallenge(Creature owner, DurationType duration) {
		mark = new Mark();
		mark.setTypeOfMark(MarkType.COMBAT_CHALLENGE);
		mark.setMarker(owner);
		mark.setDuration(duration);
		mark.setStartTurn(owner.getCurrentTurn());
	}

	@Override
	public void markByMisdirectedMark(Creature owner, Creature misdirectedMarker, DurationType duration) {
		mark = new Mark();
		mark.setTypeOfMark(MarkType.MISDIRECTED_MARK);
		mark.setMarker(owner);
		mark.setMisdirectedMarker(misdirectedMarker);
		mark.setDuration(duration);
		mark.setStartTurn(owner.getCurrentTurn());
	}

	public void grantCombatAdvantageViaWardensFury(Creature owner) {
		combatAdvantage = new CombatAdvantage();
		combatAdvantage.setTypeOfCombatAdvantage(CombatAdvantageType.WARDENS_FURY);
		combatAdvantage.setOwner(owner);
		combatAdvantage.setDuration(DurationType.END_OF_NEXT_TURN);
		combatAdvantage.setStartTurn(owner.getCurrentTurn());
	}

	public void setTemporaryAttackRollModifier(Creature owner, DurationType durationType, int attackRollModifier) {
		temporaryAttackRollModifier = new TemporaryAttackRollModifier();
		temporaryAttackRollModifier.setOwner(owner);
		temporaryAttackRollModifier.setDuration(durationType);
		temporaryAttackRollModifier.setStartTurn(owner.getCurrentTurn());
		temporaryAttackRollModifier.setAttackRollModifier(attackRollModifier);
	}

	public void setTemporaryInvisibility(Creature owner, DurationType durationType, List<Creature> target) {
		temporaryInvisibility = new TemporaryInvisibility();
		temporaryInvisibility.setOwner(owner);
		temporaryInvisibility.setDuration(durationType);
		temporaryInvisibility.setStartTurn(owner.getCurrentTurn());
		/* If target is null, then you became invisible to everyone. */
		if (target != null) {
			temporaryInvisibility.setTargets(target);
		}
	}

	public int getOtherAttackModifier(List<AttackTarget> targets, Encounter encounter) {
		int total = 0;

		/* Check for a temporary attack roll modifier. */
		if (hasTemporaryAttackRollModifier()) {
			Utils.print(name + " currently has a temporary attack roll modifier of " + temporaryAttackRollModifier.getAttackRollModifier());
			total = total + temporaryAttackRollModifier.getAttackRollModifier();
			/* If it was immediate duration, delete the modifier now. */
			if (temporaryAttackRollModifier.getDuration() == DurationType.IMMEDIATE) {
				temporaryAttackRollModifier = null;
			}
		}

		if (isMarked() && !targets.contains(mark.getMarker())) {
			Utils.print(getName() + " is currently marked by " + mark.getMarker().getName() + " and takes a -2 penalty because " + mark.getMarker().getName() + " is not being attacked.");
			total = total - 2;

			/* If I am marked by "Combat challenge", then the fighter that marked me gets to make a melee basic attack against me
			 * as an immediate interrupt.
			 */
			if (mark.getTypeOfMark() == MarkType.COMBAT_CHALLENGE) {
				Utils.print("UH OH!  " + mark.getMarker().getName() + " gets to make a basic melee attack against me because they marked me with Combat Challenge.");
				Utils.print("Make sure to pick me (" + getName() + ") when it asks who to attack.");
				/* Should be able to cast the marker to a character. */
				if (Character.class.isInstance(mark.getMarker())) {
					Power power = mark.getMarker().getBasicMeleeAttack();
					power.process(encounter, mark.getMarker());
				}
			}

			/* If I am marked by "Nature's Wrath", then the warden that marked me gets to make a Warden's Fury against me
			 * as an immediate interrupt.
			 */
			if (mark.getTypeOfMark() == MarkType.NATURES_WRATH) {
				Utils.print("UH OH!  " + mark.getMarker().getName() + " gets to make a Warden's Fury attack against me because they marked me with Nature's Wrath.");
				/* Should be able to cast the marker to a warden. */
				if (Warden.class.isInstance(mark.getMarker().getDndClass())) {
					((Warden) (mark.getMarker().getDndClass())).wardensFury(encounter, this);
				}
			}
		}

		/* Do I have combat advantage against the target? */
		/* I think I can only check this if it is a single target. */
		if (targets.size() == 1) {
			if (Utils.hasCombatAdvantage(this, (Creature) targets.get(0), encounter)) {
				Utils.print(getName() + " has combat advantage over " + targets.get(0).getName() + " and recieves a +2 bonus.");
				total = total + 2;
			}
		}

		return total;
	}


	public boolean isMarked() {
		if (mark != null) {
			if (mark.stillApplies()) {
				return true;
			} else {
				/* Mark is over.  Reset the mark. */
				mark = null;
				Utils.print("Mark no longer applies.  Resetting.");
				return false;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	private boolean hasTemporaryAttackRollModifier() {
		if (temporaryAttackRollModifier != null) {
			if (temporaryAttackRollModifier.stillApplies()) {
				return true;
			} else {
				/* temporaryAttackRollModifier is over.  Reset it. */
				temporaryAttackRollModifier = null;
				Utils.print("Temporary attack roll modifier no longer applies.  Resetting.");
				return false;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	/* There could be several reasons you are invisible.  For now, just checking for temporary invisibility, though. */
	public boolean isInvisibleTo(TurnTaker attacker) {
		if (temporaryInvisibility != null) {
			if (temporaryInvisibility.stillApplies()) {
				/* Does it apply to this creature? */
				if (temporaryInvisibility.getTargets() == null) {
					return true;
				} else {
					if (temporaryInvisibility.getTargets().contains(attacker)) {
						return true;
					}
				}
			} else {
				/* temporaryInvisibility is over.  Reset it. */
				temporaryInvisibility = null;
				Utils.print("Temporary invisibility no longer applies.  Resetting.");
				return false;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	public void shift(int distance, boolean free, Encounter encounter) {
		Utils.print(name + " is shifting " + distance + " squares.");

		if (!free) {
			if (!canTakeMoveAction()) {
				Utils.print("I don't know how you got in this method (useMoveAction), but you can't!!!");
				return;
			}
			if (!usedMoveAction) {
				usedMoveAction = true;
			} else if (!usedStandardAction) {
				usedStandardAction = true;
			}
		}

		/* Right now, the only difference between walk and shift is the distance.
		 * Later I'll add things like checking for opportunity attacks, etc.
		 */

		int distanceLeft = distance;
		if (isMarked() && (mark.getTypeOfMark() == MarkType.COMBAT_CHALLENGE)) {
			Utils.print("UH OH!  " + mark.getMarker().getName() + " gets to make a basic melee attack against me because they marked me with Combat Challenge.");
			Utils.print("Make sure to pick me (" + getName() + ") when it asks who to attack.");
			/* Should be able to cast the marker to a character. */
			if (Character.class.isInstance(mark.getMarker())) {
				Power power = mark.getMarker().getBasicMeleeAttack();
				power.process(encounter, mark.getMarker());
			}
		}

		while (distanceLeft > 0) {
			Utils.print("What direction do you want to move (N, E, S, W, NE, NW, SE, SW, STOP)?");
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
				distanceLeft = 0;
			} else {
				moveCreature(direction, encounter, MovementType.SHIFTING);
				distanceLeft--;
			}
		}
	}


	public void setTemporaryHitPoints(int i) {
		temporaryHitPoints = i;
	}


	public boolean isAdjacentTo(Creature target) {
		if (target.getCurrentPosition().isWithinReachOf(this.getCurrentPosition(), 1)) {
			return true;
		} else {
			return false;
		}
	}


	public boolean canFlank() {
		/* Some conditions don't allow you to flank. */
		if (isBlinded() || isDazed() || isDominated() || isDying() || isPetrified() || isStunned() || isSurprised() || isUnconscious()) {
			return false;
		} else {
			return true;
		}
	}


	private boolean isUnconscious() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean isSurprised() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean isPetrified() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean isDying() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean isDominated() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean isDazed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void knockProne() {
		currentConditions.add(new Prone());
	}

	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public ReadiedWeapon getReadiedWeapon() {
		Utils.print("Not sure how you got here, but you're in the Creature.getReadiedWeapon() method");
		return null;
	}

	public HashMap<Hand,ReadiedWeapon> getReadiedWeapons() {
		Utils.print("Not sure how you got here, but you're in the Creature.getReadiedWeapons() method");
		return null;
	}

	public int getWeaponProficiencyBonus() {
		Utils.print("Not sure how you got here, but you're in the Creature.getWeaponProficiencyBonus() method");
		return 0;
	}

	public void heal(int hitPoints) {
		int tempHitPoints = currentHitPoints + hitPoints;
		if (tempHitPoints >= this.maxHitPoints) {
			currentHitPoints = maxHitPoints;
			Utils.print("Fully healed.  Hit Points = " + currentHitPoints);
		} else {
			currentHitPoints = tempHitPoints;
			Utils.print("Partially healed.  Hit Points = " + currentHitPoints);
		}
	}

	public boolean usedSecondWind() {
		return usedSecondWind;
	}

	public void setTemporaryReflexBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source) {
		temporaryReflexBonus = new TemporaryBonus();
		temporaryReflexBonus.setBonus(bonus);
		temporaryReflexBonus.setStartTurn(bonusStartTurn);
		temporaryReflexBonus.setDuration(duration);
		temporaryReflexBonus.setSource(source);
	}

	public void setTemporaryWillBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source) {
		temporaryWillBonus = new TemporaryBonus();
		temporaryWillBonus.setBonus(bonus);
		temporaryWillBonus.setStartTurn(bonusStartTurn);
		temporaryWillBonus.setDuration(duration);
		temporaryWillBonus.setSource(source);
	}

	public void setTemporaryFortitudeBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source) {
		temporaryFortitudeBonus = new TemporaryBonus();
		temporaryFortitudeBonus.setBonus(bonus);
		temporaryFortitudeBonus.setStartTurn(bonusStartTurn);
		temporaryFortitudeBonus.setDuration(duration);
		temporaryFortitudeBonus.setSource(source);
	}

	public void setTemporaryAidAnotherBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source, AttackTarget target, int bonusType) {
		temporaryAidAnotherBonus = new TemporaryAidAnotherBonus();
		temporaryAidAnotherBonus.setBonus(bonus);
		temporaryAidAnotherBonus.setStartTurn(bonusStartTurn);
		temporaryAidAnotherBonus.setDuration(duration);
		temporaryAidAnotherBonus.setSource(source);
		temporaryAidAnotherBonus.setTarget(target);
		temporaryAidAnotherBonus.setType(bonusType);
	}

	public void setTemporaryDamageResistance(int bonus, int bonusStartTurn,
			DurationType duration, Creature source, DamageType damageType) {
		temporaryDamageResistance = new TemporaryDamageResistance();
		temporaryDamageResistance.setBonus(bonus);
		temporaryDamageResistance.setStartTurn(bonusStartTurn);
		temporaryDamageResistance.setDuration(duration);
		temporaryDamageResistance.setSource(source);
		temporaryDamageResistance.setDamageType(damageType);
	}

	public void setTemporaryArmorClassBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source) {
		temporaryArmorClassBonus = new TemporaryBonus();
		temporaryArmorClassBonus.setBonus(bonus);
		temporaryArmorClassBonus.setStartTurn(bonusStartTurn);
		temporaryArmorClassBonus.setDuration(duration);
		temporaryArmorClassBonus.setSource(source);
	}

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public void useAction(ActionType actionType) {
		if (actionType == ActionType.MINOR) {
			if (!usedMinorAction) {
				usedMinorAction = true;
			} else if (!usedMoveAction) {
				usedMoveAction = true;
			} else if (!usedStandardAction) {
				usedStandardAction = true;
			}			
		} else if (actionType == ActionType.MOVE) {
			if (!usedMoveAction) {
				usedMoveAction = true;
			} else if (!usedStandardAction) {
				usedStandardAction = true;
			}			
		} else if (actionType == ActionType.STANDARD) {
			usedStandardAction = true;
		}
	}
	
}
