package com.jimmie.domain.map;

import com.jimmie.domain.Position;
import com.jimmie.util.SkillCheck;

public class MapLocation {
	private Position position;
	private LocationType locationType; // This tells if the square can be entered at all (e.g. wall vs non-wall)
	private boolean difficultTerrain;
	private SkillCheck skillCheckToEnter;
	int extraMovementCostToEnter;
	int height; // 0 represents the normal level for that map.  (in feet)
	
	
	public MapLocation(Position position, LocationType locationType, boolean difficultTerrain, SkillCheck skillCheckToEnter, int extraMovementCostToEnter, int height) {
		setPosition(position);
		setLocationType(locationType);
		setDifficultTerrain(difficultTerrain);
		setSkillCheckToEnter(skillCheckToEnter);
		setExtraMovementCostToEnter(extraMovementCostToEnter);
		setHeight(height);
	}


	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public SkillCheck getSkillCheckToEnter() {
		return skillCheckToEnter;
	}

	public void setSkillCheckToEnter(SkillCheck skillCheckToEnter) {
		this.skillCheckToEnter = skillCheckToEnter;
	}

	public int getExtraMovementCostToEnter() {
		return extraMovementCostToEnter;
	}

	public void setExtraMovementCostToEnter(int extraMovementCostToEnter) {
		this.extraMovementCostToEnter = extraMovementCostToEnter;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isDifficultTerrain() {
		return difficultTerrain;
	}

	public void setDifficultTerrain(boolean difficultTerrain) {
		this.difficultTerrain = difficultTerrain;
	}
}
