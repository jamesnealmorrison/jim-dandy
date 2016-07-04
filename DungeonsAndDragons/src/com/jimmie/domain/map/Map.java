package com.jimmie.domain.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.AttackType;
import com.jimmie.domain.IlluminationType;
import com.jimmie.domain.LightSource;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class Map {

	private int width;
	private int height;
	private HashMap<Integer, MapLocation> locations;
	private List<Door> doors;
	private IlluminationType illumination;
	private List<LightSource> lightSources;

	public Map() {
		locations = new HashMap<Integer, MapLocation>();
		doors = new ArrayList<Door>();
		lightSources = new ArrayList<LightSource>();
	}
	
	public boolean isDifficultTerrain(Position position) {
		MapLocation location = locations.get(getKey(position));
		if (location != null) {
			return location.isDifficultTerrain();
		} else {
			return false;
		}
	}

	public boolean isLightlyObscured(Position position) {
		MapLocation location = locations.get(getKey(position));
		if ((location != null) && (location.getLocationType() == LocationType.LIGHTLY_OBSCURED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isHeavilyObscured(Position position) {
		MapLocation location = locations.get(getKey(position));
		if ((location != null) && (location.getLocationType() == LocationType.HEAVILY_OBSCURED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isTotallyObscured(Position position) {
		MapLocation location = locations.get(getKey(position));
		if ((location != null) && (location.getLocationType() == LocationType.TOTALLY_OBSCURED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOpenSquare(Position position) {
		MapLocation location = locations.get(getKey(position));
		if ((location != null) && ((location.getLocationType() == LocationType.OPEN) || (location.getLocationType() == LocationType.HEAVILY_OBSCURED) ||
				(location.getLocationType() == LocationType.TOTALLY_OBSCURED) || (location.getLocationType() == LocationType.LIGHTLY_OBSCURED))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isObstacleSquare(Position position) {
		MapLocation location = locations.get(getKey(position));
		if ((location != null) && (location.getLocationType() == LocationType.OBSTACLE)) {
			return true;
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


	public boolean providesCover(Position position, Creature attacker, AttackType attackType) {
		MapLocation location = locations.get(getKey(position));
		if (location != null) {
			if (location.getLocationType() == LocationType.OBSTACLE) {
				return true;
			} else {
				// If it's for a ranged attack, see if an enemy of the attacker occupies this square.
				if ((attackType != null) && Utils.isRangedAttack(attackType)) {
					if (Encounter.getEncounter().isSquareOccupiedByEnemyOf(attacker, position)) {
						return true;
					}
				}
			}
		}
		return false;
	}

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
	
	public void addDoor(Door door) {
		doors.add(door);
	}

	public boolean isClosedDoorBetween(Position currentPos, Position nextPos) {
		Position p1 = null;
		Position p2 = null;
		boolean checkForHorizontalDoor;
		
		/* Sort. Either X or Y should be equal. */
		if (currentPos.getX() == nextPos.getX()) {
			checkForHorizontalDoor = true;
			if (currentPos.getY() < nextPos.getY()) {
				p1 = currentPos;
				p2 = nextPos;
			} else {
				p1 = nextPos;
				p2 = currentPos;
			}
			// If they are checking more than one space apart, just return false.
			if (p2.getY() != p1.getY()+1) {
				return false;
			}
		} else if (currentPos.getY() == nextPos.getY()) {
			checkForHorizontalDoor = false;
			if (currentPos.getX() < nextPos.getX()) {
				p1 = currentPos;
				p2 = nextPos;
			} else {
				p1 = nextPos;
				p2 = currentPos;
			}
			// If they are checking more than one space apart, just return false.
			if (p2.getX() != p1.getX()+1) {
				return false;
			}
		} else {
			// This is a bad test, just return false
			return false;
		}
		
		if (doors != null) {
			for (Door door : doors) {
				// Skip open doors
				if (door.isOpen()) {
					continue;
				}
				// Skip doors with the wrong orientation.
				if (checkForHorizontalDoor && door.getOrientation() == Orientation.VERTICAL) {
					continue;
				}
				if (!checkForHorizontalDoor && door.getOrientation() == Orientation.HORIZONTAL) {
					continue;
				}
				if (checkForHorizontalDoor) {
					// First check if we're on one of the correct columns
					if (p1.getX() < door.getPosition().getX()) {
						continue;
					}
					if (p1.getX() > door.getPosition().getX() + (door.getWidth()-1)) {
						continue;
					}
					// If we've reached this point, we know that the door is on the same column as the column we're checking.  Now just check the row.
					// All I need to check is one of the y's.  Because I sorted above and already know that they are exactly one square apart.
					if (p1.getY() == door.getPosition().getY() - 1) {
						return true;
					}
				} else {
					// Check for vertical door.
					// First check if we're on one of the correct rows
					if (p1.getY() < door.getPosition().getY()) {
						continue;
					}
					if (p1.getY() > door.getPosition().getY() + (door.getWidth()-1)) {
						continue;
					}
					// If we've reached this point, we know that the door is on the same row as the row we're checking.  Now just check the column.
					// All I need to check is one of the X's.  Because I sorted above and already know that they are exactly one square apart.
					if (p1.getX() == door.getPosition().getX() - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isOpenDoorBetween(Position currentPos, Position nextPos) {
		Position p1 = null;
		Position p2 = null;
		boolean checkForHorizontalDoor;
		
		/* Sort. Either X or Y should be equal. */
		if (currentPos.getX() == nextPos.getX()) {
			checkForHorizontalDoor = true;
			if (currentPos.getY() < nextPos.getY()) {
				p1 = currentPos;
				p2 = nextPos;
			} else {
				p1 = nextPos;
				p2 = currentPos;
			}
			// If they are checking more than one space apart, just return false.
			if (p2.getY() != p1.getY()+1) {
				return false;
			}
		} else if (currentPos.getY() == nextPos.getY()) {
			checkForHorizontalDoor = false;
			if (currentPos.getX() < nextPos.getX()) {
				p1 = currentPos;
				p2 = nextPos;
			} else {
				p1 = nextPos;
				p2 = currentPos;
			}
			// If they are checking more than one space apart, just return false.
			if (p2.getX() != p1.getX()+1) {
				return false;
			}
		} else {
			// This is a bad test, just return false
			return false;
		}
		
		if (doors != null) {
			for (Door door : doors) {
				// Skip open doors
				if (!door.isOpen()) {
					continue;
				}
				// Skip doors with the wrong orientation.
				if (checkForHorizontalDoor && door.getOrientation() == Orientation.VERTICAL) {
					continue;
				}
				if (!checkForHorizontalDoor && door.getOrientation() == Orientation.HORIZONTAL) {
					continue;
				}
				if (checkForHorizontalDoor) {
					// First check if we're on one of the correct columns
					if (p1.getX() < door.getPosition().getX()) {
						continue;
					}
					if (p1.getX() > door.getPosition().getX() + (door.getWidth()-1)) {
						continue;
					}
					// If we've reached this point, we know that the door is on the same column as the column we're checking.  Now just check the row.
					// All I need to check is one of the y's.  Because I sorted above and already know that they are exactly one square apart.
					if (p1.getY() == door.getPosition().getY() - 1) {
						return true;
					}
				} else {
					// Check for vertical door.
					// First check if we're on one of the correct rows
					if (p1.getY() < door.getPosition().getY()) {
						continue;
					}
					if (p1.getY() > door.getPosition().getY() + (door.getWidth()-1)) {
						continue;
					}
					// If we've reached this point, we know that the door is on the same row as the row we're checking.  Now just check the column.
					// All I need to check is one of the X's.  Because I sorted above and already know that they are exactly one square apart.
					if (p1.getX() == door.getPosition().getX() - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Door getDoorBetween(Position currentPos, Position nextPos) {
		Position p1 = null;
		Position p2 = null;
		boolean checkForHorizontalDoor;
		
		/* Sort. Either X or Y should be equal. */
		if (currentPos.getX() == nextPos.getX()) {
			checkForHorizontalDoor = true;
			if (currentPos.getY() < nextPos.getY()) {
				p1 = currentPos;
				p2 = nextPos;
			} else {
				p1 = nextPos;
				p2 = currentPos;
			}
			// If they are checking more than one space apart, just return false.
			if (p2.getY() != p1.getY()+1) {
				return null;
			}
		} else if (currentPos.getY() == nextPos.getY()) {
			checkForHorizontalDoor = false;
			if (currentPos.getX() < nextPos.getX()) {
				p1 = currentPos;
				p2 = nextPos;
			} else {
				p1 = nextPos;
				p2 = currentPos;
			}
			// If they are checking more than one space apart, just return false.
			if (p2.getX() != p1.getX()+1) {
				return null;
			}
		} else {
			// This is a bad test, just return false
			return null;
		}
		
		if (doors != null) {
			for (Door door : doors) {
				// Skip doors with the wrong orientation.
				if (checkForHorizontalDoor && door.getOrientation() == Orientation.VERTICAL) {
					continue;
				}
				if (!checkForHorizontalDoor && door.getOrientation() == Orientation.HORIZONTAL) {
					continue;
				}
				if (checkForHorizontalDoor) {
					// First check if we're on one of the correct columns
					if (p1.getX() < door.getPosition().getX()) {
						continue;
					}
					if (p1.getX() > door.getPosition().getX() + (door.getWidth()-1)) {
						continue;
					}
					// If we've reached this point, we know that the door is on the same column as the column we're checking.  Now just check the row.
					// All I need to check is one of the y's.  Because I sorted above and already know that they are exactly one square apart.
					if (p1.getY() == door.getPosition().getY() - 1) {
						return door;
					}
				} else {
					// Check for vertical door.
					// First check if we're on one of the correct rows
					if (p1.getY() < door.getPosition().getY()) {
						continue;
					}
					if (p1.getY() > door.getPosition().getY() + (door.getWidth()-1)) {
						continue;
					}
					// If we've reached this point, we know that the door is on the same row as the row we're checking.  Now just check the column.
					// All I need to check is one of the X's.  Because I sorted above and already know that they are exactly one square apart.
					if (p1.getX() == door.getPosition().getX() - 1) {
						return door;
					}
				}
			}
		}
		return null;
	}
	
	
	public void openDoor(String direction, Position currentPosition) {
		Door door = null;
		if ("N".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX(), currentPosition.getY()+1));
		}
		if ("S".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX(), currentPosition.getY()-1));
		}
		if ("E".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX()+1, currentPosition.getY()));
		}
		if ("W".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX()-1, currentPosition.getY()));
		}
		if (door != null) {
			door.setOpen(true);
		}
	}

	public void closeDoor(String direction, Position currentPosition) {
		Door door = null;
		if ("N".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX(), currentPosition.getY()+1));
		}
		if ("S".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX(), currentPosition.getY()-1));
		}
		if ("E".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX()+1, currentPosition.getY()));
		}
		if ("W".equalsIgnoreCase(direction)) {
			door = getDoorBetween(currentPosition, new Position(currentPosition.getX()-1, currentPosition.getY()));
		}
		if (door != null) {
			door.setOpen(false);
		}
	}

	public IlluminationType getIllumination() {
		return illumination;
	}

	public void setIllumination(IlluminationType illumination) {
		this.illumination = illumination;
	}

	public List<LightSource> getLightSources() {
		return lightSources;
	}
	
	public void addLightSource(LightSource lightSource) {
		lightSources.add(lightSource);
	}

}
