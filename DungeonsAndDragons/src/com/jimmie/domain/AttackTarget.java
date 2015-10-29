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
	int getFortitude(Encounter encounter, Creature attacker);
	int getReflex(Creature attacker);
	int getWill(Creature attacker);
	boolean isStunned();
	boolean isBlinded();
	void hitByBondOfPursuit(Creature pursuer);
	void hitByStirringShout(int charismaModifier);
	void hitByTelekineticAnchor();
	void move(String direction, Encounter encounter);
	boolean isHitByStirringShout();
	int getStirringShoutCharismaModifier();
	void knockProne();
	void pull(String pullDirection);
	void slide(String direction);
}
