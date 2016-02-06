package com.jimmie.domain.creatures;

import com.jimmie.domain.AmmunitionType;

public interface AmmunitionUser {
	void expendRounds(int count, AmmunitionType ammunitionType);
	int roundsLeft(AmmunitionType ammunitionType);
}
