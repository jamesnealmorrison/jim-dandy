package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.GnomeSkulk;
import com.jimmie.domain.creatures.monsters.GuardDrake;
import com.jimmie.domain.creatures.monsters.HalflingSlinger;
import com.jimmie.domain.creatures.monsters.HumanRabble;
import com.jimmie.encounters.BurialSiteEncounter;
import com.jimmie.encounters.Encounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
//@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class BurialSiteEncounterConfig {
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
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\BurialSite.JPG";
	}
	@Bean
	public Encounter encounter() {
		BurialSiteEncounter encounter = new BurialSiteEncounter();
		return encounter;
	}

	@Bean
	public GuardDrake d1() {
		GuardDrake d1 = new GuardDrake();
		d1.setName("Guard Drake 1");
		d1.setDisplayName("D1");
		d1.setCurrentPosition(new Position(20,11));		
		return d1;
	}

	@Bean
	public GuardDrake d2() {
		GuardDrake d2 = new GuardDrake();
		d2.setName("Guard Drake 2");
		d2.setDisplayName("D2");
		d2.setCurrentPosition(new Position(20,13));		
		return d2;
	}

	@Bean
	public HumanRabble r1() {
		HumanRabble r1 = new HumanRabble();
		r1.setName("Human Rabble 1");
		r1.setDisplayName("R1");
		r1.setCurrentPosition(new Position(13,14));		
		return r1;
	}

	@Bean
	public HumanRabble r2() {
		HumanRabble r2 = new HumanRabble();
		r2.setName("Human Rabble 2");
		r2.setDisplayName("R2");
		r2.setCurrentPosition(new Position(15,16));		
		return r2;
	}

	@Bean
	public HumanRabble r3() {
		HumanRabble r3 = new HumanRabble();
		r3.setName("Human Rabble 3");
		r3.setDisplayName("R3");
		r3.setCurrentPosition(new Position(18,16));		
		return r3;
	}

	@Bean
	public HumanRabble r4() {
		HumanRabble r4 = new HumanRabble();
		r4.setName("Human Rabble 4");
		r4.setDisplayName("R4");
		r4.setCurrentPosition(new Position(18,18));		
		return r4;
	}


	@Bean
	public HalflingSlinger h() {
		HalflingSlinger h = new HalflingSlinger();
		h.setName("Halfling Slinger");
		h.setDisplayName("H");
		h.setCurrentPosition(new Position(7,16));		
		return h;
	}

	@Bean
	public GnomeSkulk s() {
		GnomeSkulk s = new GnomeSkulk();
		s.setName("Gnome Skulk");
		s.setDisplayName("S");
		s.setCurrentPosition(new Position(13,16));		
		return s;
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
