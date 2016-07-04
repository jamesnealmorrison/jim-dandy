package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.SkeletonWarriorLongsword;

public class SkeletonWarrior extends AbstractSkeleton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SkeletonWarrior() {
		setSize(Size.MEDIUM);
		setInitiative(6);
		setMaxHitPoints(45);
		setCurrentHitPoints(45);
		setArmorClass(18);
		setFortitude(15);
		setReflex(16);
		setWill(15);
		setBaseSpeed(5);
		setStrength(15);
		setConstitution(13);
		setDexterity(17);
		setIntelligence(3);
		setWisdom(14);
		setCharisma(3);
		addPower(new SkeletonWarriorLongsword());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\SkeletonWarrior.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\SkeletonWarriorBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\SkeletonWarriorBattleCard.jpg");
	}

	public int getStrengthModifier() {
		return 3;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 4;
	}

	public int getIntelligenceModifier() {
		return -3;
	}
	
	public int getWisdomModifier() {
		return 3;
	}
	
	public int getCharismaModifier() {
		return -3;
	}

}
