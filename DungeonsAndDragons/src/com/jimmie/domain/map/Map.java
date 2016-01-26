package com.jimmie.domain.map;

import java.util.HashMap;

import com.jimmie.domain.Position;

public class Map {
	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	private int width;
	private int height;
	
	HashMap<Integer, MapLocation> locations;

	public Map() {
		locations = new HashMap<Integer, MapLocation>();
	}
	
	public boolean isDifficultTerrain(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (location != null) {
			return location.isDifficultTerrain();
		} else {
			return false;
		}
	}


	public void addLocation(MapLocation loc) {
		locations.put(getKey(loc.getPosition()), loc);		
	}


	private Integer getKey(Position loc) {
		int key;
		key = ((loc.getX()-1)*height)+loc.getY();
		return (key);
	}


	public boolean isBoulder(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (location != null) {
			if (LocationType.BOULDER == location.getType()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean isGrass(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.GRASS == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isRoad(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.ROAD == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isRubble(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.RUBBLE == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isWall(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.WALL == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isShallowRiver(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.SHALLOW_RIVER == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isDeepRiver(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.DEEP_RIVER == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isFallenTree(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.FALLEN_TREE == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isWaterfall(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.WATERFALL == location.getType()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFoliage(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (location != null) {
			if (LocationType.FOLIAGE == location.getType()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean isTree(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (location != null) {
			if (LocationType.TREE == location.getType()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean providesCover(Position position) {
		/* Right now I only know if it provides cover based on the type. */
		if (isBoulder(position) || isFoliage(position) || isTree(position)) {
			return true;
		} else {
			return false;
		}
	}


	public boolean isSacredCircle(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (LocationType.SACRED_CIRCLE == location.getType()) {
			return true;
		} else {
			return false;
		}
	}
}
