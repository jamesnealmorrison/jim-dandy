package com.jimmie.domain.map;

import com.jimmie.domain.Position;

public class MapLocation {
	private Position position;
	private LocationType type;
	
	public MapLocation(Position p, LocationType t) {
		setPosition(p);
		setType(t);
	}

	public boolean isDifficultTerrain() {
/* The trees in encounter "KoboldLairOutside" do not count as difficult terrain.
 		if (type == LocationType.TREE) {
			return true;
		} else */ 
			if (type == LocationType.RUBBLE) {
			return true;
		} else if (type == LocationType.FOLIAGE) {
			return true;
		} else if (type == LocationType.BOULDER) {
			return true;
		} else if (type == LocationType.DEEP_RIVER) {
			return true;
		} else {
			return false;
		}
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public LocationType getType() {
		return type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}
}
