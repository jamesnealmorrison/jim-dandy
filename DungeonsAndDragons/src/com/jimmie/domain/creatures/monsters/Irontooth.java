package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.IrontoothBattleaxe;
import com.jimmie.powers.IrontoothDualAxe;
import com.jimmie.util.Utils;

public class Irontooth extends Goblin {
	@Override
	public void startOfTurn() {
		super.startOfTurn();
		if (isBloodied()) {
			Utils.print("IRONTOOTH IS IN A BLOOD CRAZE!!!!!!  HE MUST ATTACK THE NEAREST FOE, CHARGING WHEN POSSIBLE!!!!!!!!!");
		}
	}

	@Override
	public void endOfTurn() {
		super.endOfTurn();
		if (isBloodied()) {
			Utils.print("When Irontooth is bloodied, he is Blood Crazed, and heals 5 hp at the end of each turn.");
			heal(5);
		}
	}

	private static final long serialVersionUID = 1L;

	public Irontooth() {
		setSize(Size.SMALL);
		setInitiative(2);
		setMaxHitPoints(106);
		setCurrentHitPoints(106);
		setArmorClass(18);
		setFortitude(18);
		setReflex(16);
		setWill(17);
		setBaseSpeed(6);
		this.setActionPoints(1);
		setStrength(18);
		setConstitution(13);
		setDexterity(14);
		setIntelligence(8);
		setWisdom(16);
		setCharisma(12);
		addPower(new IrontoothBattleaxe());
		addPower(new IrontoothDualAxe());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\Irontooth.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\IrontoothBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\IrontoothBattleCard.jpg");
	}
	
	public int getStrengthModifier() {
		return 5;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 4;
	}
	
	public int getCharismaModifier() {
		return 2;
	}
}
