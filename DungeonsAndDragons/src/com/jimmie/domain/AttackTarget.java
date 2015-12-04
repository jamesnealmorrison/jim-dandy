package com.jimmie.domain;

import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;

public interface AttackTarget {
	String getName();
	int getArmorClass(Creature attacker);
	void hurt(int damage, DamageType damageType, Encounter encounter, boolean hit);
	Position getCurrentPosition();
	void push(String pushDirection);
	void markByCombatChallenge(Creature owner, DurationType durationType);
	void markByMisdirectedMark(Creature owner, Creature misdirectedOwner, DurationType durationType);
	void grantCombatAdvantageViaWardensFury(Creature owner);
	void setTemporaryAttackRollModifier(Creature owner, DurationType durationType, int attackRollModifier);
	int getFortitude();
	int getReflex(Creature attacker);
	int getWill(Creature attacker);
	boolean isStunned();
	boolean isBlinded();
	void hitByBondOfPursuit(Creature pursuer);
	void hitByStirringShout(int charismaModifier);
	void hitByTelekineticAnchor();
	void moveCreature(String direction, Encounter encounter, MovementType movementType);
	boolean isHitByStirringShout();
	int getStirringShoutCharismaModifier();
	void knockProne();
	void pull(String pullDirection);
	void slide(String direction);
	int getCurrentHitPoints();
}
