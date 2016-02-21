package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.creatures.AmmunitionUser;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.GoblinWarriorJavelin;
import com.jimmie.powers.GoblinWarriorMobileRangedAttack;
import com.jimmie.powers.GoblinWarriorSpear;

public class GoblinWarrior extends Goblin implements AmmunitionUser {
	// TODO: Great Position
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int javelins;

	public GoblinWarrior() {
		setSize(Size.SMALL);
		setInitiative(5);
		setMaxHitPoints(29);
		setCurrentHitPoints(29);
		setArmorClass(17);
		setFortitude(13);
		setReflex(15);
		setWill(12);
		setBaseSpeed(6);
		setStrength(14);
		setConstitution(13);
		setDexterity(17);
		setIntelligence(8);
		setWisdom(12);
		setCharisma(8);
		addPower(new GoblinWarriorSpear());
		addPower(new GoblinWarriorJavelin());
		addPower(new GoblinWarriorMobileRangedAttack());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GoblinWarrior.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GoblinWarriorBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GoblinWarriorBattleCard.jpg");
		javelins = 5;
	}

	public int getStrengthModifier() {
		return 2;
	}

	public int getConstitutionModifier() {
		return 1;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return -1;
	}
	
	public int getWisdomModifier() {
		return 1;
	}
	
	public int getCharismaModifier() {
		return -1;
	}

	@Override
	public void expendRounds(int count, AmmunitionType ammunitionType) {
		javelins -= count;
	}

	@Override
	public int roundsLeft(AmmunitionType ammunitionType) {
		if (ammunitionType == AmmunitionType.JAVELINS) {
			return javelins;
		}
		return 0;
	}

}
