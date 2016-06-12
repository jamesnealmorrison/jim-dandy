package com.jimmie.encounters;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.jimmie.domain.IlluminationType;
import com.jimmie.domain.Position;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.DecrepitSkeleton;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.creatures.monsters.SkeletonWarrior;
import com.jimmie.domain.map.Door;
import com.jimmie.domain.map.LocationType;
import com.jimmie.domain.map.Map;
import com.jimmie.domain.map.MapLocation;
import com.jimmie.domain.map.Orientation;

public class SkeletalLegionEncounter extends Encounter {

	@Autowired
	private SkeletonWarrior w1;
	@Autowired
	private SkeletonWarrior w2;
	@Autowired
	private DecrepitSkeleton s1;
	@Autowired
	private DecrepitSkeleton s2;
	@Autowired
	private DecrepitSkeleton s3;
	@Autowired
	private DecrepitSkeleton s4;
	@Autowired
	private DecrepitSkeleton s5;
	@Autowired
	private DecrepitSkeleton s6;
	@Autowired
	private DecrepitSkeleton s7;
	@Autowired
	private DecrepitSkeleton s8;

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
	protected void makeEncounterInitiativeChanges() {
	}

	@Override
	public void init() {
		gamal.setCurrentPosition(new Position (-1,-1));
		percian.setCurrentPosition(new Position (-1,-1));
		keothi.setCurrentPosition(new Position (-1,-1));
		travok.setCurrentPosition(new Position (-1,-1));
		hazel.setCurrentPosition(new Position (-1,-1));

		// Set up the monsters
		monsters = new ArrayList<Monster>();		
		monsters.add(w1);
		monsters.add(w2);
		monsters.add(s1);
		monsters.add(s2);
		monsters.add(s3);
		monsters.add(s4);
		monsters.add(s5);
		monsters.add(s6);
		monsters.add(s7);
		monsters.add(s8);

		characters = new ArrayList<DndCharacter>();
		characters.add(gamal);
		characters.add(percian);
		characters.add(keothi);
		characters.add(travok);
		characters.add(hazel);
		
		map = new Map();
		map.setIllumination(IlluminationType.DIM_LIGHT);
		map.setWidth(25);
		map.setHeight(14);
		
		//Position position, LocationType locationType, boolean difficultTerrain, SkillCheck skillCheckToEnter, int extraMovementCostToEnter, int height
		map.addLocation(new MapLocation(new Position(1,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,7), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,8), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(1,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(2,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(3,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(4,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(5,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(6,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(7,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(8,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(9,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(10,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(11,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(12,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(13,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(14,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,6), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,9), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(15,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(16,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,2), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,4), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(17,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(18,1), LocationType.OBSTACLE, false, null, 0, 0));
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
		map.addLocation(new MapLocation(new Position(19,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(19,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(20,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,5), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,10), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(21,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,1), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,2), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,3), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,4), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,11), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,12), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,13), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(22,14), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(23,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(24,14), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,1), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,2), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,3), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,4), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,5), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,6), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,7), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,8), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,9), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,10), LocationType.OBSTACLE, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,11), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,12), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,13), LocationType.OPEN, false, null, 0, 0));
		map.addLocation(new MapLocation(new Position(25,14), LocationType.OPEN, false, null, 0, 0));

		map.addDoor(new Door(false, 4, new Position(24,6), Orientation.VERTICAL));
	}
		
	@Override
	public void setup() {
		gamal.setCurrentPosition(new Position (6,12));
		percian.setCurrentPosition(new Position (6,13));
		keothi.setCurrentPosition(new Position (6,14));
		travok.setCurrentPosition(new Position (7,12));
		hazel.setCurrentPosition(new Position (7,13));
	}

	@Override
	public boolean isActive(TurnTaker nextParticipant) {
		return true;
	}
	
	public SkeletonWarrior getW1() {
		return w1;
	}

	public void setW1(SkeletonWarrior monster) {
		this.w1 = monster;
	}

	public SkeletonWarrior getW2() {
		return w2;
	}

	public void setW2(SkeletonWarrior monster) {
		this.w2 = monster;
	}

	public DecrepitSkeleton getS1() {
		return s1;
	}

	public void setS1(DecrepitSkeleton monster) {
		this.s1 = monster;
	}

	public DecrepitSkeleton getS2() {
		return s2;
	}

	public void setS2(DecrepitSkeleton monster) {
		this.s2 = monster;
	}

	public DecrepitSkeleton getS3() {
		return s3;
	}

	public void setS3(DecrepitSkeleton monster) {
		this.s3 = monster;
	}

	public DecrepitSkeleton getS4() {
		return s4;
	}

	public void setS4(DecrepitSkeleton monster) {
		this.s4 = monster;
	}

	public DecrepitSkeleton getS5() {
		return s5;
	}

	public void setS5(DecrepitSkeleton monster) {
		this.s5 = monster;
	}

	public DecrepitSkeleton getS6() {
		return s6;
	}

	public void setS6(DecrepitSkeleton monster) {
		this.s6 = monster;
	}

	public DecrepitSkeleton getS7() {
		return s7;
	}

	public void setS7(DecrepitSkeleton monster) {
		this.s7 = monster;
	}

	public DecrepitSkeleton getS8() {
		return s8;
	}

	public void setS8(DecrepitSkeleton monster) {
		this.s8 = monster;
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

}
