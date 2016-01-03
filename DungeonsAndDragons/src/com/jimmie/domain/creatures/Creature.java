package com.jimmie.domain.creatures;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AlternativeMovementMode;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.TemporaryCombatAdvantage;
import com.jimmie.domain.TemporaryCondition;
import com.jimmie.domain.CombatAdvantageType;
import com.jimmie.domain.Condition;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
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
import com.jimmie.domain.TargetedTemporaryEffect;
import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
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
import com.jimmie.powers.DivineChallenge;
import com.jimmie.powers.Power;
import com.jimmie.util.Dice;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.Utils;

public abstract class Creature implements Serializable, TurnTaker, AttackTarget {
	protected List<TemporaryEffect> temporaryEffects;
	private String imagePath;
	private String bloodiedImagePath;
	protected Role role;
	protected boolean usedSecondWind;
	private Creature pursuer;
	private boolean hitByStirringShout;
	private boolean hitByTelekineticAnchor;
	private static final long serialVersionUID = 1L;
	private Position currentPosition;
	protected String name;
	protected String displayName;
	private boolean usedMinorAction;
	private boolean usedMoveAction;
	private boolean usedStandardAction;
	private MovementType movementType;
	protected List<Power> powers;
	protected Alignment alignment;
	protected List<String> languages;
	protected List<Skill> skills;
	protected Size size;
	protected List<Equipment> equipment;
	protected int stirringShoutCharismaModifier;
	protected int currentTurn;
	protected int initiativeRoll;
	protected int level;
	protected int currentHitPoints;
	protected int maxHitPoints;
	protected int reflex;
	protected int will;
	protected int actionPoints;
	protected int strength;
	protected int constitution;
	protected int dexterity;
	protected int intelligence;
	protected int wisdom;
	protected int charisma;
	protected int initiative;
	protected int baseSpeed;
	protected int temporaryHitPoints;
	protected int channelDivinityUses;
	protected List<Sense> senses;
	protected List<AlternativeMovementMode> alternativeMovementModes;
	protected List<Condition> currentConditions;
	protected List<Effect> currentEffects;
	private HashMap<DamageType, Integer> damageResistances;
	private HashMap<DamageType, Integer> damageVulnerabilities;
	protected Race race;
	protected DndClass dndClass;
	private boolean turnOver;
	private List<Mark> marks;
	protected Origin origin;
	Image image = null;
	Image scaledImage = null;
	Image bloodiedImage = null;
	Image scaledBloodiedImage = null;

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
		
		temporaryEffects = new ArrayList<TemporaryEffect>();
		
		stirringShoutCharismaModifier = 0;
		currentTurn = 0;
		initiativeRoll = 0;
		level = 0;
		currentHitPoints = 0;
		maxHitPoints = 0;
		reflex = 0;
		will = 0;
		actionPoints = 0;
		strength = 0;
		constitution = 0;
		dexterity = 0;
		intelligence = 0;
		wisdom = 0;
		charisma = 0;
		initiative = 0;
		baseSpeed = 0;
		temporaryHitPoints = 0;
		channelDivinityUses = 0;
	}
	
	public int getTemporaryHitPoints() {
		return temporaryHitPoints;
	}

	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}

	public void push(String direction) {
		/* Check for things like "Form of the Willow Sentinel. */
		if (DndCharacter.class.isAssignableFrom(this.getClass())) {
			DndClass dndClass = ((DndCharacter) this).getDndClass();
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
		if (DndCharacter.class.isAssignableFrom(this.getClass())) {
			DndClass dndClass = ((DndCharacter) this).getDndClass();
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
		if (DndCharacter.class.isAssignableFrom(this.getClass())) {
			DndClass dndClass = ((DndCharacter) this).getDndClass();
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

	public void mark(Creature owner, DurationType duration, MarkType markType, int startTurn) {
		Mark mark = new Mark();
		mark.setMarkType(markType);
		mark.setMarker(owner);
		mark.setDuration(duration);
		mark.setStartTurn(startTurn);
		mark.setMisdirectedMarker(null);
		
		addMark(mark);
	}
	
	private void addMark(Mark mark) {
		if (marks == null) {
			marks = new ArrayList<Mark>();
		}
		marks.add(mark);
	}

	public void mark(Creature owner, DurationType duration, MarkType markType, int startTurn, Creature misdirectedMarker) {
		Mark mark = new Mark();
		mark.setMarkType(markType);
		mark.setMarker(owner);
		mark.setDuration(duration);
		mark.setStartTurn(startTurn);
		mark.setMisdirectedMarker(misdirectedMarker);
		addMark(mark);
	}
	
	public void setTemporaryCombatAdvantage(Creature source, DurationType durationType, CombatAdvantageType combatAdvantageType, int startTurn) {
		// I believe multiple people could have combat advantage over a creature at the same time.  So it's ok if there
		// are multiple combat advantages in the list.
		
		TemporaryCombatAdvantage temporaryCombatAdvantage = new TemporaryCombatAdvantage();
		temporaryCombatAdvantage.setTypeOfCombatAdvantage(combatAdvantageType);
		temporaryCombatAdvantage.setSource(source);
		temporaryCombatAdvantage.setDuration(durationType);
		temporaryCombatAdvantage.setStartTurn(startTurn);
		temporaryEffects.add(temporaryCombatAdvantage);
	}
	
	public void setTemporaryCondition(Creature source, DurationType durationType, CreatureConditionType conditionType, int startTurn) {
		// TODO: Probably want to write smart code that will check the condition type to determine if multiples of that
		// condition can apply or not, and check the list to see if multiples do apply.
		TemporaryCondition temporaryCondition = new TemporaryCondition();
		temporaryCondition.setConditionType(conditionType);
		temporaryCondition.setSource(source);
		temporaryCondition.setDuration(durationType);
		temporaryCondition.setStartTurn(startTurn);
		temporaryEffects.add(temporaryCondition);
	}

	public void setTemporaryInvisibility(Creature source, DurationType durationType, List<Creature> target) {
		TemporaryInvisibility temporaryInvisibility = new TemporaryInvisibility();
		temporaryInvisibility.setSource(source);
		temporaryInvisibility.setDuration(durationType);
		temporaryInvisibility.setStartTurn(source.getCurrentTurn());
		/* If target is null, then you became invisible to everyone. */
		if (target != null) {
			temporaryInvisibility.setTargets(target);
		}
		temporaryEffects.add(temporaryInvisibility);
	}

	public int getOtherAttackModifier(List<AttackTarget> targets) {
		int total = 0;

		/* Check for a temporary attack roll modifier. */
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
	
			if (tempEffect.getEffectType() == TemporaryEffectType.ATTACK_ROLL_MODIFIER) {
				boolean appliesToTarget = false;
				// See if it's a targeted effect (i.e. only applies to certain targets).
				if (TargetedTemporaryEffect.class.isAssignableFrom(tempEffect.getClass())) {
					TargetedTemporaryEffect targetedTempEffect = (TargetedTemporaryEffect) tempEffect;
					if ((targetedTempEffect.getTarget() == null) || (!targets.contains(targetedTempEffect.getTarget()))) {
						appliesToTarget = false;
					} else {
						appliesToTarget = true;
					}
				} else {
					appliesToTarget = true;
				}
				if (!appliesToTarget) {
					continue;
				}
				if (tempEffect.stillApplies()) {
					Utils.print(name + " currently has a temporary attack roll modifier of " + tempEffect.getModifier());
					total = total + tempEffect.getModifier();
					/* If it should be removed now, delete the modifier now. */
					if (tempEffect.shouldBeRemoved()) {
						Utils.print("Attack modifier will no longer apply.");
						it.remove();
					}
				} else {
					Utils.print("Attack modifier no longer applies.  Removing.");
					it.remove();
				}
			}			
		}


		/* Do I have combat advantage against the target? */
		/* I think I can only check this if it is a single target. */
		if (targets.size() == 1) {
			if (Utils.hasCombatAdvantage(this, (Creature) targets.get(0))) {
				Utils.print(getName() + " has combat advantage over " + targets.get(0).getName() + " and recieves a +2 bonus.");
				total = total + 2;
			}
		}

		return total;
	}


	public boolean isMarked() {
		if (marks != null) {
			for (Iterator<Mark> iterator = marks.iterator(); iterator.hasNext();) {
				Mark mark = iterator.next();
				if (mark.stillApplies()) {
					return true;
				} else {
					Utils.print(mark.getMarkType() + " does not apply anymore.  Removing.");
					iterator.remove();
				}
			}
		}
		return false;
	}

	public void shift(int distance, boolean free) {
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
				moveCreature(direction, MovementType.SHIFTING);
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

	public void knockProne() {
		currentConditions.add(new Prone());
	}

	public Image getImage() {
		if (image == null) {
			try {
				image = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}

	public Image getBloodiedImage() {
		if (bloodiedImage == null) {
			try {
				bloodiedImage = ImageIO.read(new File(bloodiedImagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bloodiedImage;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void setBloodiedImagePath(String bloodiedImagePath) {
		this.bloodiedImagePath = bloodiedImagePath;
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

	public void setTemporaryEffect(int modifier, int modifierStartTurn,
			DurationType duration, Creature source, TemporaryEffectType effectType, TemporaryEffectReason reason) {
		TemporaryEffect temporaryEffect = new TemporaryEffect();
		temporaryEffect.setModifier(modifier);
		temporaryEffect.setStartTurn(modifierStartTurn);
		temporaryEffect.setDuration(duration);
		temporaryEffect.setSource(source);
		temporaryEffect.setEffectType(effectType);
		temporaryEffect.setReason(reason);
		temporaryEffects.add(temporaryEffect);
	}

	public void setTargetedTemporaryEffect(int modifier, int modifierStartTurn,
			DurationType duration, Creature source, TemporaryEffectType effectType, TemporaryEffectReason reason, Creature target) {
		TargetedTemporaryEffect temporaryEffect = new TargetedTemporaryEffect();
		temporaryEffect.setModifier(modifier);
		temporaryEffect.setStartTurn(modifierStartTurn);
		temporaryEffect.setDuration(duration);
		temporaryEffect.setSource(source);
		temporaryEffect.setEffectType(effectType);
		temporaryEffect.setTarget(target);
		temporaryEffect.setReason(reason);
		temporaryEffects.add(temporaryEffect);
	}

	public void setTemporaryAidAnotherBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source, AttackTarget target, int bonusType) {
		TemporaryAidAnotherBonus temporaryAidAnotherBonus = new TemporaryAidAnotherBonus();
		temporaryAidAnotherBonus.setModifier(bonus);
		temporaryAidAnotherBonus.setStartTurn(bonusStartTurn);
		temporaryAidAnotherBonus.setDuration(duration);
		temporaryAidAnotherBonus.setSource(source);
		temporaryAidAnotherBonus.setTarget(target);
		temporaryAidAnotherBonus.setType(bonusType);
		temporaryEffects.add(temporaryAidAnotherBonus);
	}

	public void setTemporaryDamageResistance(int bonus, int bonusStartTurn,
			DurationType duration, Creature source, DamageType damageType) {
		TemporaryDamageResistance temporaryDamageResistance = new TemporaryDamageResistance();
		temporaryDamageResistance.setModifier(bonus);
		temporaryDamageResistance.setStartTurn(bonusStartTurn);
		temporaryDamageResistance.setDuration(duration);
		temporaryDamageResistance.setSource(source);
		temporaryDamageResistance.setDamageType(damageType);
		temporaryEffects.add(temporaryDamageResistance);
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public void useAction(ActionType actionType) {
		// A dazed creature can only take one action while dazed.  So mark them all as used.
		if (isDazed()) {
			Utils.print(getName() + " can only take one action per turn because they are dazed.");
			usedMinorAction = true;
			usedMoveAction = true;
			usedStandardAction = true;
		}
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

	public void setChannelDivinityUses(int channelDivinityUses) {
		this.channelDivinityUses = channelDivinityUses;
	}

	public int getChannelDivinityUses() {
		return channelDivinityUses;
	}

	private void addSkill(Skill skill) {
		if (skills == null) {
			skills = new ArrayList<Skill>();
		}
		skills.add(skill);
	}
	
	public boolean isHitByStirringShout() {
		return hitByStirringShout;
	}

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

	public void initializeForEncounter() {
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int roll = d.roll(DiceRollType.INITIATIVE_ROLL);
		initiativeRoll = roll + initiative;
		setCurrentTurn(0);
		channelDivinityUses = 0;
		
		// Initialize powers.
		for (Power power : powers) {
			power.initializeForEncounter();
		}
	}

	public void initializeForNewDay() {		
		// TODO: Remove any conditions/effects that last until the "end of next extended rest".
		actionPoints = 0;
	}

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

	public void useMoveAction() {
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
				Position newPosition = Encounter.getEncounter().getPositionRelativeTo(getCurrentPosition(), direction);
				/* Do they have to perform a check to enter? */
				if (Encounter.getEncounter().requiresCheckToEnter(newPosition, getCurrentPosition())) {
					int costForEntering = Encounter.getEncounter().getCostForEnteringSquare();
					if (distanceLeft < costForEntering) {
						Utils.print("Sorry.  Don't have enough movement left to enter that square.");
						continue;
					}
					SkillCheck skillCheck = Encounter.getEncounter().getGenericSkillCheck();
					if (performSkillCheck(skillCheck)) {
						Utils.print("You successfully passed the skill check.");
						/* An extra 1 will be subtracted later. */
						distanceLeft = distanceLeft - (costForEntering - 1);
					} else {
						Utils.print("You failed the skill check.  Staying where you are.");
						continue;
					}
				}/* Are they about to move into difficult terrain? */
				else if (isDifficultTerrain(Encounter.getEncounter().getPositionRelativeTo(getCurrentPosition(), direction), movementType)) {
					if (distanceLeft < 2) {
						Utils.print("Sorry.  Can't enter that square.  It's difficult terrain.");
						continue;
					} else {
						Utils.print("Entering difficult terrain.  Costs two movements.");
						distanceLeft--;
					}
				}

				moveCreature(direction, movementType);
				distanceLeft--;

			}
		}

	}

	private boolean isDifficultTerrain(Position position, MovementType movementType) {
		return Encounter.getEncounter().isDifficultTerrain(position);
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

	private boolean performSkillCheck(SkillCheck skillCheck) {
		int roll = skillCheckRoll(skillCheck);

		Utils.print("Your target DC was + " + skillCheck.getDifficultyClass());

		if (roll >= skillCheck.getDifficultyClass()) {
			Utils.print("You successfully completed this skill check.");
			return true;
		} else {
			Utils.print("FAIL!");
			return false;
		}

	}

	private int skillCheckRoll(SkillCheck skillCheck) {
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.roll(DiceRollType.SKILL_CHECK_ROLL);
		int roll = diceRoll + getSkillModifier(skillCheck.getSkillType());
		Utils.print("Your skill check roll with modifiers is " + roll);
		return roll;
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
				if (DndCharacter.class.isAssignableFrom(this.getClass())) {
					Armor armor = ((DndCharacter) this).getReadiedArmor();
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

	public void performOpportunityAttack(Creature target) {
		if (isDazed()) {
			Utils.print("Sorry. Can't take an opportunity action because " + getName() + " is dazed.");
			return;
		}
		
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
		chosenPower.process(this);
	}


	public void moveCreature(String direction, MovementType movementType) {
		/* See if I was hit by Telekinetic Anchor.  If so, I take 5 force damage, but only once. */
		if (hitByTelekineticAnchor) {
			Utils.print(getName() + " was previously hit by telekinetic anchor and takes 5 force damage now.");
			// TODO, shouldn't pass "this" in as the hurter.
			hurt(5, DamageType.FORCE, true, this);
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
		
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if ((tempEffect.getDuration() == DurationType.START_OF_NEXT_TURN) && (tempEffect.getStartTurn() < currentTurn)) {
				Utils.print("It's the start of a new turn.  Removing " + tempEffect.getEffectType() + " temporary effect.");
				it.remove();
			}
		}
		
		// Some powers need initialized for the start of turn.
		for (Power power : getPowers()) {
			power.initializeForStartOfTurn();
		}
	}

	public void endOfTurn() {
		turnOver = true;
		/* Check for bond of pursuit. */
		if (pursuer != null) {
			/* If I didn't end my turn adjacent to my pursuer, they get to shift. */
			if (!(this.isAdjacentTo(pursuer))) {
				Utils.print("Pursuer (" + pursuer.getName() + ") gets to shift " + (pursuer.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)+1) + " squares.");
				Utils.print("Please note: You must end closer to " + this.getName() + ".  This is not enforced in the code though");
				pursuer.shift(pursuer.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)+1, true);
			}
			/* At the end of turn, the bond of pursuit is over. */
			pursuer = null;
		}
		hitByTelekineticAnchor = false;
		
		// Divine Challenge
		for (Power power : powers) {
			if (DivineChallenge.class.isAssignableFrom(power.getClass())) {
				DivineChallenge divineChallenge = (DivineChallenge) power; 
				Creature markedCreature = divineChallenge.getMarkedCreature();
				if (markedCreature != null) {
					// TODO: Implement the check to see if we attacked this creature during the turn.  For now,
					// just do "adjacent check".
					// TODO: Also need to implement the "can't use divine challenge on your next turn".
					List<Creature> adjCreatures = Encounter.getEncounter().getAllAdjacentCreatures(this);
					if (!adjCreatures.contains(markedCreature)) {
						Utils.print(getName() + " did not engage " + markedCreature.getName() + ". Removing the Divine Challenge mark.");
						divineChallenge.setMarkedCreature(null);
						for (Iterator<Mark> it = markedCreature.getMarks().iterator(); it.hasNext();) {
							Mark mark = it.next();
							if (mark.getMarkType() == MarkType.DIVINE_CHALLENGE) {
								it.remove();
							}
						}
					}
				}
			}
		}
		
		// Saving throws
		performSavingThrows(0, 0);
	}

	// quantity is the number of saving throws you can do now.  A 0 means you perform a saving throw against all your "save ends" effects.
	public void performSavingThrows(int quantity, int modifier) {
		int count = 0;
		for (Iterator<TemporaryEffect> it = getTemporaryEffects().iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (effect.getDuration() == DurationType.SAVE_ENDS) {
				if (quantity == 0) {
					Utils.print(getName() + " gets to make a saving throw against " + effect.getEffectType());
					int roll = rollForSavingThrow(modifier);
					if (roll >= 10) {
						Utils.print("You are no longer affected.");
						it.remove();
					}
				} else if (count < quantity) {
					Utils.print(getName() + " has " + (quantity - count) + " saving throws left.  Would you like to use one against " + effect.getEffectType() + "?");
					Utils.print("Your choice (Y or N):");
					if (Utils.getYesOrNoInput().equalsIgnoreCase("Y")) {
						Dice dice = new Dice(DiceType.TWENTY_SIDED);
						int roll = dice.roll(DiceRollType.SAVING_THROW_ROLL);
						// TODO: Add any modifiers (once you run into a power or something that adds a modifier).
						if (roll + modifier >= 10) {
							Utils.print("You are no longer affected.");
							it.remove();
							count++;
						}
					}
					
				}
			}
		}
	}

	private int rollForSavingThrow(int modifier) {
		Dice dice = new Dice(DiceType.TWENTY_SIDED);
		int roll = dice.roll(DiceRollType.SAVING_THROW_ROLL);
		int total = roll+modifier;
		Utils.print("Your saving throw total is + " + total);
		return total;
	}

	public List<AlternativeMovementMode> getAlternativeMovementModes() {
		return alternativeMovementModes;
	}

	public void setAlternativeMovementModes(
			List<AlternativeMovementMode> alternativeMovementModes) {
		this.alternativeMovementModes = alternativeMovementModes;
	}


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
 	public int getFortitude(Creature attacker) {

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

		return getBaseFortitude() + bonus;
	}
*/
	public int getReflex(Creature attacker) {
		int modifier = 0;
		/* See if there is a temporary modifier to the reflex. */
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (tempEffect.getEffectType() == TemporaryEffectType.REFLEX_MODIFIER) {
				if (tempEffect.stillApplies()) {
					Utils.print(name + " is supposed to get a modifier to reflex until " + tempEffect.getDuration());
					modifier = modifier + tempEffect.getModifier();
					Utils.print("Modifier still applies.");
					/* If it should be removed now, delete the modifier now. */
					if (tempEffect.shouldBeRemoved()) {
						Utils.print("Modifier will no longer apply.");
						it.remove();
					}
				} else {
					/* modifier is over.  Reset the modifier. */
					it.remove();
					Utils.print("Modifier no longer applies.  Resetting modifier.");
				}
			}
		}
		return reflex + modifier;
	}


	public void setReflex(int reflex) {
		this.reflex = reflex;
	}


	public int getWill(Creature attacker) {
		int currentWill = will;

		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (tempEffect.getEffectType() == TemporaryEffectType.WILL_MODIFIER) {
				if (tempEffect.stillApplies()) {
					Utils.print(name + " is supposed to get a modifier to will until " + tempEffect.getDuration());
					currentWill = currentWill + tempEffect.getModifier();
					Utils.print("Modifier still applies.");
					/* If it should be removed now, delete the modifier now. */
					if (tempEffect.shouldBeRemoved()) {
						Utils.print("Modifier will no longer apply.");
						it.remove();
					}
				} else {
					/* modifier is over.  Reset the modifier. */
					it.remove();
					Utils.print("Modifier no longer applies.  Resetting modifier.");
				}
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
		// Check for slowness
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(tempEffect.getClass())) {
				TemporaryCondition temporaryCondition = (TemporaryCondition) tempEffect;

				if (temporaryCondition.getConditionType() == CreatureConditionType.SLOWED) {
					if (temporaryCondition.stillApplies()) {
						Utils.print(getName() + " is slowed.  Can only move 2 spaces.");
						/* If it should be removed now, delete the modifier now. */
						if (temporaryCondition.shouldBeRemoved()) {
							Utils.print("Slowed condition will no longer apply.");
							it.remove();
						}
						return 2;
					} else {
						Utils.print("The SLOWED condition no longer applies.  Removing.");
						it.remove();
					}
				}
			}
		}

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

	public void hurt(int damage, DamageType damageType, boolean hit, Object hurter) {
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
				currentHitPoints = 0;
				Utils.print(name + " is dead.");
				Encounter.getEncounter().removeCreature(this);
			} else {
				/* For now just do the same for player characters. */
				Utils.print(name + " is dead.");
				Encounter.getEncounter().removeCreature(this);
			}
		}
	}

	private int getDamageResistance(DamageType damageType) {
		int resistance = 0;

		if (damageResistances.containsKey(damageType)) {
			resistance = damageResistances.get(damageType);
		}
		
		// Check for temporary damage resistance.
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (TemporaryDamageResistance.class.isAssignableFrom(tempEffect.getClass())) {
				TemporaryDamageResistance temporaryDamageResistance = (TemporaryDamageResistance) tempEffect;
				if (temporaryDamageResistance.stillApplies()) {
					if ((temporaryDamageResistance.getDamageType() == DamageType.ALL) || (temporaryDamageResistance.getDamageType() == damageType)) {
						resistance += temporaryDamageResistance.getModifier();
						/* If it should be removed now, delete the modifier now. */
						if (tempEffect.shouldBeRemoved()) {
							Utils.print("Temporary damage resistance will no longer apply.");
							it.remove();
						}
					}
				} else {
					Utils.print("Temporary damage resistance no longer applies.  Removing.");
					it.remove();
				}
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

	public List<TemporaryEffect> getTemporaryEffects() {
		return temporaryEffects;
	}

	public void setTemporaryEffects(List<TemporaryEffect> temporaryEffects) {
		this.temporaryEffects = temporaryEffects;
	}

	public boolean isUnconscious() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.UNCONSCIOUS) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still unconscious.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be unconscious after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer unconscious.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isSurprised() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.SURPRISED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still surprised.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be surprised after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer surprised.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isDeafened() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.DEAFENED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still deafened.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be deafened after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer deafened.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}


	public boolean isPetrified() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.PETRIFIED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is petrified still.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be petrified after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer petrified.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}


	public boolean isDying() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.DYING) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is dying still.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be dying after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer dying.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}


	public boolean isDominated() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.DOMINATED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is dominated still.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be dominated after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer dominated.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}


	public boolean isDazed() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.DAZED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is dazed still.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be dazed after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer dazed.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isStunned() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.STUNNED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is stunned still.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be stunned after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer stunned.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	/* There could be several reasons you are invisible.  For now, just checking for temporary invisibility, though. */
	public boolean isInvisibleTo(TurnTaker attacker) {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (TemporaryInvisibility.class.isAssignableFrom(tempEffect.getClass())) {
				TemporaryInvisibility temporaryInvisibility = (TemporaryInvisibility) tempEffect;

				if (temporaryInvisibility.stillApplies()) {
					/* Does it apply to this creature? */
					if (temporaryInvisibility.getTargets() == null) {
						/* If it should be removed now, delete the modifier now. */
						if (temporaryInvisibility.shouldBeRemoved()) {
							it.remove();
						}
						return true;
					} else {
						if (temporaryInvisibility.getTargets().contains(attacker)) {
							/* If it should be removed now, delete the modifier now. */
							if (temporaryInvisibility.shouldBeRemoved()) {
								it.remove();
							}
							return true;
						}
					}
				} else {
					it.remove();
					Utils.print("Temporary invisibility no longer applies.  Resetting.");
				}
			}
		}
		return false;
	}

	@Override
	public boolean isBlinded() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.BLINDED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is blinded still.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be blinded after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer blinded.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isHelpless() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.HELPLESS) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still helpless.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be helpless after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer helpless.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isImmobilized() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.IMMOBILIZED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still immobilized.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be immobilized after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer immobilized.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isProne() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.PRONE) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still prone.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be prone after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer prone.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isRestrained() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.RESTRAINED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still restrained.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be restrained after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer restrained.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isSlowed() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.SLOWED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still slowed.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be slowed after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer slowed.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public boolean isWeakened() {
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect effect = it.next();
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.WEAKENED) {
					if (condition.stillApplies()) {
						Utils.print(getName() + " is still weakened.");
						/* If it should be removed now, delete the modifier now. */
						if (condition.shouldBeRemoved()) {
							Utils.print("But will not be weakened after this.");
							it.remove();
						}
						return true;
					} else {
						Utils.print(getName() + " is no longer weakened.  Removing.");
						it.remove();
					}
				}
			}
		}
		return false;
	}

	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public int attackRollWithPowerModifier(AbilityType abilityType, AccessoryType accessoryType, List<AttackTarget> targets,
			int powerModifiers) {
		Utils.print("In a bit, you will be told your total dice roll with modifiers.  But please keep in mind that it doesn't include the " + powerModifiers + " of power modifiers.");
		int totalRoll = attackRoll(abilityType, accessoryType, targets) + powerModifiers;
		Utils.print("Your real total was " + totalRoll);
		return totalRoll;
	}
	
	public int attackRoll(AbilityType abilityType, AccessoryType accessoryType, List<AttackTarget> targets) {
		int diceRoll = rawAttackRoll() + getAbilityModifierPlusHalfLevel(abilityType) + getOtherAttackModifier(targets);
		
		if (accessoryType == AccessoryType.IMPLEMENT) {
			diceRoll += getImplementAttackBonus();
		} else if (accessoryType == AccessoryType.WEAPON) {
			diceRoll += this.getWeaponProficiencyBonus();
		}
		
		Utils.print("With all the modifiers, your roll becomes " + diceRoll);
		
		return diceRoll;
	}

	private int rawAttackRoll() {
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		return d.roll(DiceRollType.ATTACK_ROLL);
	}

	public int getImplementAttackBonus() {
		Utils.print("In Creature.getImplementAttackBonus.");
		return 0;
	}

	public int getImplementDamageBonus() {
		Utils.print("In Creature.getImplementDamageBonus.");
		return 0;
	}

	@Override
	public void miss(Creature misser) {
	}

	public void slideTargets(List<AttackTarget> targets, int distance) {

		if (targets != null) {
			for (AttackTarget target : targets) {
				// No use sliding a dead target.
				if (target.getCurrentHitPoints() > 0) {
					for (int i = 0; i <= distance; i++) {
						Utils.print("What direction do you want to slide " + target.getName() + "? (N, E, S, W, NE, NW, SE, SW, STOP)?");
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
				}
			}
		}
	}

	public void pushTargets(List<Creature> pushTargets, int distance) {
		if (pushTargets != null) {
			for (Creature target : pushTargets) {
				for (int i = 0; i < distance; i++) {
					String pushDirection = Encounter.getEncounter().getPushDirection(getCurrentPosition(), target.getCurrentPosition());
					target.push(pushDirection);
				}
			}
		}
		
	}

	public Image getScaledImage(double shrinkPercent) {
		if (scaledImage == null) {
			if ((getImage().getWidth(null) == 200) || (getImage().getWidth(null) == 400) || (getImage().getWidth(null) == 600)) {
				// Go ahead and do transparency here, too.  But not based on white.  Base it on being outside the circle or not.
				Image image1 = getImage();
				Image newImage = makeTransparent((BufferedImage)image1);
				
				scaledImage = newImage.getScaledInstance((int) (newImage.getWidth(null)*shrinkPercent), (int)(newImage.getHeight(null)*shrinkPercent), Image.SCALE_SMOOTH);
			} else {
				scaledImage = getImage();
			}
		}
		return scaledImage;
	}

	private Image makeTransparent(BufferedImage image)
	{
		ImageFilter filter = new RGBImageFilter()
		{
			public final int filterRGB(int x, int y, int rgb)
			{
				if (!pixelInsideCircle(x,y,image.getWidth())) {
					return 0;
				} else {
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
	
	private boolean pixelInsideCircle(int x, int y, int width) {
		// For now, start with a width 4 pixels outside the circle.
		int r = (width/2) + 1;
		int h = width/2;
		int b = -2*h;
		int c = (x*x)-(2*h*x)+(2*h*h)-(r*r);
		
		// Use the quadratic formula to figure out the y edge values
		int yEdgeUp = (-b+(int) Math.sqrt((b*b)-(4*c)))/2;
		int yEdgeDown = (-b-(int) Math.sqrt((b*b)-(4*c)))/2;
		if ((y>=yEdgeUp) || (y<=yEdgeDown)) {
			return false;
		} else {
			return true;
		}
		
	}	
	
	public Image getScaledBloodiedImage(double shrinkPercent) {
		if (scaledBloodiedImage == null) {
			if ((getBloodiedImage().getWidth(null) == 200) || (getBloodiedImage().getWidth(null) == 400) || (getBloodiedImage().getWidth(null) == 600)) {
				scaledBloodiedImage = getBloodiedImage().getScaledInstance((int) (getBloodiedImage().getWidth(null)*shrinkPercent), (int)(getBloodiedImage().getHeight(null)*shrinkPercent), Image.SCALE_SMOOTH);
			} else {
				ImageFilter filter = new RGBImageFilter()
				{
					public final int filterRGB(int x, int y, int rgb)
					{
						/* Still have to filter out the white transparency. */
						int red = (rgb >> 16) & 0xFF;
						int green = (rgb >> 8) & 0xFF;
						int blue = rgb & 0xFF;
						if ((red > 200) && (green > 200) && (blue > 200)) { 
							return 0;
						} else {
							return (rgb | 0x00880000);
						}
					}
				};

				ImageProducer ip = new FilteredImageSource(getBloodiedImage().getSource(), filter);
				scaledBloodiedImage = Toolkit.getDefaultToolkit().createImage(ip);
			}
		}
		return scaledBloodiedImage;
		
	}
}
