package com.jimmie.domain.map;

public enum LocationType {
	OPEN, // You can enter the square and it doesn't provide any cover/concealment.
	OBSTACLE, // The square is closed and is an obstacle (i.e. provided concealment).
	LIGHTLY_OBSCURED, // (dim light, foliage, fog, smoke, etc...)
	HEAVILY_OBSCURED, // (heavy fog, heavy smoke, etc...)
	TOTALLY_OBSCURED // (darkness)
}
