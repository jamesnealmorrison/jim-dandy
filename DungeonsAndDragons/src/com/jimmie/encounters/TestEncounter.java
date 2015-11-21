package com.jimmie.encounters;

import com.jimmie.domain.Position;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.Human;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldSkirmisher;
import com.jimmie.domain.items.armor.ClothArmor;
import com.jimmie.domain.items.armor.NoShield;
import com.jimmie.domain.items.weapons.Mace;
import com.jimmie.domain.map.LocationType;
import com.jimmie.domain.map.Map;
import com.jimmie.domain.map.MapLocation;

public class TestEncounter extends Encounter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestEncounter encounter = new TestEncounter();
		
		encounter.run();
	}

	public void run() {
		/* Set up the monsters */
		KoboldSkirmisher k = new KoboldSkirmisher();
		k.setName("Kobold Skirmisher");
		k.setCurrentPosition(new Position(4,5));
		KoboldDragonshield d1 = new KoboldDragonshield();
		d1.setName("Kobold Dragonshield 1");
		d1.setCurrentPosition(new Position(5,4));
		monsters.add(k);
		monsters.add(d1);
	
		Human human = new Human();
		Psion psion = new Psion();
		psion.addPower(PowerId.KINETIC_TRAWL);
		psion.addPower(PowerId.MEMORY_HOLE);
		psion.addPower(PowerId.FORCEFUL_PUSH);
		psion.addPower(PowerId.TELEKINETIC_ANCHOR);
		PlayerCharacter zanros = new PlayerCharacter(human, psion);
		psion.setOwner(zanros);
		human.setOwner(zanros);
		zanros.setName("Zanros Hawklight");
		zanros.setInitiative(30); 
		zanros.setMaxHitPoints(25);
		zanros.setCurrentHitPoints(25);
		zanros.setFortitude(12);
		zanros.setReflex(15);
		zanros.setWill(15);
		zanros.setSpeed(6);
		zanros.setStrength(12);
		zanros.setConstitution(13);
		zanros.setDexterity(10);
		zanros.setIntelligence(18);
		zanros.setWisdom(14);
		zanros.setCharisma(11);
		zanros.setReadiedWeapon(new Mace());
		zanros.setReadiedShield(new NoShield());
		zanros.setCurrentPosition(new Position(1,2));
		zanros.addArmor(new ClothArmor());
		zanros.setReadiedArmor(zanros.getArmor().get(0));
		zanros.setHealingSurgesPerDay(7);
		zanros.setHealingSurgeValue(6);
//		zanros.setTrainedInArcana(true);
//		zanros.setTrainedInDungeoneering(true);
//		zanros.setTrainedInInsight(true);
//		zanros.setTrainedInIntimidate(true);
//		zanros.setTrainedInPerception(true);
		
		characters.add(zanros);
		
		map = new Map();
		map.setWidth(5);
		map.setHeight(6);
		map.addLocation(new MapLocation(new Position(1,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,6), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(2,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,6), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(3,1), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(3,2), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(3,3), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(3,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,6), LocationType.GRASS));
		
		runEncounter();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
