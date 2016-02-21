package com.jimmie.encounters;

import java.util.ArrayList;

import com.jimmie.domain.DurationType;
import com.jimmie.domain.Position;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.domain.creatures.monsters.KoboldSkirmisher;
import com.jimmie.domain.creatures.monsters.KoboldSlinger;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.map.Map;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KoboldLairOutsideEncounter extends Encounter {
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
	private KoboldMinion m6;
	@Autowired
	private KoboldMinion m7;
	@Autowired
	private KoboldMinion m8;
	@Autowired
	private KoboldMinion m9;
	@Autowired
	private KoboldMinion m10;
	@Autowired
	private KoboldSkirmisher k;
	@Autowired
	private KoboldDragonshield d;
	@Autowired
	private KoboldSlinger s;
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
		Encounter.setMonstersVisible(false);
		
		gamal.setCurrentPosition(new Position (-1,-1));
		percian.setCurrentPosition(new Position (-1,-1));
		keothi.setCurrentPosition(new Position (-1,-1));
		travok.setCurrentPosition(new Position (-1,-1));
		hazel.setCurrentPosition(new Position (-1,-1));
		
		/* Set up the monsters */
		monsters = new ArrayList<Monster>();		
		
		monsters.add(m1);		
		monsters.add(m2);		
		monsters.add(m3);		
		monsters.add(m4);		
		monsters.add(m5);		
		monsters.add(m6);		
		monsters.add(m7);		
		monsters.add(m8);		
		monsters.add(m9);
		monsters.add(m10);
		
		monsters.add(k);
		monsters.add(d);
		monsters.add(s);
	
		/* Set up the player characters */
/*		ReadiedWeapon readiedWeapon = new ReadiedWeapon();
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
		readiedWeapon.setWeapon(keothi.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		keothi.addReadiedWeapon(readiedWeapon);
		keothi.setReadiedArmor(keothi.getArmor().get(0));
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
		ReadiedWeapon readiedWeapon = new ReadiedWeapon();
		readiedWeapon.setWeapon(hazel.getWeapons().get(0));
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			readiedWeapon.setHand(Hand.BOTH_HANDS);
		} else {
			readiedWeapon.setHand(Hand.MAIN_HAND);
		}
		hazel.addReadiedWeapon(readiedWeapon);
		hazel.setReadiedArmor(hazel.getArmor().get(0));
*/		

//		gamal.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Gamal.jpg");		
//		gamal.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\GamalBloodied.jpg");		
//		percian.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Percian.JPG");
//		percian.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\PercianBloodied.JPG");
//		keothi.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Keothi.JPG");
//		keothi.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KeothiBloodied.JPG");
//		travok.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Travok.JPG");
//		travok.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\TravokBloodied.JPG");
//		hazel.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Hazel.JPG");
//		hazel.setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\HazelBloodied.JPG");
/*		
		if (Druid.class.isAssignableFrom(hazel.getDndClass().getClass())) {
			((Druid) hazel.getDndClass()).setBeastFormImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GrayWolf.JPG");
			((Druid) hazel.getDndClass()).setBeastFormBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GrayWolfBloodied.JPG");
		}
*/
//		Utils.saveCharacter(gamal);
//		Utils.saveCharacter(percian);
//		Utils.saveCharacter(keothi);
//		Utils.saveCharacter(travok);
//		Utils.saveCharacter(hazel);
		
		
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
		map.addLocation(new MapLocation(new Position(1,1), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(1,2), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(1,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(1,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(1,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(1,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(1,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(1,8), LocationType.GRASS));
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
		map.addLocation(new MapLocation(new Position(1,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,1), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(2,2), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(2,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(2,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(2,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(2,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(2,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(2,8), LocationType.TREE));
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
		map.addLocation(new MapLocation(new Position(2,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,1), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(3,2), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(3,3), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(3,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,8), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(3,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,2), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(4,3), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(4,4), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(4,5), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(4,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,8), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,9), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,10), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,11), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,12), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,13), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,14), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(4,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,3), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(5,4), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(5,5), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(5,6), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(5,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,8), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,9), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,10), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,11), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,12), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,13), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,14), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(5,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,5), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(6,6), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(6,7), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(6,8), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(6,9), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,10), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(6,11), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(6,12), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(6,13), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,14), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,15), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(6,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(6,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,6), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(7,7), LocationType.DEEP_RIVER));
		map.addLocation(new MapLocation(new Position(7,8), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(7,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,10), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(7,11), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(7,12), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(7,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,14), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,15), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,16), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(7,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,4), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,5), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,8), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(8,9), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(8,10), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(8,11), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(8,12), LocationType.SACRED_CIRCLE));
		map.addLocation(new MapLocation(new Position(8,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,15), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,16), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(8,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,9), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(9,10), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(9,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,12), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(9,13), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(9,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,15), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,16), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,20), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(9,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,9), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(10,10), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(10,11), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(10,12), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(10,13), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(10,14), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(10,15), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,16), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(10,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,21), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,2), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,3), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,7), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,8), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,10), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(11,11), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(11,12), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(11,13), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(11,14), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(11,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,16), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(11,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(11,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,2), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(12,3), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(12,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(12,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,10), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(12,11), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(12,12), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(12,13), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(12,14), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(12,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(12,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(12,19), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(12,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(12,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,2), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(13,3), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(13,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(13,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,10), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(13,11), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(13,12), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(13,13), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(13,14), LocationType.FALLEN_TREE));
		map.addLocation(new MapLocation(new Position(13,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(13,18), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(13,19), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(13,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(14,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,10), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(14,11), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(14,12), LocationType.SHALLOW_RIVER));
		map.addLocation(new MapLocation(new Position(14,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(14,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,1), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,2), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,3), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,6), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,9), LocationType.WATERFALL));
		map.addLocation(new MapLocation(new Position(15,10), LocationType.WATERFALL));
		map.addLocation(new MapLocation(new Position(15,11), LocationType.WATERFALL));
		map.addLocation(new MapLocation(new Position(15,12), LocationType.WATERFALL));
		map.addLocation(new MapLocation(new Position(15,13), LocationType.WATERFALL));
		map.addLocation(new MapLocation(new Position(15,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,17), LocationType.TREE));
		map.addLocation(new MapLocation(new Position(15,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(15,21), LocationType.GRASS));
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

	public KoboldMinion getM6() {
		return m6;
	}

	public void setM6(KoboldMinion m6) {
		this.m6 = m6;
	}

	public KoboldMinion getM7() {
		return m7;
	}

	public void setM7(KoboldMinion m7) {
		this.m7 = m7;
	}

	public KoboldMinion getM8() {
		return m8;
	}

	public void setM8(KoboldMinion m8) {
		this.m8 = m8;
	}

	public KoboldMinion getM9() {
		return m9;
	}

	public void setM9(KoboldMinion m9) {
		this.m9 = m9;
	}

	public KoboldMinion getM10() {
		return m10;
	}

	public void setM10(KoboldMinion m10) {
		this.m10 = m10;
	}
	

	public KoboldSkirmisher getK() {
		return k;
	}

	public void setK(KoboldSkirmisher k) {
		this.k = k;
	}

	public KoboldDragonshield getD() {
		return d;
	}

	public void setD(KoboldDragonshield d) {
		this.d = d;
	}

	public KoboldSlinger getS() {
		return s;
	}

	public void setS(KoboldSlinger s) {
		this.s = s;
	}

	public DndCharacter getGamal() {
		return gamal;
	}

	public void setGamal(DndCharacter gamal) {
		this.gamal = gamal;
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

	public DndCharacter getPercian() {
		return percian;
	}

	public void setPercian(DndCharacter percian) {
		this.percian = percian;
	}

	@Override
	public void setup() {
		// Put the characters on the road.
		Utils.print("Place characters in the clearing on the left hand edge of the map. Take into consideration whether or not");
		Utils.print("the PC's will want to sneak attack.");
		
		boolean allStealthChecksSucceeded = true;
		
		for (DndCharacter character : getCharacters()) {
			Utils.print("Where do you want to put " + character.getName() + "?");

			Encounter.showCoordinateSystem(true);
			
			Utils.print("Please enter the X coordinate (1 - 8).");
			int x = Utils.getValidIntInputInRange(1, 8);

			Utils.print("Please enter the Y coordinate (1 - 21).");
			int y = Utils.getValidIntInputInRange(1, 21);
			Encounter.showCoordinateSystem(false);
			
			character.setCurrentPosition(new Position(x,y));
			
			Utils.print("Perception Check:");
			SkillCheck skillCheck = new SkillCheck();
			skillCheck.setDifficultyClass(13);
			skillCheck.setSkillType(SkillType.PERCEPTION);
			int roll = character.skillCheckRoll(skillCheck);
			if (roll >= 15) {
				Utils.print("Several kobolds are visible through the trees.");
				Encounter.setMonstersVisible(true);
			} else if (roll >= 13) {
				Utils.print("The sound of many voices is just audible over the din of the waterfall.");
			}
			
			Utils.print("Stealth Check:");
			skillCheck = new SkillCheck();
			skillCheck.setDifficultyClass(12);
			skillCheck.setSkillType(SkillType.STEALTH);
			if (!character.performSkillCheck(skillCheck)) {
				Utils.print(character.getName() + " was a little too noisy and the enemies are alerted to your presence.");
				allStealthChecksSucceeded = false;
			}
		}
		
		if (allStealthChecksSucceeded) {
			// Surprise round.
			m1.setTemporaryCondition(m1, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m1.getCurrentTurn());
			m2.setTemporaryCondition(m2, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m2.getCurrentTurn());
			m3.setTemporaryCondition(m3, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m3.getCurrentTurn());
			m4.setTemporaryCondition(m4, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m4.getCurrentTurn());
			m5.setTemporaryCondition(m5, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m5.getCurrentTurn());
			m6.setTemporaryCondition(m6, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m6.getCurrentTurn());
			m7.setTemporaryCondition(m7, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m7.getCurrentTurn());
			m8.setTemporaryCondition(m8, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m8.getCurrentTurn());
			m9.setTemporaryCondition(m9, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m9.getCurrentTurn());
			m10.setTemporaryCondition(m10, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, m10.getCurrentTurn());
			k.setTemporaryCondition(k, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, k.getCurrentTurn());
			d.setTemporaryCondition(d, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, d.getCurrentTurn());
			s.setTemporaryCondition(s, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, s.getCurrentTurn());
		}
	}

	@Override
	protected void makeEncounterInitiativeChanges() {
		// The dragonshield and skirmisher are supposed to have an initiative 1 lower than the minion's initiative.
		d.setInitiativeRoll(m1.getInitiativeRoll()-2); // Setting dragonshield to -2 because they have a +4 initiative while the minions have +3.  So I have to subtract 2 to get a total roll that's 1 less.
		k.setInitiativeRoll(m1.getInitiativeRoll()-3); // Setting skirmisher to -3 because they have a +5 initiative while the minions have +3.  So I have to subtract 2 to get a total roll that's 1 less.
		
	}

	@Override
	public boolean isActive(TurnTaker nextParticipant) {
		return true;
	}
}
