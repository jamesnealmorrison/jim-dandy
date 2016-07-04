package com.jimmie.encounters;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.jimmie.domain.Position;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.domain.creatures.monsters.KoboldSlinger;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.map.Map;
import com.jimmie.powers.KoboldMinionJavelin;
import com.jimmie.powers.KoboldMinionSpear;
import com.jimmie.util.Utils;

public class OnTheRoadKoboldBrigandsEncounter extends Encounter {
	@Autowired
	private KoboldMinion m1;
	@Autowired
	private KoboldMinion m2;
	@Autowired
	private KoboldMinion m3;
	@Autowired
	private KoboldMinion m4;
	@Autowired
	private KoboldMinion m5;
	@Autowired
	private KoboldSlinger s;
	@Autowired
	private KoboldDragonshield d1;
	@Autowired
	private KoboldDragonshield d2;

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
	
	@Override
	public void init() {
		Encounter.setMonstersActive(false);
		Encounter.setMonstersVisible(false);
		Encounter.setCharactersVisible(true);
		Encounter.setCharactersActive(true);
		
		// Set up monsters
		monsters = new ArrayList<Monster>();
		
		m1.inializeForEncounter(3);
		m2.inializeForEncounter(3);
		m3.inializeForEncounter(3);
		m4.inializeForEncounter(3);
		m5.inializeForEncounter(3);
		monsters.add(m1);
		monsters.add(m2);
		monsters.add(m3);
		monsters.add(m4);
		monsters.add(m5);
		s.initializeForEncounter(20, 2, 1, 0);
		monsters.add(s);
		
		monsters.add(d1);
		monsters.add(d2);
		
		// Set up the player characters
/*		gamal.setCurrentPosition(new Position(13,13));
		percian.setCurrentPosition(new Position(13,14));
		keothi.setCurrentPosition(new Position(13,15));
		travok.setCurrentPosition(new Position(14,13));
		hazel.setCurrentPosition(new Position(14,14));
		ReadiedWeapon readiedWeapon = new ReadiedWeapon();
		readiedWeapon.setWeapon(keothi.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		keothi.addReadiedWeapon(readiedWeapon);
		keothi.setReadiedArmor(keothi.getArmor().get(0));
		Utils.saveCharacter(keothi);

		readiedWeapon = new ReadiedWeapon();
		readiedWeapon.setWeapon(travok.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		travok.addReadiedWeapon(readiedWeapon);
		
		travok.setReadiedArmor(travok.getArmor().get(0));
		
		for (Gear gear : travok.getGear()) {
			if (HolySymbol.class.isAssignableFrom(gear.getClass())) {
				travok.setReadiedImplement((Implement) gear);
			}
		}

		readiedWeapon = new ReadiedWeapon();
		readiedWeapon.setWeapon(gamal.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		gamal.addReadiedWeapon(readiedWeapon);
		gamal.setReadiedArmor(gamal.getArmor().get(0));

		readiedWeapon = new ReadiedWeapon();
		readiedWeapon.setWeapon(percian.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		percian.addReadiedWeapon(readiedWeapon);
		percian.setReadiedArmor(percian.getArmor().get(0));

		readiedWeapon = new ReadiedWeapon();
		readiedWeapon.setWeapon(hazel.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		hazel.addReadiedWeapon(readiedWeapon);
		hazel.setReadiedArmor(hazel.getArmor().get(0));
		
		gamal.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Gamal.jpg");		
		gamal.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\GamalBloodied.jpg");		
		percian.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Percian.JPG");
		percian.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\PercianBloodied.JPG");
		keothi.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Keothi.JPG");
		keothi.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KeothiBloodied.JPG");
		travok.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Travok.JPG");
		travok.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\TravokBloodied.JPG");
		hazel.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Hazel.JPG");
		hazel.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\HazelBloodied.JPG");
		
		if (Druid.class.isAssignableFrom(hazel.getDndClass().getClass())) {
			((Druid) hazel.getDndClass()).setBeastFormImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GrayWolf.JPG");
			((Druid) hazel.getDndClass()).setBeastFormBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GrayWolfBloodied.JPG");
		}
		
		
		gamal.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Gamal.jpg");		
		percian.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Percian.jpg");		
		keothi.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Keothi.jpg");		
		travok.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Travok.jpg");		
		hazel.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Hazel.jpg");		

		gamal.setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\GamalBattleCard.jpg");		
		percian.setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\PercianBattleCard.jpg");		
		keothi.setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KeothiBattleCard.jpg");		
		travok.setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\TravokBattleCard.jpg");		
		hazel.setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\HazelBattleCard.jpg");		

		
		if (Warden.class.isAssignableFrom(keothi.getDndClass().getClass())) {
			((Warden) keothi.getDndClass()).setWillowSentinelImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\WillowSentinel.JPG");
			((Warden) keothi.getDndClass()).setWillowSentinelBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\WillowSentinelBloodied.JPG");
		}

		
		gamal.getDndClass().setOwner(gamal);
		percian.getDndClass().setOwner(percian);
		keothi.getDndClass().setOwner(keothi);
		travok.getDndClass().setOwner(travok);
		hazel.getDndClass().setOwner(hazel);

		if (PlayerCharacter.class.isAssignableFrom(gamal.getClass())) {
			((PlayerCharacter) gamal).setExperiencePoints(95);
			gamal.addCoins(7, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(percian.getClass())) {
			((PlayerCharacter) percian).setExperiencePoints(95);
			percian.addCoins(7, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(keothi.getClass())) {
			((PlayerCharacter) keothi).setExperiencePoints(95);
			keothi.addCoins(7, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(travok.getClass())) {
			((PlayerCharacter) travok).setExperiencePoints(95);
			travok.addCoins(7, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(hazel.getClass())) {
			((PlayerCharacter) hazel).setExperiencePoints(95);
			hazel.addCoins(7, CoinType.SILVER_PIECE);
		}
		
Utils.saveCharacter(gamal);
Utils.saveCharacter(percian);
Utils.saveCharacter(keothi);
Utils.saveCharacter(travok);
Utils.saveCharacter(hazel);
*/
		// Just to be certain, print off all the characters stats.
		Utils.print("Keothi reflex = " + keothi.getReflex(null));
		Utils.print("Keothi will = " + keothi.getWill(null));
		Utils.print("Keothi fortitude = " + keothi.getFortitude(null));
		Utils.print("Keothi ac = " + keothi.getArmorClass(null));
		Utils.print("Hazel reflex = " + hazel.getReflex(null));
		Utils.print("Hazel will = " + hazel.getWill(null));
		Utils.print("Hazel fortitude = " + hazel.getFortitude(null));
		Utils.print("Hazel ac = " + hazel.getArmorClass(null));
		Utils.print("Travok reflex = " + travok.getReflex(null));
		Utils.print("Travok will = " + travok.getWill(null));
		Utils.print("Travok fortitude = " + travok.getFortitude(null));
		Utils.print("Travok ac = " + travok.getArmorClass(null));
		Utils.print("Percian reflex = " + percian.getReflex(null));
		Utils.print("Percian will = " + percian.getWill(null));
		Utils.print("Percian fortitude = " + percian.getFortitude(null));
		Utils.print("Percian ac = " + percian.getArmorClass(null));
		Utils.print("Gamal reflex = " + gamal.getReflex(null));
		Utils.print("Gamal will = " + gamal.getWill(null));
		Utils.print("Gamal fortitude = " + gamal.getFortitude(null));
		Utils.print("Gamal ac = " + gamal.getArmorClass(null));
		

		characters = new ArrayList<DndCharacter>();
		characters.add(gamal);
		characters.add(percian);
		characters.add(keothi);
		characters.add(travok);
		characters.add(hazel);

		map = new Map();
		map.setWidth(15);
		map.setHeight(21);
		/* Commenting out.  If I want to run this encounter again, I'll have to refactor all this.		
		map.addLocation(new MapLocation(new Position(1,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,6), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(1,7), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(1,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(1,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(2,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,6), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(2,7), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(2,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(2,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,7), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(3,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(3,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(3,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,7), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,13), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,14), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,19), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,2), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(5,3), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(5,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(5,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(5,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(5,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,12), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,13), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,19), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,2), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(6,3), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(6,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(6,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(6,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(6,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(6,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,19), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(7,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(7,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(7,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(7,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,19), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(8,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(8,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(8,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(8,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,16), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(8,17), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(8,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(9,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(9,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(9,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(9,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,16), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(9,17), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(9,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,9), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(10,10), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(10,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(10,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(10,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(10,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,9), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(11,10), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(11,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(11,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(11,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(11,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(11,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(12,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(12,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(12,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,16), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(13,17), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(13,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(14,2), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(14,3), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(14,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(14,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(14,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(14,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(14,16), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(14,17), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(14,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,2), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,3), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,12), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(15,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(15,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(15,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(15,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,21), LocationType.TREE));
*/		
	}

	public KoboldMinion getM1() {
		return m1;
	}

	public void setM1(KoboldMinion m1) {
		this.m1 = m1;
	}

	public KoboldMinion getM2() {
		return m2;
	}

	public void setM2(KoboldMinion m2) {
		this.m2 = m2;
	}

	public KoboldMinion getM3() {
		return m3;
	}

	public void setM3(KoboldMinion m3) {
		this.m3 = m3;
	}

	public KoboldMinion getM4() {
		return m4;
	}

	public void setM4(KoboldMinion m4) {
		this.m4 = m4;
	}

	public KoboldMinion getM5() {
		return m5;
	}

	public void setM5(KoboldMinion m5) {
		this.m5 = m5;
	}

	public KoboldSlinger getS() {
		return s;
	}

	public void setS(KoboldSlinger s) {
		this.s = s;
	}

	public KoboldDragonshield getD1() {
		return d1;
	}

	public void setD1(KoboldDragonshield d1) {
		this.d1 = d1;
	}

	public KoboldDragonshield getD2() {
		return d2;
	}

	public void setD2(KoboldDragonshield d2) {
		this.d2 = d2;
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

	@Override
	public void setup() {
		// Put the characters on the road.
		Utils.print("Place characters on the road within 2 squares of the eastern edge of the map.");
		
		for (DndCharacter character : getCharacters()) {
			Utils.print("Where do you want to put " + character.getName() + "?");

			Encounter.showCoordinateSystem(true);
			
			Utils.print("Please enter the X coordinate (13 - 15).");
			int x = Utils.getValidIntInputInRange(13, 15);

			Utils.print("Please enter the Y coordinate (13 - 15).");
			int y = Utils.getValidIntInputInRange(13, 15);
			Encounter.showCoordinateSystem(false);
			
			character.setCurrentPosition(new Position(x,y));
		}
		
		// Now let the characters do two rounds of walking west along the road.
		Utils.print("Now, each character will be given two rounds of walking along the road towards the west.  (I'm not validating that your moving west, but just do it.)");
		// The book is confusing here.  It says to let them move for two rounds.  Then it mentions the minions attacking when one of the creatures passes a certain point.
		// But that certain point is very far to the right.  So I'm just going to let all the characters move once and then start the attack.
		for (DndCharacter character : getCharacters()) {
			character.startOfTurn(); // To reinitialize their move actions.
			Utils.print("Time to move " + character.getName());
			character.useMoveAction();
		}
		
		Utils.print("The wind in your face is cool and comfortable.  The road beneath your feet is level.  An occasional ancient cobblestone ");
		Utils.print("peeks through the dirt road, indicating decades of neglect.  You notice footprints leading up and down the road, many of");
		Utils.print("which were made by small, clawed feet.");
		
		Utils.print("Small creatures hiding behind the rocks spring into view and begin to move toward you.  With a shriek, the small humanoids");
		Utils.print("attack.  Scaled and rust-colored, they have reptilian heads and tails.");
		Encounter.setMonstersActive(true);
		Encounter.setMonstersVisible(true);
		
		// Setup monster values that can change for different encounters.
		KoboldMinionJavelin.setAttackModifier(4);
		KoboldMinionSpear.setAttackModifier(4);
		
	}

	@Override
	protected void makeEncounterInitiativeChanges() {
		// The dragonshields and slinger are supposed to have an initiative 1 lower than the minion's initiative.
		d1.setInitiativeRoll(m1.getInitiativeRoll()-2); // Setting dragonshield to -2 because they have a +4 initiative while the minions have +3.  So I have to subtract 2 to get a total roll that's 1 less.
		d2.setInitiativeRoll(m1.getInitiativeRoll()-2); // Setting dragonshield to -2 because they have a +4 initiative while the minions have +3.  So I have to subtract 2 to get a total roll that's 1 less.
		s.setInitiativeRoll(m1.getInitiativeRoll()-1);
	}

	@Override
	public boolean isActive(TurnTaker nextParticipant) {
		return true;
	}
}
