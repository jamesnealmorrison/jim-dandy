package com.jimmie.encounters;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.Position;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldSkirmisher;
import com.jimmie.domain.creatures.monsters.KoboldWyrmpriest;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.map.Map;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.Utils;

public class KoboldAmbushEncounter extends Encounter {
	@Autowired
	private KoboldSkirmisher k;
	@Autowired
	private KoboldDragonshield d1;
	@Autowired
	private KoboldDragonshield d2;
	@Autowired
	private KoboldDragonshield d3;
	@Autowired
	private KoboldWyrmpriest w;
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
		showCoordinateSystem(true);
		Encounter.setMonstersActive(false);
		Encounter.setMonstersVisible(false);
		Encounter.setCharactersVisible(true);
		Encounter.setCharactersActive(true);

		// Set up monsters
		monsters = new ArrayList<Monster>();
		monsters.add(k);
		monsters.add(d1);
		monsters.add(d2);
		monsters.add(d3);
		monsters.add(w);

		characters = new ArrayList<DndCharacter>();
		characters.add(gamal);
		characters.add(percian);
		characters.add(keothi);
		characters.add(travok);
		characters.add(hazel);

		map = new Map();
		map.setWidth(30);
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
		map.addLocation(new MapLocation(new Position(1,21), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(2,21), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(3,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(3,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(3,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(4,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,7), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(4,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,13), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(4,14), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(4,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(4,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(4,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(4,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(4,21), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(5,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(5,13), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(5,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(5,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(5,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(5,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(5,21), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(6,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(6,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(6,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(6,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(7,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(7,7), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(7,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(7,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(7,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(8,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(8,6), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(8,7), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(8,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(8,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(9,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(9,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(9,5), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(9,6), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(10,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(10,5), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(10,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(10,9), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(10,10), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(10,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(10,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(10,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(10,14), LocationType.ROAD));
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
		map.addLocation(new MapLocation(new Position(13,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(13,16), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(13,17), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(13,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(13,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(14,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(14,2), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(14,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(14,4), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(14,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(15,1), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(15,2), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(15,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(15,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(15,5), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(15,6), LocationType.FOLIAGE));
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
		map.addLocation(new MapLocation(new Position(15,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(15,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,1), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(16,2), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(16,3), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(16,4), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(16,5), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(16,6), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,7), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,8), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(16,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(16,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(16,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(16,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(16,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(16,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(16,16), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,17), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(16,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(16,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(16,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,1), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(17,2), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(17,3), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(17,4), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(17,5), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,6), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,7), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,8), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(17,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(17,11), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(17,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(17,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(17,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(17,17), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(17,21), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(18,1), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(18,2), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(18,3), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(18,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(18,5), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(18,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(18,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(18,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(18,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(18,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(18,11), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(18,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(18,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(18,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(18,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(18,16), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(18,17), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(18,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(18,19), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(18,20), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(18,21), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(19,1), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(19,2), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(19,3), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(19,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(19,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(19,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(19,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(19,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(19,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(19,11), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,13), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(19,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(19,16), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(19,17), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,18), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(19,20), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(19,21), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(20,1), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(20,2), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(20,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(20,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(20,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(20,8), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,9), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,10), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,11), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,13), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(20,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(20,16), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(20,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(20,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(20,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(20,21), LocationType.WALL));
		map.addLocation(new MapLocation(new Position(21,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,2), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,9), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,10), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,11), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(21,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(21,16), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(21,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(21,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(21,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,2), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,10), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,11), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,12), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(22,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(22,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(22,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(22,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(22,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(23,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(23,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,4), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(23,5), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(23,6), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(23,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,11), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(23,12), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(23,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(23,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(23,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(23,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(23,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(24,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,11), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(24,12), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(24,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(24,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(24,15), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(24,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(24,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(25,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(25,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(25,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(25,15), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(25,16), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(25,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(25,21), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(26,2), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(26,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,10), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(26,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(26,13), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(26,14), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(26,15), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(26,16), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(26,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(26,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(27,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(27,2), LocationType.FOLIAGE));		
		map.addLocation(new MapLocation(new Position(27,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(27,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,7), LocationType.BOULDER));
		map.addLocation(new MapLocation(new Position(27,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,9), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(27,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(27,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(27,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,14), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(27,15), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(27,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,20), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(27,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(28,1), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(28,2), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(28,3), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(28,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,8), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(28,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(28,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(28,12), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(28,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(28,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(28,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(29,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(29,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(29,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(29,11), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(29,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,19), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(29,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(29,21), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(30,1), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,2), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,3), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,4), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,5), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,6), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,7), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,8), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(30,9), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(30,10), LocationType.ROAD));
		map.addLocation(new MapLocation(new Position(30,11), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,12), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,13), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,14), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,15), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,16), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,17), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,18), LocationType.GRASS));
		map.addLocation(new MapLocation(new Position(30,19), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(30,20), LocationType.FOLIAGE));
		map.addLocation(new MapLocation(new Position(30,21), LocationType.FOLIAGE));
		*/		
	}	

	@Override
	public void setup() {
/*		
		if (PlayerCharacter.class.isAssignableFrom(gamal.getClass())) {
			((PlayerCharacter) gamal).setExperiencePoints(220);
			gamal.addCoins(1, CoinType.GOLD_PIECE);
			gamal.addCoins(4, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(percian.getClass())) {
			((PlayerCharacter) percian).setExperiencePoints(220);
			percian.addCoins(1, CoinType.GOLD_PIECE);
			percian.addCoins(4, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(keothi.getClass())) {
			((PlayerCharacter) keothi).setExperiencePoints(220);
			keothi.addCoins(1, CoinType.GOLD_PIECE);
			keothi.addCoins(4, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(travok.getClass())) {
			((PlayerCharacter) travok).setExperiencePoints(220);
			travok.addCoins(1, CoinType.GOLD_PIECE);
			travok.addCoins(5, CoinType.SILVER_PIECE);
		}
		if (PlayerCharacter.class.isAssignableFrom(hazel.getClass())) {
			((PlayerCharacter) hazel).setExperiencePoints(220);
			hazel.addCoins(1, CoinType.GOLD_PIECE);
			hazel.addCoins(5, CoinType.SILVER_PIECE);
		}
		
Utils.saveCharacter(gamal);
Utils.saveCharacter(percian);
Utils.saveCharacter(keothi);
Utils.saveCharacter(travok);
Utils.saveCharacter(hazel);
*/		
		// Put the characters on the road.
		Utils.print("Place characters on the road at the western side of the map.");
		
		for (DndCharacter character : getCharacters()) {
			Utils.print("Where do you want to put " + character.getName() + "?");

			Encounter.showCoordinateSystem(true);
			
			Utils.print("Please enter the X coordinate (8 - 11).");
			int x = Utils.getValidIntInputInRange(8, 11);

			Utils.print("Please enter the Y coordinate (11 - 14).");
			int y = Utils.getValidIntInputInRange(11, 14);
			Encounter.showCoordinateSystem(false);
			
			character.setCurrentPosition(new Position(x,y));
			
			Utils.print("Now, doing a perception check.");
			SkillCheck skillCheck = new SkillCheck();
			skillCheck.setDifficultyClass(25);
			skillCheck.setSkillType(SkillType.PERCEPTION);
			if (!character.performSkillCheck(skillCheck)) {
				character.setTemporaryCondition(character, DurationType.END_OF_NEXT_TURN, CreatureConditionType.SURPRISED, TemporaryEffectReason.SURPRISE_ROUND, character.getCurrentTurn());
			}
		}
		
		Utils.print("The wind in your face is cool and comfortable.  The road beneath your feet is level.  An occasional ancient cobblestone ");
		Utils.print("peeks through the dirt road, indicating decades of neglect.  You notice footprints leading up and down the road, many of");
		Utils.print("which were made by small, clawed feet.");
		
		Utils.print("Small creatures hiding behind the rocks spring into view and begin to move toward you.  With a shriek, the small humanoids");
		Utils.print("attack.  Scaled and rust-colored, they have reptilian heads and tails.");
		Encounter.setMonstersActive(true);
		Encounter.setMonstersVisible(true);
	}

	@Override
	protected void makeEncounterInitiativeChanges() {
		// TODO Auto-generated method stub
		
	}

	public KoboldSkirmisher getK() {
		return k;
	}

	public void setK(KoboldSkirmisher k) {
		this.k = k;
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

	public KoboldDragonshield getD3() {
		return d3;
	}

	public void setD3(KoboldDragonshield d3) {
		this.d3 = d3;
	}

	public KoboldWyrmpriest getW() {
		return w;
	}

	public void setW(KoboldWyrmpriest w) {
		this.w = w;
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
	public boolean isActive(TurnTaker nextParticipant) {
		return true;
	}
}
