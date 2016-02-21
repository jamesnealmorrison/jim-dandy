package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.Zombie;
import com.jimmie.domain.creatures.monsters.ZombieRotter;
import com.jimmie.encounters.CryptOfShadowsEncounter;
import com.jimmie.encounters.Encounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CryptOfShadowsEncounterConfig {
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
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\CryptOfShadows.JPG";
	}
	@Bean
	public Encounter encounter() {
		CryptOfShadowsEncounter encounter = new CryptOfShadowsEncounter();
		return encounter;
	}

	@Bean
	public ZombieRotter r1() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 1");
		monster.setDisplayName("R1");
		monster.setCurrentPosition(new Position(5,30));		
		return monster;
	}

	@Bean
	public ZombieRotter r2() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 2");
		monster.setDisplayName("R2");
		monster.setCurrentPosition(new Position(7,30));		
		return monster;
	}

	@Bean
	public ZombieRotter r3() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 3");
		monster.setDisplayName("R3");
		monster.setCurrentPosition(new Position(6,28));		
		return monster;
	}

	@Bean
	public ZombieRotter r4() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 4");
		monster.setDisplayName("R4");
		monster.setCurrentPosition(new Position(8,28));		
		return monster;
	}

	@Bean
	public ZombieRotter r5() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 5");
		monster.setDisplayName("R5");
		monster.setCurrentPosition(new Position(7,26));		
		return monster;
	}

	@Bean
	public ZombieRotter r6() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 6");
		monster.setDisplayName("R6");
		monster.setCurrentPosition(new Position(15,7));		
		return monster;
	}

	@Bean
	public ZombieRotter r7() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 7");
		monster.setDisplayName("R7");
		monster.setCurrentPosition(new Position(16,9));		
		return monster;
	}

	@Bean
	public ZombieRotter r8() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 8");
		monster.setDisplayName("R8");
		monster.setCurrentPosition(new Position(17,6));		
		return monster;
	}

	@Bean
	public ZombieRotter r9() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 9");
		monster.setDisplayName("R9");
		monster.setCurrentPosition(new Position(19,9));		
		return monster;
	}

	@Bean
	public ZombieRotter r10() {
		ZombieRotter monster = new ZombieRotter();
		monster.setName("Zombie Rotter 10");
		monster.setDisplayName("R10");
		monster.setCurrentPosition(new Position(20,7));		
		return monster;
	}

	@Bean
	public Zombie z1() {
		Zombie monster = new Zombie();
		monster.setName("Zombie 1");
		monster.setDisplayName("Z1");
		monster.setCurrentPosition(new Position(17,4));		
		return monster;
	}

	@Bean
	public Zombie z2() {
		Zombie monster = new Zombie();
		monster.setName("Zombie 2");
		monster.setDisplayName("Z2");
		monster.setCurrentPosition(new Position(18,4));
		return monster;
	}

	@Bean
	public Zombie z3() {
		Zombie monster = new Zombie();
		monster.setName("Zombie 3");
		monster.setDisplayName("Z3");
		monster.setCurrentPosition(new Position(5,26));		
		return monster;
	}

	@Bean
	public Zombie z4() {
		Zombie monster = new Zombie();
		monster.setName("Zombie 4");
		monster.setDisplayName("Z4");
		monster.setCurrentPosition(new Position(6,31));		
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
