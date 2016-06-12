package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.DecrepitSkeleton;
import com.jimmie.domain.creatures.monsters.SkeletonWarrior;
import com.jimmie.encounters.SkeletalLegionEncounter;
import com.jimmie.encounters.Encounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SkeletalLegionEncounterConfig {
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
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\SkeletalLegion.JPG";
	}
	@Bean
	public Encounter encounter() {
		SkeletalLegionEncounter encounter = new SkeletalLegionEncounter();
		return encounter;
	}
	@Bean
	public SkeletonWarrior w1() {
		SkeletonWarrior monster = new SkeletonWarrior();
		monster.setName("Skeleton Warrior 1");
		monster.setDisplayName("W1");
		monster.setCurrentPosition(new Position(15,8));
		return monster;
	}
	@Bean
	public SkeletonWarrior w2() {
		SkeletonWarrior monster = new SkeletonWarrior();
		monster.setName("Skeleton Warrior 2");
		monster.setDisplayName("W2");
		monster.setCurrentPosition(new Position(15,7));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s1() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 1");
		monster.setDisplayName("S1");
		monster.setCurrentPosition(new Position(12,7));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s2() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 2");
		monster.setDisplayName("S2");
		monster.setCurrentPosition(new Position(12,8));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s3() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 3");
		monster.setDisplayName("S3");
		monster.setCurrentPosition(new Position(9,7));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s4() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 4");
		monster.setDisplayName("S4");
		monster.setCurrentPosition(new Position(9,8));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s5() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 5");
		monster.setDisplayName("S5");
		monster.setCurrentPosition(new Position(5,7));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s6() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 6");
		monster.setDisplayName("S6");
		monster.setCurrentPosition(new Position(5,8));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s7() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 7");
		monster.setDisplayName("S7");
		monster.setCurrentPosition(new Position(3,7));
		return monster;
	}
	@Bean
	public DecrepitSkeleton s8() {
		DecrepitSkeleton monster = new DecrepitSkeleton();
		monster.setName("Decrepit Skeleton 8");
		monster.setDisplayName("S8");
		monster.setCurrentPosition(new Position(3,8));
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
