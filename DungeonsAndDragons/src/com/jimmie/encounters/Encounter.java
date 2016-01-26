package com.jimmie.encounters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.Position;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.Zone;
import com.jimmie.domain.ZoneShape;
import com.jimmie.domain.ZoneType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.domain.items.weapons.WeaponProperty;
import com.jimmie.domain.map.Map;
import com.jimmie.powers.Power;
import com.jimmie.util.Dice;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.TurnMaster;
import com.jimmie.util.Utils;

public abstract class Encounter {
	private static Encounter encounter;
	private static boolean debug;
	private static boolean showCoordinateSystem;
	protected Map map;
	private static boolean monstersVisible = true;
	private static boolean monstersActive = true;
	private static boolean charactersVisible = true;
	private static boolean charactersActive = true;
	@SuppressWarnings("rawtypes")
	private HashMap<Class, Integer> monsterInitiatives = new HashMap<Class, Integer>();

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Creature> getCreatures() {
		List<Creature> creatures = new ArrayList<Creature>();
		creatures.addAll(characters);
		creatures.addAll(monsters);
		return creatures;
	}

	public List<DndCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(List<DndCharacter> characters) {
		this.characters = characters;
	}

	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(List<Monster> monsters) {
		this.monsters = monsters;
	}

	protected List<DndCharacter> characters; /*
	 * This is the list of player characters
	 * and NPC's. ie the good guys.
	 */
	protected List<Monster> monsters;
	private TurnTaker currentParticipant;
	private List<Zone> zones;

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public void runEncounter() {
		Dice.setRollType(Dice.USER_ENTERED);
		setupEncounter();
		do {
			currentParticipant = TurnMaster.getNextParticipant();
			Utils.print("Up next: " + currentParticipant.getName());

			runATurn(currentParticipant);

			Utils.print("Save Encounter?");
			String choice = Utils.getYesOrNoInput();
			if ("Y".equalsIgnoreCase(choice)) {
				for (DndCharacter c : characters) {
					Utils.saveCharacter(c);
				}
			}
		} while (true);//!isEncounterOver());
	}

	private void runATurn(TurnTaker participant) {

		participant.startOfTurn();
		boolean skipTurn = false;

		do {
			HashMap<Integer, Power> validActions = new HashMap<Integer, Power>();
			int index = 0;
			int moveChoice = 0;
			int skipChoice = 0;

			if (Creature.class.isAssignableFrom(participant.getClass())) {
				Utils.print("What does " + participant.getName() + " want to do? ");
				Utils.print("Possible choices are:");
				Creature c = (Creature) participant;
				// Move is special
				if (participant.canTakeMoveAction()) {
					index++;
					moveChoice = index;
					Utils.print(index + ". Move (Move action)");
				}
				if (participant.getPowers() != null) {
					for (Power power : participant.getPowers()) {
						if ((power.getActionType() == ActionType.FREE) && (power.meetsPrerequisitesToChoosePower(c)) 
								&& (power.meetsRequirementsToUsePower(c))) {
							index++;
							validActions.put(index, power);
							Utils.print(index + ". " + power.getName() + " (Free - " + power.getPowerUsage() + ")");
						} else if ((power.getActionType() == ActionType.MINOR) && (participant.canTakeMinorAction()) &&
								(power.meetsPrerequisitesToChoosePower(c)) && (power.meetsRequirementsToUsePower(c))) {
							index++;
							validActions.put(index, power);
							Utils.print(index + ". " + power.getName() + " (Minor action - " + power.getPowerUsage() + ")");
						} else if ((power.getActionType() == ActionType.MOVE) && (participant.canTakeMoveAction()) &&
								(power.meetsPrerequisitesToChoosePower(c)) && (power.meetsRequirementsToUsePower(c))) {
							index++;
							validActions.put(index, power);
							Utils.print(index + ". " + power.getName() + " (Move action - " + power.getPowerUsage() + ")");
						} else if ((power.getActionType() == ActionType.STANDARD) && (participant.canTakeStandardAction()) &&
								(power.meetsPrerequisitesToChoosePower(c)) && (power.meetsRequirementsToUsePower(c))) {
							index++;
							validActions.put(index, power);
							Utils.print(index + ". " + power.getName() + " (Standard action - " + power.getPowerUsage() + ")");
						}
					}
				}

				index++;
				Utils.print(index + ". Skip turn");
				skipChoice = index;

				Utils.print("Your choice:");
				int choice = Utils.getValidIntInputInRange(1, index);
				if (choice == moveChoice) {
					participant.useMoveAction();
				} else if (choice == skipChoice) {
					skipTurn = true;					
				} else {
					Power chosenPower = validActions.get(choice);
					if (chosenPower.process(c) == true) {
						// Mark the character has having taken the appropriate action.
						c.useAction(chosenPower.getActionType());
					}
				}
			}

		} while (!(skipTurn)
				|| (!isTurnOver(participant)));

		participant.endOfTurn();
	}

	/*	private void displayCharacterLocations() {
		Utils.print("Current Locations:");
		for (Character character : characters) {
			Utils.print(character.getName() + " is at ("
					+ character.getCurrentPosition().getX() + ", "
					+ character.getCurrentPosition().getY() + ")");
		}
		for (Creature monster : monsters) {
			Utils.print(monster.getName() + " is at ("
					+ monster.getCurrentPosition().getX() + ", "
					+ monster.getCurrentPosition().getY() + ")");
		}
	}
	 */
	public boolean isTurnOver(TurnTaker participant) {
		/*
		 * For now, just ask. Later, I can change this to have logic to see if
		 * the participant has actions left, or is dead, etc.
		 */
		if (participant.canTakeMinorAction() || participant.canTakeMoveAction()
				|| participant.canTakeStandardAction()) {
			Utils.print("Continue Turn?");
			String cont = Utils.getYesOrNoInput();
			if (cont.equals("Y")) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public boolean isEncounterOver() {
		/*
		 * For now, just ask. Later, I can change this to have logic to see if
		 * there are still living enemies, or only ask when I reached the last
		 * person in the encounter or something.
		 */
		Utils.print("Continue Encounter?");
		String cont = Utils.getYesOrNoInput();
		if (cont.equals("Y")) {
			return false;
		} else {
			return true;
		}

	}

	private void setupEncounter() {
		/*
		 * Do setup stuff, like setting up the map, adding all the
		 * players/monsters to the map, etc.
		 */

		// Initialize everyone for the encounter before adding them to the turn master.
		for (DndCharacter c : characters) {
			c.initializeForEncounter();
		}
		
		// See if any of the characters have the Primal Instinct feat
		for (DndCharacter c : characters) {
			if (PlayerCharacter.class.isAssignableFrom(c.getClass())) {
				if (((PlayerCharacter) c).hasFeat(FeatType.PRIMAL_INSTINCT)) {
					Utils.print("Because " + c.getName() + " has the Primal Instinct feat, one of the characters (within 5 squares) can reroll initiative.");
					Utils.print("Would you like to use this feat?");
					Utils.print("Your choice (Y or N)?");
					if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
						int i = 0;
						
						HashMap<Integer, DndCharacter> choices = new HashMap<Integer, DndCharacter>();
						for (DndCharacter ally : characters) {
							if ((ally != c) && (ally.getCurrentPosition().isWithinReachOf(c.getCurrentPosition(), 5))) {
								i++;
								choices.put(i,ally);
								Utils.print(i + ". " + ally.getName());
							}
						}
						Utils.print("Your choice:");
						int choice = Utils.getValidIntInputInRange(1, i);
						DndCharacter ally = choices.get(choice);
						if (ally != null) {
							ally.rollForInitiative();
						}
					}
				}
			}
		}
		
		for (Monster m : monsters) {
			m.initializeForEncounter();
			if (!monsterInitiatives.containsKey(m.getClass())) {
				monsterInitiatives.put(m.getClass(), m.getInitiativeRoll());
			}
		}
		
		
		/* Add all the characters to the turnmaster. */
		for (DndCharacter c : characters) {
			TurnMaster.addParticipant(c);
		}
		
		// Call a method that should be overridden in the sub class to do any necessary fixing of initiatives before adding them to the turn master.
		makeEncounterInitiativeChanges();

		/* Now the monsters. */
		for (Monster m : monsters) {
			TurnMaster.addParticipant(m);
		}
		/* TODO: Add other things, like traps/hazards that take turns. */

		// Individual encounter setup.
		setup();

	}

	protected abstract void makeEncounterInitiativeChanges();
	
	public List<AttackTarget> chooseMeleeTarget(TurnTaker attacker, Weapon weapon) {
		HashMap<Integer, AttackTarget> validChoices = new HashMap<Integer, AttackTarget>();
		int reach = 1;
		if (weapon != null) {
			if (weapon.getWeaponProperties().contains(WeaponProperty.REACH)) {
				reach = 2;
			}
		}

		Utils.print("Who do you want to attack?");
		int index = 0;

		if (!characters.contains(attacker)) {
			for (DndCharacter c : characters) {
				/* Is this character within reach? */
				if (attacker.getCurrentPosition().isWithinReachOf(
						c.getCurrentPosition(), reach)) {
					/* Check for invisibility. */
					if (!c.isInvisibleTo(attacker)) {
						index++;
						validChoices.put(index, c);
						Utils.print(index + ". " + c.getName());
					}
				}
			}
		}

		if (!monsters.contains(attacker)) {
			for (Monster m : monsters) {
				if (attacker.getCurrentPosition().isWithinReachOf(
						m.getCurrentPosition(), reach)) {
					index++;
					validChoices.put(index, m);
					Utils.print(index + ". " + m.getName());
				}
			}
		}

		/*
		 * TODO: Later, when I have traps and other attackable things I can add
		 * those here.
		 */
		if (index < 1) {
			Utils.print("OOPS!  No one to choose from");
			return null;
		}

		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, index);

		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(validChoices.get(choice));
		return targets;
	}

	public List<AttackTarget> chooseMeleeTargetInRange(TurnTaker attacker, int range) {
		HashMap<Integer, AttackTarget> validChoices = new HashMap<Integer, AttackTarget>();

		Utils.print("Who do you want to attack?");
		int index = 0;

		if (!characters.contains(attacker)) {
			for (DndCharacter c : characters) {
				/* Is this character within reach? */
				if (attacker.getCurrentPosition().isWithinReachOf(
						c.getCurrentPosition(), range)) {
					/* Check for invisibility. */
					if (!c.isInvisibleTo(attacker)) {
						index++;
						validChoices.put(index, c);
						Utils.print(index + ". " + c.getName());
					}
				}
			}
		}

		if (!monsters.contains(attacker)) {
			for (Monster m : monsters) {
				if (attacker.getCurrentPosition().isWithinReachOf(
						m.getCurrentPosition(), range)) {
					index++;
					validChoices.put(index, m);
					Utils.print(index + ". " + m.getName());
				}
			}
		}

		/*
		 * TODO: Later, when I have traps and other attackable things I can add
		 * those here.
		 */
		if (index < 1) {
			Utils.print("OOPS!  No one to choose from");
			return null;
		}

		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, index);

		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(validChoices.get(choice));
		return targets;
	}

	public String getPushDirection(Position pusher, Position pushee) {
		if (pusher.isNorthOf(pushee)) {
			return "S";
		}
		if (pusher.isEastOf(pushee)) {
			return "W";
		}
		if (pusher.isSouthOf(pushee)) {
			return "N";
		}
		if (pusher.isWestOf(pushee)) {
			return "E";
		}
		if (pusher.isNorthEastOf(pushee)) {
			return "SW";
		}
		if (pusher.isNorthWestOf(pushee)) {
			return "SE";
		}
		if (pusher.isSouthEastOf(pushee)) {
			return "NW";
		}
		if (pusher.isSouthWestOf(pushee)) {
			return "NE";
		}
		return "CAN NOT PUSH";
	}

	public Creature chooseAllyAdjacentTo(Creature allyOf, Position target) {
		List<Creature> adjacentCreatureList = null;
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(allyOf)) {
			adjacentCreatureList = getAdjacentMonsters(target);
		} else {
			adjacentCreatureList = getAdjacentCharacters(target);
		}

		HashMap<Integer, Creature> adjacentAllies = new HashMap<Integer, Creature>();
		int index = 0;

		for (Creature creature : adjacentCreatureList) {
			index++;
			adjacentAllies.put(index, creature);
		}

		if (adjacentAllies.isEmpty()) {
			Utils.print("Sorry.  No adjacent allies.");
			return null;
		} else {
			Utils.print("Choose an adjacent ally.");
			for (int i = 1; i <= index; i++) {
				Utils.print(i + ". " + adjacentAllies.get(i).getName());
			}
			int choice = Utils.getValidIntInputInRange(1, index);
			Creature c = adjacentAllies.get(choice);
			return c;
		}
	}

	// This method is to be used for powers that choose an ally "adjacent to either you or the target"
	public Creature chooseAllyAdjacentToEither(Creature allyOf, Position target, Position target2) {
		List<Creature> adjacentCreatureList = null;
		List<Creature> adjacentTarget2List = null;
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(allyOf)) {
			adjacentCreatureList = getAdjacentMonsters(target);
		} else {
			adjacentCreatureList = getAdjacentCharacters(target);
			adjacentTarget2List = getAdjacentCharacters(target2);
			// Combine them.
			for (Creature adj2 : adjacentTarget2List) {
				if (!adjacentCreatureList.contains(adj2)) {
					adjacentCreatureList.add(adj2);
				}
			}
		}

		HashMap<Integer, Creature> adjacentAllies = new HashMap<Integer, Creature>();
		int index = 0;

		for (Creature creature : adjacentCreatureList) {
			index++;
			adjacentAllies.put(index, creature);
		}

		if (adjacentAllies.isEmpty()) {
			Utils.print("Sorry.  No adjacent allies.");
			return null;
		} else {
			Utils.print("Choose an adjacent ally.");
			for (int i = 1; i <= index; i++) {
				Utils.print(i + ". " + adjacentAllies.get(i).getName());
			}
			int choice = Utils.getValidIntInputInRange(1, index);
			Creature c = adjacentAllies.get(choice);
			return c;
		}
	}
	
	public Creature chooseAnyAlly(Creature allyOf) {
		HashMap<Integer, Creature> allies = new HashMap<Integer, Creature>();
		int index = 0;
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(allyOf)) {
			for (Monster monster : monsters) {
				allies.put(index, monster);
			}
		} else {
			for (DndCharacter character : characters) {
				index++;
				allies.put(index, character);
			}
		}

		Utils.print("Choose an ally.");
		for (int i = 1; i <= index; i++) {
			Utils.print(i + ". " + allies.get(i).getName());
		}
		int choice = Utils.getValidIntInputInRange(1, index);
		Creature c = allies.get(choice);
		return c;
	}

	public Creature chooseAllyWithinRangeOf(Creature allyOf, Position target,
			int range) {
		List<Creature> adjacentCreatureList = null;
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(allyOf)) {
			adjacentCreatureList = getMonstersWithinRangeOf(target, range);
		} else {
			adjacentCreatureList = getCharactersWithinRangeOf(target, range);
		}

		HashMap<Integer, Creature> adjacentAllies = new HashMap<Integer, Creature>();
		int index = 0;

		for (Creature creature : adjacentCreatureList) {
			index++;
			adjacentAllies.put(index, creature);
		}

		if (adjacentAllies.isEmpty()) {
			Utils.print("Sorry.  No allies within range.");
			return null;
		} else {
			Utils.print("Choose an ally.");
			for (int i = 1; i <= index; i++) {
				Utils.print(i + ". " + adjacentAllies.get(i).getName());
			}
			int choice = Utils.getValidIntInputInRange(1, index);
			Creature c = adjacentAllies.get(choice);
			return c;
		}
	}

	public List<Creature> getAlliesWithinRangeOf(Creature allyOf, Position target,
			int range) {
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(allyOf)) {
			return getMonstersWithinRangeOf(target, range);
		} else {
			return getCharactersWithinRangeOf(target, range);
		}

	}

	public List<Creature> getEnemiesWithinRangeOf(Creature enemyOf, Position target,
			int range) {
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(enemyOf)) {
			return getCharactersWithinRangeOf(target, range);
		} else {
			return getMonstersWithinRangeOf(target, range);
		}

	}

	public void removeCreature(Creature creature) {
		if (Monster.class.isInstance(creature)) {
			Utils.print("Removed " + creature);
			monsters.remove(creature);
		} else {
			Utils.print("Removed " + creature);
			characters.remove(creature);
		}
		TurnMaster.removeParticipant(creature);
	}

	/*
	 * Use this method to find out if you only have one specific enemy adjacent
	 * to you (for oath of enmity, for example).
	 */
	public boolean areAnyOtherEnemiesAdjacentBesidesTarget(Creature owner,
			List<AttackTarget> targets) {
		/* Get list of adjacent enemies. */
		List<Creature> adjacentEnemies = getAdjacentEnemies(owner);

		/*
		 * If the size of the array is greater than one, then there are
		 * definitely other adjacent enemies.
		 */
		if ((adjacentEnemies != null) && (adjacentEnemies.size() > 1)) {
			return true;
		} else {
			if (targets.contains(adjacentEnemies.get(0))) {
				return false;
			} else {
				return true;
			}
		}
	}

	public List<Creature> getAdjacentEnemies(Creature creature) {
		List<Creature> adjacentEnemies = new ArrayList<Creature>();
		if (DndCharacter.class.isInstance(creature)) {
			for (Monster monster : monsters) {
				if (monster.getCurrentPosition().isWithinReachOf(
						creature.getCurrentPosition(), 1)) {
					adjacentEnemies.add(monster);
				}
			}
		} else {
			for (DndCharacter character : characters) {
				if (character.getCurrentPosition().isWithinReachOf(
						creature.getCurrentPosition(), 1)) {
					adjacentEnemies.add(character);
				}
			}
		}
		if (adjacentEnemies.isEmpty()) {
			return null;
		} else {
			return adjacentEnemies;
		}
	}

	public List<Creature> getAdjacentAllies(Creature creature) {
		List<Creature> adjacentAllies = new ArrayList<Creature>();
		if (Monster.class.isInstance(creature)) {
			if (monsters != null) {
				for (Monster monster : monsters) {
					if (monster.getCurrentPosition().isWithinReachOf(
							creature.getCurrentPosition(), 1)) {
						adjacentAllies.add(monster);
					}
				}
			}
		} else {
			if (characters != null) {
				for (DndCharacter character : characters) {
					if (character.getCurrentPosition().isWithinReachOf(
							creature.getCurrentPosition(), 1)) {
						adjacentAllies.add(character);
					}
				}
			}
		}
		if (adjacentAllies.isEmpty()) {
			return null;
		} else {
			return adjacentAllies;
		}
	}

	public List<Creature> getAdjacentMonsters(Position target) {
		List<Creature> adjacentMonsters = new ArrayList<Creature>();
		for (Monster monster : monsters) {
			if (monster.getCurrentPosition().isWithinReachOf(target, 1)) {
				adjacentMonsters.add(monster);
			}
		}
		if (adjacentMonsters.isEmpty()) {
			return null;
		} else {
			return adjacentMonsters;
		}
	}

	public List<Creature> getAdjacentCharacters(Position target) {
		List<Creature> adjacentCharacters = new ArrayList<Creature>();
		for (DndCharacter character : characters) {
			if (character.getCurrentPosition().isWithinReachOf(target, 1)) {
				adjacentCharacters.add(character);
			}
		}
		if (adjacentCharacters.isEmpty()) {
			return null;
		} else {
			return adjacentCharacters;
		}
	}

	public List<Creature> getMonstersWithinRangeOf(Position target, int range) {
		List<Creature> adjacentMonsters = new ArrayList<Creature>();
		for (Monster monster : monsters) {
			if (monster.getCurrentPosition().isWithinReachOf(target, range)) {
				adjacentMonsters.add(monster);
			}
		}
		if (adjacentMonsters.isEmpty()) {
			return null;
		} else {
			return adjacentMonsters;
		}
	}

	public List<Creature> getCharactersWithinRangeOf(Position target, int range) {
		List<Creature> adjacentCharacters = new ArrayList<Creature>();
		for (DndCharacter character : characters) {
			if (character.getCurrentPosition().isWithinReachOf(target, range)) {
				adjacentCharacters.add(character);
			}
		}
		if (adjacentCharacters.isEmpty()) {
			return null;
		} else {
			return adjacentCharacters;
		}
	}

	public List<Creature> getAllAdjacentCreatures(Creature creature) {
		List<Creature> adjacentCreatures = new ArrayList<Creature>();
		for (Monster monster : monsters) {
			if (monster.getCurrentPosition().isWithinReachOf(
					creature.getCurrentPosition(), 1)) {
				adjacentCreatures.add(monster);
			}
		}
		for (DndCharacter character : characters) {
			if (character.getCurrentPosition().isWithinReachOf(
					creature.getCurrentPosition(), 1)) {
				adjacentCreatures.add(character);
			}
		}
		if (adjacentCreatures.isEmpty()) {
			return null;
		} else {
			return adjacentCreatures;
		}
	}

	public List<Creature> getSpecificTypeOfAdjacentEnemies(Creature creature,
			@SuppressWarnings("rawtypes") Class creatureType) {
		List<Creature> adjacentCreatures = new ArrayList<Creature>();
		for (Monster monster : monsters) {
			if ((creatureType.isInstance(monster))
					&& (monster.getCurrentPosition().isWithinReachOf(
							creature.getCurrentPosition(), 1))) {
				adjacentCreatures.add(monster);
			}
		}
		for (DndCharacter character : characters) {
			if ((creatureType.isInstance(character))
					&& (character.getCurrentPosition().isWithinReachOf(
							creature.getCurrentPosition(), 1))) {
				adjacentCreatures.add(character);
			}
		}
		if (adjacentCreatures.isEmpty()) {
			return null;
		} else {
			return adjacentCreatures;
		}
	}

	public List<AttackTarget> chooseRangedTarget(TurnTaker attacker, int range, int longRange) {
		/* TODO: Need to add logic for visibility/cover/etc. */
		HashMap<Integer, AttackTarget> validChoices = new HashMap<Integer, AttackTarget>();

		Utils.print("Who do you want to attack?");
		int index = 0;

		if (!characters.contains(attacker)) {
			for (DndCharacter c : characters) {
				/* Is this character within reach? */
				if (attacker.getCurrentPosition().isWithinReachOf(
						c.getCurrentPosition(), range)) {
					/* Check for invisibility. */
					if (!c.isInvisibleTo(attacker)) {
						/* Do you have line of sight? */
						if (hasLineOfSight(attacker.getCurrentPosition(), c.getCurrentPosition())) {
							index++;
							validChoices.put(index, c);
							Utils.print(index + ". " + c.getName());
						}
					}
				}
			}
		}

		if (!monsters.contains(attacker)) {
			for (Monster m : monsters) {
				/* Is this character within reach? */
				if (attacker.getCurrentPosition().isWithinReachOf(
						m.getCurrentPosition(), range)) {
					/* Check for invisibility. */
					if (!m.isInvisibleTo(attacker)) {
						/* Do you have line of sight? */
						if (hasLineOfSight(attacker.getCurrentPosition(), m.getCurrentPosition())) {
							index++;
							validChoices.put(index, m);
							Utils.print(index + ". " + m.getName());
						}
					}
				}
			}
		}

		/*
		 * TODO: Later, when I have traps and other attackable things I can add
		 * those here.
		 */
		if (index < 1) {
			Utils.print("OOPS!  No one to choose from");
			return null;
		}

		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, index);

		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(validChoices.get(choice));
		return targets;
	}

	
	// Use this one when you want to pick an enemy of the attacker, but not from the attacker's position.  See Sorcerer Chaos Bolt as an example.
	public List<AttackTarget> chooseRangedTargetFromPosition(TurnTaker attacker, Position position, int range, int longRange) {
		/* TODO: Need to add logic for visibility/cover/etc. */
		HashMap<Integer, AttackTarget> validChoices = new HashMap<Integer, AttackTarget>();

		Utils.print("Who do you want to attack?");
		int index = 0;

		if (!characters.contains(attacker)) {
			for (DndCharacter c : characters) {
				/* Is this character within reach? */
				if (position.isWithinReachOf(
						c.getCurrentPosition(), range)) {
					/* Check for invisibility. */
					if (!c.isInvisibleTo(attacker)) {
						/* Do you have line of sight? */
						if (hasLineOfSight(position, c.getCurrentPosition())) {
							index++;
							validChoices.put(index, c);
							Utils.print(index + ". " + c.getName());
						}
					}
				}
			}
		}

		if (!monsters.contains(attacker)) {
			for (Monster m : monsters) {
				/* Is this character within reach? */
				if (position.isWithinReachOf(
						m.getCurrentPosition(), range)) {
					/* Check for invisibility. */
					if (!m.isInvisibleTo(attacker)) {
						/* Do you have line of sight? */
						if (hasLineOfSight(position, m.getCurrentPosition())) {
							index++;
							validChoices.put(index, m);
							Utils.print(index + ". " + m.getName());
						}
					}
				}
			}
		}

		/*
		 * TODO: Later, when I have traps and other attackable things I can add
		 * those here.
		 */
		if (index < 1) {
			Utils.print("OOPS!  No one to choose from");
			return null;
		}

		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, index);

		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(validChoices.get(choice));
		return targets;
	}
	
	/* TODO: Check for enemies providing cover. */
	public int getCoverPenalty(Position attackerPosition, Position targetPosition) {
		int fewestLinesBlocked = 4;

		List<String> attackerCorners = new ArrayList<String>();
		List<String> targetCorners = new ArrayList<String>();
		attackerCorners.add("NW");
		targetCorners.add("NW");
		attackerCorners.add("NE");
		targetCorners.add("NE");
		attackerCorners.add("SE");
		targetCorners.add("SE");
		attackerCorners.add("SW");
		targetCorners.add("SW");

		/* We can have special EASY processing if the positions are lined up. */
		/* Are they on the same column? */
		if (attackerPosition.getX() == targetPosition.getX()) {
			if (!isVerticalLineClear(attackerPosition,
					targetPosition)) {
				Utils.print("Superior Cover: -5");
				return -5;
			}
		}

		/* Are they on the same row? */
		if (attackerPosition.getY() == targetPosition.getY()) {
			if (!isHorizontalLineClear(attackerPosition,
					targetPosition)) {
				Utils.print("Superior Cover: -5");
				return -5;
			}
		}

		/* We have no way of knowing which corner provides the least amount of blocking, so we have to go through them all. */
		for (String attackerCorner : attackerCorners) {
			int currentLinesBlocked = 0; 
			Position aCornerPos = getCornerPosition(
					attackerPosition, attackerCorner);
			for (String targetCorner : targetCorners) {
				Position tCornerPos = getCornerPosition(
						targetPosition, targetCorner);

				if (!isCornerLineClear(aCornerPos, tCornerPos)) {
					currentLinesBlocked++;
				}
			}
			Utils.print("Lines blocked = " + currentLinesBlocked);
			if (currentLinesBlocked < fewestLinesBlocked) {
				fewestLinesBlocked = currentLinesBlocked;
			}
		}
		/* How much cover did they have? */
		if (fewestLinesBlocked == 0) {
			Utils.print("Clear shot!  No penalty.");
			return 0;
		} else if (fewestLinesBlocked < 3) {
			Utils.print("Target has cover!  -2 penalty.");
			return -2;
		} else {
			Utils.print("Target has superior cover! -5 penalty.");
			return -5;
		}

	}

	private boolean hasLineOfSight(Position attackerPosition, Position targetPosition) {
		List<String> attackerCorners = new ArrayList<String>();
		List<String> targetCorners = new ArrayList<String>();
		attackerCorners.add("NW");
		targetCorners.add("NW");
		attackerCorners.add("NE");
		targetCorners.add("NE");
		attackerCorners.add("SE");
		targetCorners.add("SE");
		attackerCorners.add("SW");
		targetCorners.add("SW");

		/* We can have special EASY processing if the positions are lined up. */
		/* Are they on the same column? */
		if (attackerPosition.getX() == targetPosition.getX()) {
			return isVerticalLineClear(attackerPosition,
					targetPosition);
		}

		/* Are they on the same row? */
		if (attackerPosition.getY() == targetPosition.getY()) {
			return isHorizontalLineClear(attackerPosition,
					targetPosition);
		}

		for (String attackerCorner : attackerCorners) {
			Position aCornerPos = getCornerPosition(
					attackerPosition, attackerCorner);
			for (String targetCorner : targetCorners) {
				Position tCornerPos = getCornerPosition(
						targetPosition, targetCorner);
				/*For line of site, you only need one clear path. */
				if (isCornerLineClear(aCornerPos, tCornerPos)) {
					return true;
				}
			}
		}
		/* If we got here, then no path was open. */
		return false;
	}

	private boolean isCornerLineClear(Position aCornerPos, Position tCornerPos) {
		/*
		 * In the hasLineOfSight method, we checked for when the MAP squares
		 * were on the same row/col. In that case, we simply needed to check
		 * each MAP space between to see if it provided cover. But for checking
		 * from CORNER to CORNER, we need even more special processing to see if
		 * the line is clear. That line could run along the EDGE of an obstacle.
		 * That would NOT be blocking, but going BETWEEN two map spaces that
		 * provide cover WOULD be blocking.
		 */
		/* Are they on the same column? */
		if (aCornerPos.getX() == tCornerPos.getX()) {
			return isVerticalCornerLineClear(aCornerPos, tCornerPos);
		}

		/* Are they on the same row? */
		if (aCornerPos.getY() == tCornerPos.getY()) {
			return isHorizontalCornerLineClear(aCornerPos, tCornerPos);
		}

		/* We're dealing with an angle other than 0 or 90. */
		return isAngledCornerLineClear(aCornerPos, tCornerPos);

	}

	private boolean isVerticalLineClear(Position aPos, Position tPos) {
		Position p1 = null;
		Position p2 = null;
		/* Sort bottom to top. */
		if (aPos.getY() < tPos.getY()) {
			p1 = aPos;
			p2 = tPos;
		} else {
			p1 = tPos;
			p2 = aPos;
		}
		boolean clear = true;
		for (int y = p1.getY() + 1; y < p2.getY(); y++) {
			Position currentPos = new Position(p1.getX(), y);
			if (map.providesCover(currentPos)) {
				clear = false;
			}
		}
		return clear;
	}

	private boolean isVerticalCornerLineClear(Position aCornerPos,
			Position tCornerPos) {
		Position p1 = null;
		Position p2 = null;
		/* Sort bottom to top. */
		if (aCornerPos.getY() < tCornerPos.getY()) {
			p1 = aCornerPos;
			p2 = tCornerPos;
		} else {
			p1 = tCornerPos;
			p2 = aCornerPos;
		}
		boolean clear = true;
		for (int y = p1.getY() + 1; y < p2.getY(); y++) {
			Position leftSquare = new Position(p1.getX() - 1, y - 1);
			Position rightSquare = new Position(p1.getX(), y - 1);
			if ((map.providesCover(leftSquare))
					&& (map.providesCover(rightSquare))) {
				clear = false;
			}
		}
		return clear;
	}

	private boolean isHorizontalCornerLineClear(Position aCornerPos,
			Position tCornerPos) {
		Position p1 = null;
		Position p2 = null;
		/* Sort left to right. */
		if (aCornerPos.getX() < tCornerPos.getX()) {
			p1 = aCornerPos;
			p2 = tCornerPos;
		} else {
			p1 = tCornerPos;
			p2 = aCornerPos;
		}
		boolean clear = true;
		for (int x = p1.getX() + 1; x < p2.getX(); x++) {
			Position bottomSquare = new Position(x - 1, p1.getY() - 1);
			Position topSquare = new Position(x - 1, p1.getY());
			if ((map.providesCover(bottomSquare))
					&& (map.providesCover(topSquare))) {
				clear = false;
			}
		}
		return clear;
	}

	private boolean isAngledCornerLineClear(Position aCornerPos,
			Position tCornerPos) {
		Position p1 = null;
		Position p2 = null;
		/* Sort left to right. */
		if (aCornerPos.getX() < tCornerPos.getX()) {
			p1 = aCornerPos;
			p2 = tCornerPos;
		} else {
			p1 = tCornerPos;
			p2 = aCornerPos;
		}
		boolean clear = true;

		/* Calculate the slope of the line. */
		double slope = (double) (p2.getY() - p1.getY())
				/ (double) (p2.getX() - p1.getX());

		/*
		 * Now increment from the beginning of the line to the end, checking for
		 * blockage.
		 */
		double currentX = p1.getX();
		double currentY = 0.0;
		double incrementValue = 0.05;
		/* Calculate the y intercept (b) */
		double b = p1.getY() - (slope * p1.getX());

		while (currentX < p2.getX()) {
			currentY = (slope * currentX) + b;

			/* If you are going right through a corner, check all four surrounding squares. */
			/* But since we are dealing with floating points numbers, it is difficult, so bear with me.... */
			double floatingPortionOfX = currentX - Math.floor(currentX);
			double floatingPortionOfY = currentY - Math.floor(currentY);
			double wholeX = 0;
			double wholeY = 0;
			/* We could have just gone slightly greater than the most recent whole X. */
			if (floatingPortionOfX < 0.000001) {
				wholeX = Math.floor(currentX);
			} else if (floatingPortionOfX > 0.999999) {
				/* Or we could be approaching a whole X. */
				wholeX = Math.ceil(currentX);
			}

			/* We could have just gone slightly greater than the most recent whole Y. */
			if (floatingPortionOfY < 0.000001) {
				wholeY = Math.floor(currentY);
			} else if (floatingPortionOfY > 0.999999) {
				/* Or we could be approaching a whole Y. */
				wholeY = Math.ceil(currentY);
			}

			/* Are we "on" a corner? (But don't check when on an endpoint of the line. */
			if ((wholeX != 0.0) && (wholeY != 0.0) && (wholeX != p2.getX()) && (wholeY != p2.getY())
					&& (wholeX != p1.getX()) && (wholeY != p1.getY())) {
				/* Now check the 4 surrounding squares.  I THINK I don't need to check for map edges
				 * because that would be an endpoint of the line anyway, right?
				 */
				/* NW square. */
				Position nw = new Position(wholeX-1, wholeY);
				if (map.providesCover(nw)) {
					return false;
				}
				/* NE square. */
				Position ne = new Position(wholeX, wholeY);
				if (map.providesCover(ne)) {
					return false;
				}
				/* SE square. */
				Position se = new Position(wholeX, wholeY-1);
				if (map.providesCover(se)) {
					return false;
				}
				/* SW square. */
				Position sw = new Position(wholeX-1, wholeY-1);
				if (map.providesCover(sw)) {
					return false;
				}
			} else {
				Position pos = new Position(currentX, currentY);

				if (map.providesCover(pos)) {
					return false;
				}
			}

			currentX = currentX + incrementValue;
		}

		return clear;
	}

	private boolean isHorizontalLineClear(Position aPos, Position tPos) {
		Position p1 = null;
		Position p2 = null;
		/* Sort left to right. */
		if (aPos.getX() < tPos.getX()) {
			p1 = aPos;
			p2 = tPos;
		} else {
			p1 = tPos;
			p2 = aPos;
		}
		boolean clear = true;
		for (int x = p1.getX() + 1; x < p2.getX(); x++) {
			Position currentPos = new Position(x, p1.getY());
			if (map.providesCover(currentPos)) {
				clear = false;
			}
		}
		return clear;
	}

	private Position getCornerPosition(Position mapPos, String corner) {
		int x = 0;
		int y = 0;
		if ("NW".equalsIgnoreCase(corner)) {
			x = mapPos.getX();
			y = mapPos.getY() + 1;
		} else if ("NE".equalsIgnoreCase(corner)) {
			x = mapPos.getX() + 1;
			y = mapPos.getY() + 1;
		} else if ("SE".equalsIgnoreCase(corner)) {
			x = mapPos.getX() + 1;
			y = mapPos.getY();
		} else {
			x = mapPos.getX();
			y = mapPos.getY();
		}
		Position pos = new Position(x, y);
		return pos;
	}

	public List<AttackTarget> getAllEnemiesInBlast(Creature creature, int lowerLeftX,
			int lowerLeftY, int size) {
		List<AttackTarget> adjacentCreatures = new ArrayList<AttackTarget>();

		if (DndCharacter.class.isInstance(creature)) {
			for (Monster monster : monsters) {
				if (monster.getCurrentPosition().isWithinBlast(lowerLeftX,
						lowerLeftY, size)) {
					adjacentCreatures.add(monster);
				}
			}
		} else {
			for (DndCharacter character : characters) {
				if (character.getCurrentPosition().isWithinBlast(lowerLeftX,
						lowerLeftY, size)) {
					adjacentCreatures.add(character);
				}
			}
		}
		if (adjacentCreatures.isEmpty()) {
			return null;
		} else {
			return adjacentCreatures;
		}
	}	
	
	public List<Creature> getAllCreaturesInBlast(int lowerLeftX,
			int lowerLeftY, int size) {
		/* TODO: Implement "must have line of sight" rule. */
		List<Creature> adjacentCreatures = new ArrayList<Creature>();
		for (Monster monster : monsters) {
			if (monster.getCurrentPosition().isWithinBlast(lowerLeftX,
					lowerLeftY, size)) {
				adjacentCreatures.add(monster);
			}
		}
		for (DndCharacter character : characters) {
			if (character.getCurrentPosition().isWithinBlast(lowerLeftX,
					lowerLeftY, size)) {
				adjacentCreatures.add(character);
			}
		}
		if (adjacentCreatures.isEmpty()) {
			return null;
		} else {
			return adjacentCreatures;
		}
	}

	public List<Creature> getAllCreaturesInAreaBurst(Position position, int size) {
		/* TODO: Implement "must have line of sight" rule. */
		List<Creature> creatures = new ArrayList<Creature>();
		for (Monster monster : monsters) {
			if (monster.getCurrentPosition().isWithinReachOf(position, size)) {
				creatures.add(monster);
			}
		}
		for (DndCharacter character : characters) {
			if (character.getCurrentPosition().isWithinReachOf(position, size)) {
				creatures.add(character);
			}
		}
		if (creatures.isEmpty()) {
			return null;
		} else {
			return creatures;
		}
	}

	public List<AttackTarget> getAllEnemiesInAreaBurst(Creature creature, Position position, int size) {
		List<AttackTarget> creatures = new ArrayList<AttackTarget>();

		if (DndCharacter.class.isInstance(creature)) {
			for (Monster monster : monsters) {
				if (monster.getCurrentPosition().isWithinReachOf(position, size)) {
					creatures.add(monster);
				}
			}
		} else {
			for (DndCharacter character : characters) {
				if (character.getCurrentPosition().isWithinReachOf(position, size)) {
					creatures.add(character);
				}
			}
		}

		if (creatures.isEmpty()) {
			return null;
		} else {
			return creatures;
		}
	}

	public String getPullDirection(Position puller, Position pullee) {
		if (puller.isNorthOf(pullee)) {
			return "N";
		}
		if (puller.isEastOf(pullee)) {
			return "E";
		}
		if (puller.isSouthOf(pullee)) {
			return "S";
		}
		if (puller.isWestOf(pullee)) {
			return "W";
		}
		if (puller.isNorthEastOf(pullee)) {
			return "NE";
		}
		if (puller.isNorthWestOf(pullee)) {
			return "NW";
		}
		if (puller.isSouthEastOf(pullee)) {
			return "SE";
		}
		if (puller.isSouthWestOf(pullee)) {
			return "SW";
		}
		return "CAN NOT PULL";
	}

	public Position getPositionRelativeTo(Position currentPosition,
			String direction) {

		Position tempPosition = new Position(currentPosition.getX(),
				currentPosition.getY());

		/*
		 * For now, I'm trusting the user input, and not doing any validation
		 * about whether the creature can actually move that direction or not.
		 */
		if ("N".equalsIgnoreCase(direction)) {
			tempPosition.setY(tempPosition.getY() + 1);
		} else if ("E".equalsIgnoreCase(direction)) {
			tempPosition.setX(tempPosition.getX() + 1);
		} else if ("S".equalsIgnoreCase(direction)) {
			tempPosition.setY(tempPosition.getY() - 1);
		} else if ("W".equalsIgnoreCase(direction)) {
			tempPosition.setX(tempPosition.getX() - 1);
		} else if ("NE".equalsIgnoreCase(direction)) {
			tempPosition.setX(tempPosition.getX() + 1);
			tempPosition.setY(tempPosition.getY() + 1);
		} else if ("NW".equalsIgnoreCase(direction)) {
			tempPosition.setX(tempPosition.getX() - 1);
			tempPosition.setY(tempPosition.getY() + 1);
		} else if ("SE".equalsIgnoreCase(direction)) {
			tempPosition.setX(tempPosition.getX() + 1);
			tempPosition.setY(tempPosition.getY() - 1);
		} else if ("SW".equalsIgnoreCase(direction)) {
			tempPosition.setX(tempPosition.getX() - 1);
			tempPosition.setY(tempPosition.getY() - 1);
		}
		return tempPosition;
	}

	public boolean isDifficultTerrain(Position position) {
		return map.isDifficultTerrain(position);
	}

	public boolean requiresCheckToEnter(Position newPosition,
			Position currentPosition) {
		/* Right now, only know about boulders. */
		/* If moving from a non-boulder to a boulder. */
		if ((map.isBoulder(newPosition)) && (!map.isBoulder(currentPosition))) {
			return true;
		}
		return false;
	}

	public SkillCheck getGenericSkillCheck() {
		Utils.print("I'm trying to keep this generic.  Please enter the skill that needs to be used for the skill test to enter this location.");
		List<String> validChoices = new ArrayList<String>();
		validChoices.add("Acrobatics");
		Utils.print("Acrobatics");
		validChoices.add("Arcana");
		Utils.print("Arcana");
		validChoices.add("Athletics");
		Utils.print("Athletics");
		validChoices.add("Bluff");
		Utils.print("Bluff");
		validChoices.add("Diplomacy");
		Utils.print("Diplomacy");
		validChoices.add("Dungeoneering");
		Utils.print("Dungeoneering");
		validChoices.add("Endurance");
		Utils.print("Endurance");
		validChoices.add("Heal");
		Utils.print("Heal");
		validChoices.add("History");
		Utils.print("History");
		validChoices.add("Insight");
		Utils.print("Insight");
		validChoices.add("Intimidate");
		Utils.print("Intimidate");
		validChoices.add("Nature");
		Utils.print("Nature");
		validChoices.add("Perception");
		Utils.print("Perception");
		validChoices.add("Religion");
		Utils.print("Religion");
		validChoices.add("Stealth");
		Utils.print("Stealth");
		validChoices.add("Streetwise");
		Utils.print("Streetwise");
		validChoices.add("Thievery");
		Utils.print("Thievery");

		Utils.print("You Choose:");
		String choice = Utils.getValidInput(validChoices);
		SkillCheck skillCheck = new SkillCheck();
		if ("Acrobatics".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.ACROBATICS);
		} else if ("Arcana".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.ARCANA);
		} else if ("Athletics".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.ATHLETICS);
		} else if ("Bluff".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.BLUFF);
		} else if ("Diplomacy".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.DIPLOMACY);
		} else if ("Endurance".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.ENDURANCE);
		} else if ("Heal".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.HEAL);
		} else if ("History".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.HISTORY);
		} else if ("Insight".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.INSIGHT);
		} else if ("Intimidate".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.INTIMIDATE);
		} else if ("Nature".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.NATURE);
		} else if ("Perception".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.PERCEPTION);
		} else if ("Religion".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.RELIGION);
		} else if ("Stealth".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.STEALTH);
		} else if ("Streetwise".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.STREETWISE);
		} else if ("Thievery".equalsIgnoreCase(choice)) {
			skillCheck.setSkillType(SkillType.THIEVERY);
		}

		Utils.print("What is the DC for this test?");
		int dc = Utils.getValidIntInputInRange(1, 100);
		skillCheck.setDifficultyClass(dc);
		return skillCheck;
	}

	public int getCostForEnteringSquare() {
		Utils.print("Enter the cost for entering this square.");
		int cost = Utils.getValidIntInputInRange(0, 20);
		return cost;
	}

	public boolean isSacredCircle(Position pos) {
		return map.isSacredCircle(pos);
	}

	public abstract void init();

	public static void setEncounter(Encounter e) {
		encounter = e;
		
	}

	public static Encounter getEncounter() {
		return encounter;
	}

	public void setZone(Position zoneOrigin, ZoneShape zoneShape, int size, Creature owner, DurationType duration, int startTurn, ZoneType zoneType) {
		Zone zone = new Zone();
		zone.setZoneOrigin(zoneOrigin);
		zone.setZoneShape(zoneShape);
		zone.setSize(size);
		zone.setOwner(owner);
		zone.setDuration(duration);
		zone.setStartTurn(startTurn);
		zone.setZoneType(zoneType);
		
		if (zones == null) {
			zones = new ArrayList<Zone>();
		}
		zones.add(zone);
	}

	public static void setDebug(boolean b) {
		debug = b;		
	}
	public static boolean isDebug() {
		return debug;
	}

	public static void showCoordinateSystem(boolean b) {
		showCoordinateSystem = b;
	}

	public static boolean isShowCoordinateSystem() {
		return showCoordinateSystem;
	}

	public int getDistanceToClosestEnemy(Creature enemyOf) {
		int minDistance = 999;
		
		List<Creature> allEnemies = getAllEnemies(enemyOf);
		for (Creature enemy : allEnemies) {
			int distance = enemyOf.getCurrentPosition().getDistanceTo(enemy.getCurrentPosition());
			if (distance < minDistance) {
				minDistance = distance;
			}
		}
		
		return minDistance;
	}

	private List<Creature> getAllEnemies(Creature enemyOf) {
		List<Creature> creatures = new ArrayList<Creature>();
		
		/* Get the list of adjacent allies. */
		if (Monster.class.isInstance(enemyOf)) {
			for (DndCharacter character : characters) {
				creatures.add(character);
			}
		} else {
			for (Monster monster : monsters) {
				creatures.add(monster);
			}
		}
		return creatures;
	}

	public static boolean areMonstersVisible() {
		return monstersVisible;
	}

	public static void setMonstersVisible(boolean monstersVisible) {
		Encounter.monstersVisible = monstersVisible;
	}

	public static boolean areMonstersActive() {
		return monstersActive;
	}

	public static void setMonstersActive(boolean monstersActive) {
		Encounter.monstersActive = monstersActive;
	}

	public abstract void setup();

	public static boolean areCharactersVisible() {
		return charactersVisible;
	}

	public static void setCharactersVisible(boolean charactersVisible) {
		Encounter.charactersVisible = charactersVisible;
	}

	public static boolean areCharactersActive() {
		return charactersActive;
	}

	public static void setCharactersActive(boolean charactersActive) {
		Encounter.charactersActive = charactersActive;
	}

	public int getMonsterInitiative(Class<? extends Creature> class1) {
		if (monsterInitiatives.containsKey(class1)) {
			return monsterInitiatives.get(class1);
		}
		return 0;
	}

	public TurnTaker getCurrentParticipant() {
		return currentParticipant;
	}

	public void setCurrentParticipant(TurnTaker currentParticipant) {
		this.currentParticipant = currentParticipant;
	}
}
