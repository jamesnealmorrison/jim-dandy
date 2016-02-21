package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.GoblinSharpshooter;
import com.jimmie.domain.creatures.monsters.GoblinWarrior;
import com.jimmie.domain.creatures.monsters.RatSwarm;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.GoblinGuardRoomEncounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
//@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class GoblinGuardRoomEncounterConfig {
	@Bean
	public DungeonGUI dungeonGUI() {
		return new DungeonGUI();
	}
	@Bean
	public PartyPanel partyPanel() {
		return new PartyPanel();
	}
	@Bean
	public ConsolePanel consolePanel() {
		return new ConsolePanel();
	}
	@Bean
	public BattlefieldPanel battlefieldPanel() {
		return new BattlefieldPanel();
	}
	@Bean
	public BattleCardPanel battleCardPanel() {
		return new BattleCardPanel();
	}
	@Bean
	public String battlefieldImageFilePath() {
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\Area1GoblinGuardRoom.JPG";
	}
	@Bean
	public Encounter encounter() {
		GoblinGuardRoomEncounter encounter = new GoblinGuardRoomEncounter();
		return encounter;
	}

	@Bean
	public RatSwarm r() {
		RatSwarm monster = new RatSwarm();
		monster.setName("Rat Swarm");
		monster.setDisplayName("R");
		monster.setCurrentPosition(new Position(10,16));		
		return monster;
	}

	@Bean
	public GoblinSharpshooter g1() {
		GoblinSharpshooter monster = new GoblinSharpshooter();
		monster.setName("Goblin Sharpshooter 1");
		monster.setDisplayName("G1");
		monster.setCurrentPosition(new Position(6,7));		
		return monster;
	}

	@Bean
	public GoblinSharpshooter g2() {
		GoblinSharpshooter monster = new GoblinSharpshooter();
		monster.setName("Goblin Sharpshooter 2");
		monster.setDisplayName("G2");
		monster.setCurrentPosition(new Position(8,7));		
		return monster;
	}

	@Bean
	public GoblinWarrior w1() {
		GoblinWarrior monster = new GoblinWarrior();
		monster.setName("Goblin Warrior 1");
		monster.setDisplayName("W1");
		monster.setCurrentPosition(new Position(10,13));		
		return monster;
	}

	@Bean
	public GoblinWarrior w2() {
		GoblinWarrior monster = new GoblinWarrior();
		monster.setName("Goblin Warrior 2");
		monster.setDisplayName("W2");
		monster.setCurrentPosition(new Position(17,11));		
		return monster;
	}

	@Bean
	public DndCharacter percian() {
		return Utils.loadCharacter("Percian");
	}

	@Bean
	public DndCharacter gamal() {
		return Utils.loadCharacter("Gamal");
	}

	@Bean
	public DndCharacter keothi() {
		return Utils.loadCharacter("Keothi");
	}
	
	@Bean
	public DndCharacter travok() {
		return Utils.loadCharacter("Travok");
	}

	@Bean
	public DndCharacter hazel() {
		return Utils.loadCharacter("Hazel");
	}
}
