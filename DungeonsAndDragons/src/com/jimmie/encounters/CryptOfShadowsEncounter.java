package com.jimmie.encounters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.jimmie.domain.IlluminationType;
import com.jimmie.domain.Position;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.creatures.monsters.Zombie;
import com.jimmie.domain.creatures.monsters.ZombieRotter;
import com.jimmie.domain.map.Door;
import com.jimmie.domain.map.LocationType;
import com.jimmie.domain.map.Map;
import com.jimmie.domain.map.MapLocation;
import com.jimmie.domain.map.Orientation;
import com.jimmie.util.Utils;

public class CryptOfShadowsEncounter extends Encounter {
	@Autowired
	private Zombie z1;
	@Autowired
	private Zombie z2;
	@Autowired
	private Zombie z3;
	@Autowired
	private Zombie z4;
	@Autowired
	private ZombieRotter r1;
	@Autowired
	private ZombieRotter r2;
	@Autowired
	private ZombieRotter r3;
	@Autowired
	private ZombieRotter r4;
	@Autowired
	private ZombieRotter r5;
	@Autowired
	private ZombieRotter r6;
	@Autowired
	private ZombieRotter r7;
	@Autowired
	private ZombieRotter r8;
	@Autowired
	private ZombieRotter r9;
	@Autowired
	private ZombieRotter r10;
	@Autowired
	private DndCharacter gamal;
	@Autowired
	private DndCharacter percian;
	@Autowired
	private DndCharacter keothi;
	@Autowired
	private DndCharacter travok;
	@Autowired
	private DndCharacter hazel;
	private boolean pcHasSteppedIntoSouthernChamber;
	private boolean runeHasBeenActivated;
	private HashMap<Integer, List<Creature>> terrorRuneEffects = new HashMap<Integer, List<Creature>>();

	@Override
	protected void makeEncounterInitiativeChanges() {
	}

	@Override
	public void init() {
		// Initialize hashmap.
		List<Creature> creatures1 = new ArrayList<Creature>();
		List<Creature> creatures2 = new ArrayList<Creature>();
		List<Creature> creatures3 = new ArrayList<Creature>();
		List<Creature> creatures4 = new ArrayList<Creature>();
		List<Creature> creatures5 = new ArrayList<Creature>();
		terrorRuneEffects.put(1, creatures1);
		terrorRuneEffects.put(2, creatures2);
		terrorRuneEffects.put(3, creatures3);
		terrorRuneEffects.put(4, creatures4);
		terrorRuneEffects.put(5, creatures5);
		
		gamal.setCurrentPosition(new Position (-1,-1));
		percian.setCurrentPosition(new Position (-1,-1));
		keothi.setCurrentPosition(new Position (-1,-1));
		travok.setCurrentPosition(new Position (-1,-1));
		hazel.setCurrentPosition(new Position (-1,-1));

		/* Set up the monsters */
		monsters = new ArrayList<Monster>();		
		monsters.add(z1);		
		monsters.add(z2);		
		monsters.add(z3);		
		monsters.add(z4);		
		monsters.add(r1);		
		monsters.add(r2);		
		monsters.add(r3);		
		monsters.add(r4);		
		monsters.add(r5);		
		monsters.add(r6);		
		monsters.add(r7);		
		monsters.add(r8);		
		monsters.add(r9);		
		monsters.add(r10);		

		if (PlayerCharacter.class.isAssignableFrom(gamal.getClass())) {
//			((PlayerCharacter) gamal).setExperiencePoints(1016);
//			gamal.addCoins(9, CoinType.GOLD_PIECE);
//			gamal.addCoins(6, CoinType.SILVER_PIECE);
			Utils.print("Gamal has " + ((PlayerCharacter) gamal).getExperiencePoints() + " experience points.");
			Utils.printCoins(gamal);			
		}
		if (PlayerCharacter.class.isAssignableFrom(percian.getClass())) {
//			((PlayerCharacter) percian).setExperiencePoints(1016);
//			percian.addCoins(9, CoinType.GOLD_PIECE);
//			percian.addCoins(6, CoinType.SILVER_PIECE);
			Utils.print("Percian has " + ((PlayerCharacter) percian).getExperiencePoints() + " experience points.");
			Utils.printCoins(percian);			
		}
		if (PlayerCharacter.class.isAssignableFrom(keothi.getClass())) {
//			((PlayerCharacter) keothi).setExperiencePoints(1016);
//			keothi.addCoins(9, CoinType.GOLD_PIECE);
//			keothi.addCoins(7, CoinType.SILVER_PIECE);
			Utils.print("Keothi has " + ((PlayerCharacter) keothi).getExperiencePoints() + " experience points.");
			Utils.printCoins(keothi);			
		}
		if (PlayerCharacter.class.isAssignableFrom(travok.getClass())) {
//			((PlayerCharacter) travok).setExperiencePoints(1016);
//			travok.addCoins(9, CoinType.GOLD_PIECE);
//			travok.addCoins(6, CoinType.SILVER_PIECE);
			Utils.print("Travok has " + ((PlayerCharacter) travok).getExperiencePoints() + " experience points.");
			Utils.printCoins(travok);			
		}
		if (PlayerCharacter.class.isAssignableFrom(hazel.getClass())) {
//			((PlayerCharacter) hazel).setExperiencePoints(1016);
//			hazel.addCoins(9, CoinType.GOLD_PIECE);
//			hazel.addCoins(6, CoinType.SILVER_PIECE);
			Utils.print("Hazel has " + ((PlayerCharacter) hazel).getExperiencePoints() + " experience points.");
			Utils.printCoins(hazel);			
		}

/*		
try {
	gamal.spendCoins(new Price(50,CoinType.GOLD_PIECE));
	keothi.spendCoins(new Price(50,CoinType.GOLD_PIECE));
	gamal.addGear(new PotionOfHealing());
	keothi.addGear(new PotionOfHealing());
} catch (NotEnoughCurrencyException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


gamal.addPower(new ReadyPotion());
gamal.addPower(new QuaffPotion());
percian.addPower(new ReadyPotion());
percian.addPower(new QuaffPotion());
keothi.addPower(new ReadyPotion());
keothi.addPower(new QuaffPotion());
travok.addPower(new ReadyPotion());
travok.addPower(new QuaffPotion());
hazel.addPower(new ReadyPotion());
hazel.addPower(new QuaffPotion());
gamal.addPower(new StandUp());
percian.addPower(new StandUp());
keothi.addPower(new StandUp());
travok.addPower(new StandUp());
hazel.addPower(new StandUp());

gamal.addPower(new OpenDoor());
percian.addPower(new OpenDoor());
keothi.addPower(new OpenDoor());
travok.addPower(new OpenDoor());
hazel.addPower(new OpenDoor());
gamal.addPower(new CloseDoor());
percian.addPower(new CloseDoor());
keothi.addPower(new CloseDoor());
travok.addPower(new CloseDoor());
hazel.addPower(new CloseDoor());


boolean removedOpen = false;
boolean removedClose = false;
removedOpen = false;
removedClose = false;
for (Iterator<Power> it = gamal.getPowers().iterator(); it.hasNext();) {
	Power power = it.next();
	if (OpenDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedOpen) {
			it.remove();
			Utils.print("Removed Open");			
			removedOpen = true;
		} else {
			Utils.print("Found another open");
		}
	}
	if (CloseDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedClose) {
			it.remove();
			Utils.print("Removed Close");			
			removedClose = true;
		} else {
			Utils.print("Found another close");
		}
	}
}
for (Iterator<Power> it = percian.getPowers().iterator(); it.hasNext();) {
	Power power = it.next();
	boolean removedOpen = false;
	boolean removedClose = false;
	if (OpenDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedOpen) {
			it.remove();
			Utils.print("Removed Open");			
			removedOpen = true;
		} else {
			Utils.print("Found another open");
		}
	}
	if (CloseDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedClose) {
			it.remove();
			Utils.print("Removed Close");			
			removedClose = true;
		} else {
			Utils.print("Found another close");
		}
	}
}
for (Iterator<Power> it = keothi.getPowers().iterator(); it.hasNext();) {
	Power power = it.next();
	boolean removedOpen = false;
	boolean removedClose = false;
	if (OpenDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedOpen) {
			it.remove();
			Utils.print("Removed Open");			
			removedOpen = true;
		} else {
			Utils.print("Found another open");
		}
	}
	if (CloseDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedClose) {
			it.remove();
			Utils.print("Removed Close");			
			removedClose = true;
		} else {
			Utils.print("Found another close");
		}
	}
}
for (Iterator<Power> it = travok.getPowers().iterator(); it.hasNext();) {
	Power power = it.next();
	boolean removedOpen = false;
	boolean removedClose = false;
	if (OpenDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedOpen) {
			it.remove();
			Utils.print("Removed Open");			
			removedOpen = true;
		} else {
			Utils.print("Found another open");
		}
	}
	if (CloseDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedClose) {
			it.remove();
			Utils.print("Removed Close");			
			removedClose = true;
		} else {
			Utils.print("Found another close");
		}
	}
}
for (Iterator<Power> it = hazel.getPowers().iterator(); it.hasNext();) {
	Power power = it.next();
	boolean removedOpen = false;
	boolean removedClose = false;
	if (OpenDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedOpen) {
			it.remove();
			Utils.print("Removed Open");			
			removedOpen = true;
		} else {
			Utils.print("Found another open");
		}
	}
	if (CloseDoor.class.isAssignableFrom(power.getClass())) {
		if (!removedClose) {
			it.remove();
			Utils.print("Removed Close");			
			removedClose = true;
		} else {
			Utils.print("Found another close");
		}
	}
}

		
gamal.addPower(new ReadyLightSource());
percian.addPower(new ReadyLightSource());
keothi.addPower(new ReadyLightSource());
travok.addPower(new ReadyLightSource());
hazel.addPower(new ReadyLightSource());

gamal.addPower(new Escape());
percian.addPower(new Escape());
keothi.addPower(new Escape());
travok.addPower(new Escape());
hazel.addPower(new Escape());
	
keothi.addPower(new RemoveFormOfTheWillowSentinel());

		
gamal.setLevel(2);
gamal.addPower(new ShieldOfSacrifice());
if (PlayerCharacter.class.isAssignableFrom(gamal.getClass())) {
	((PlayerCharacter) gamal).addFeat(new RuneOfVengeance());
}
percian.setLevel(2);
percian.addPower(new DeepShroud());
if (PlayerCharacter.class.isAssignableFrom(percian.getClass())) {
	((PlayerCharacter) percian).addFeat(new RisingSpellfury());
}
keothi.setLevel(2);
keothi.addPower(new GuardianThorns());
if (PlayerCharacter.class.isAssignableFrom(keothi.getClass())) {
	((PlayerCharacter) keothi).addFeat(new LifespiritHealing());
}
travok.setLevel(2);
travok.addPower(new BlessWeapon());
if (PlayerCharacter.class.isAssignableFrom(travok.getClass())) {
	((PlayerCharacter) travok).addFeat(new DevotedPaladin());
}
hazel.setLevel(2);
hazel.addPower(new WardingWind());
if (PlayerCharacter.class.isAssignableFrom(hazel.getClass())) {
	((PlayerCharacter) hazel).addFeat(new FerociousTigerForm());
}


travok.setMaxHitPoints(35);
travok.setCurrentHitPoints(35);
hazel.setMaxHitPoints(31);
hazel.setCurrentHitPoints(31);
percian.setMaxHitPoints(28);
percian.setCurrentHitPoints(28);
keothi.setMaxHitPoints(40);
keothi.setCurrentHitPoints(40);
gamal.setMaxHitPoints(30);
gamal.setCurrentHitPoints(30);

if (PlayerCharacter.class.isAssignableFrom(gamal.getClass())) {
	PlayerCharacter pc = (PlayerCharacter) gamal;
	Iterator<Armor> it = pc.getArmor().iterator();
	while (it.hasNext()) {
		Armor armor = it.next();
		if (ClothArmor.class.isAssignableFrom(armor.getClass())) {
			it.remove();
		}
		if (ScaleArmor.class.isAssignableFrom(armor.getClass())) {
			pc.setReadiedArmor(armor);
		}
	}
}
		
Utils.saveCharacter(gamal);
Utils.saveCharacter(percian);
Utils.saveCharacter(keothi);
Utils.saveCharacter(travok);
Utils.saveCharacter(hazel);
*/
		
		
		characters = new ArrayList<DndCharacter>();
		characters.add(gamal);
		characters.add(percian);
		characters.add(keothi);
		characters.add(travok);
		characters.add(hazel);
		
		map = new Map();
		map.setIllumination(IlluminationType.DARKNESS);		
		map.setWidth(25);
		map.setHeight(35);
		
		//Position position, LocationType locationType, boolean difficultTerrain, SkillCheck skillCheckToEnter, int extraMovementCostToEnter, int height
		map.addLocation(new MapLocation(new Position(1,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,22), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,23), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,26), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,27), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,28), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,29), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,30), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,31), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,32), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,32), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,15), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,18), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,19), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,32), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,33), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,34), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,35), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,8), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,32), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,33), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,34), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,35), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,32), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,26), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,27), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,30), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,31), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,32), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,26), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,27), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,30), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,30), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,29), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,8), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,25), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,26), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,27), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,28), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,29), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,30), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,4), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,8), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,2), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,15), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,15), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,2), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,4), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,8), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,15), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,16), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,17), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,18), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,19), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,20), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,21), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,22), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,23), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,24), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,35), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,15), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,16), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,17), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,18), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,19), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,20), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,21), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,22), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,23), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,24), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,25), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,26), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,27), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,28), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,29), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,30), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,31), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,32), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,33), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,34), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,35), LocationType.OPEN, false, null, 0, 0));

		
		
		map.addDoor(new Door(false, 2, new Position(25,12), Orientation.VERTICAL));
	}

	@Override
	public void setup() {
		gamal.setCurrentPosition(new Position (22,12));
		percian.setCurrentPosition(new Position (24,13));
		keothi.setCurrentPosition(new Position (23,12));
		travok.setCurrentPosition(new Position (23,13));
		hazel.setCurrentPosition(new Position (24,12));
	}

	@Override
	public boolean isActive(TurnTaker nextParticipant) {
		// The southern zombies aren't activated until someone steps into their chamber.
		if ((z1 == nextParticipant) || (z2 == nextParticipant) || (r6 == nextParticipant) || (r7 == nextParticipant) || (r8 == nextParticipant) ||
				(r9 == nextParticipant) || (r10 == nextParticipant)) {
			if (pcHasSteppedIntoSouthernChamber) {
				return true;
			}
		} else if ((z3 == nextParticipant) || (z4 == nextParticipant) || (r1 == nextParticipant) || (r2 == nextParticipant) || (r3 == nextParticipant) ||
				(r4 == nextParticipant) || (r5 == nextParticipant)) {
			if (runeHasBeenActivated) {
				return true;
			}
		}
		return false;
	}

	public Zombie getZ1() {
		return z1;
	}

	public void setZ1(Zombie z1) {
		this.z1 = z1;
	}

	public Zombie getZ2() {
		return z2;
	}

	public void setZ2(Zombie z2) {
		this.z2 = z2;
	}

	public Zombie getZ3() {
		return z3;
	}

	public void setZ3(Zombie z3) {
		this.z3 = z3;
	}

	public Zombie getZ4() {
		return z4;
	}

	public void setZ4(Zombie z4) {
		this.z4 = z4;
	}

	public ZombieRotter getR1() {
		return r1;
	}

	public void setR1(ZombieRotter r1) {
		this.r1 = r1;
	}

	public ZombieRotter getR2() {
		return r2;
	}

	public void setR2(ZombieRotter r2) {
		this.r2 = r2;
	}

	public ZombieRotter getR3() {
		return r3;
	}

	public void setR3(ZombieRotter r3) {
		this.r3 = r3;
	}

	public ZombieRotter getR4() {
		return r4;
	}

	public void setR4(ZombieRotter r4) {
		this.r4 = r4;
	}

	public ZombieRotter getR5() {
		return r5;
	}

	public void setR5(ZombieRotter r5) {
		this.r5 = r5;
	}

	public ZombieRotter getR6() {
		return r6;
	}

	public void setR6(ZombieRotter r6) {
		this.r6 = r6;
	}

	public ZombieRotter getR7() {
		return r7;
	}

	public void setR7(ZombieRotter r7) {
		this.r7 = r7;
	}

	public ZombieRotter getR8() {
		return r8;
	}

	public void setR8(ZombieRotter r8) {
		this.r8 = r8;
	}

	public ZombieRotter getR9() {
		return r9;
	}

	public void setR9(ZombieRotter r9) {
		this.r9 = r9;
	}

	public ZombieRotter getR10() {
		return r10;
	}

	public void setR10(ZombieRotter r10) {
		this.r10 = r10;
	}

	public DndCharacter getGamal() {
		return gamal;
	}

	public void setGamal(DndCharacter gamal) {
		this.gamal = gamal;
	}

	public DndCharacter getPercian() {
		return percian;
	}

	public void setPercian(DndCharacter percian) {
		this.percian = percian;
	}

	public DndCharacter getKeothi() {
		return keothi;
	}

	public void setKeothi(DndCharacter keothi) {
		this.keothi = keothi;
	}

	public DndCharacter getTravok() {
		return travok;
	}

	public void setTravok(DndCharacter travok) {
		this.travok = travok;
	}

	public DndCharacter getHazel() {
		return hazel;
	}

	public void setHazel(DndCharacter hazel) {
		this.hazel = hazel;
	}

	public boolean isPcHasSteppedIntoSouthernChamber() {
		return pcHasSteppedIntoSouthernChamber;
	}

	public void setPcHasSteppedIntoSouthernChamber(boolean pcHasSteppedIntoSouthernChamber) {
		this.pcHasSteppedIntoSouthernChamber = pcHasSteppedIntoSouthernChamber;
	}

	public boolean isRuneHasBeenActivated() {
		return runeHasBeenActivated;
	}

	public void setRuneHasBeenActivated(boolean runeHasBeenActivated) {
		this.runeHasBeenActivated = runeHasBeenActivated;
	}

	public HashMap<Integer, List<Creature>> getTerrorRuneEffects() {
		return terrorRuneEffects;
	}

	public void setTerrorRuneEffects(HashMap<Integer, List<Creature>> terrorRuneEffects) {
		this.terrorRuneEffects = terrorRuneEffects;
	}

}
