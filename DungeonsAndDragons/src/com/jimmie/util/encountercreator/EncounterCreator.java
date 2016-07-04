package com.jimmie.util.encountercreator;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.util.Utils;

public class EncounterCreator {
	private String encounterName;
	private List<MonsterInfo> monsters;
	private String illumination;
	private int height;
	private int width;
	private List<Integer> mapSquares;

	public static void main(String[] args) {
		EncounterCreator creator = new EncounterCreator();
		
		creator.run();

	}

	private void run() {
		getEncounterParameters();
		printEncounterClass();
		printConfigClass();
	}

	private void getEncounterParameters() {
		Utils.print("What is the encounter name?");
		encounterName = Utils.getInput();
		
		monsters = new ArrayList<MonsterInfo>();
		boolean moreMonsters = true;
		while (moreMonsters) {
			MonsterInfo monsterInfo = new MonsterInfo();
			Utils.print("What is the class name for this monster?");
			monsterInfo.setMonsterClass(Utils.getInput());
			Utils.print("What LOWER CASE letter will represent this monster on the screen?");
			monsterInfo.setDisplayName(Utils.getInput());
			Utils.print("What is the monster long name (usually the class name with spaces)?");
			monsterInfo.setLongName(Utils.getInput());			
			Utils.print("How many of this monster?");
			monsterInfo.setCount(Utils.getInt());
			monsters.add(monsterInfo);
			Utils.print("More monsters?");
			if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
				moreMonsters = true;
			} else {
				moreMonsters = false;
			}
		}
		
		Utils.print("What type of lighting for this encounter?");
		Utils.print("1. Bright");
		Utils.print("2. Dim");
		Utils.print("3. Dark");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 3);
		switch (choice) {
		case 1 : 
			illumination = "BRIGHT_LIGHT";
			break;
		case 2 :
			illumination = "DIM_LIGHT";
			break;
		case 3 :
			illumination = "DARKNESS";
			break;
		}
		
		Utils.print("What is the height of the encounter map?");
		height = Utils.getInt();
		Utils.print("What is the width of the encounter map?");
		width = Utils.getInt();
		
		mapSquares = new ArrayList<Integer>();
		for (int col = 1; col <= width; col++) {
			for (int row = 1; row <= height; row++) {
				Utils.print("Is (" +  col + ", " + row + ") a 1 (open), 2 (obstacle), 3 (lightly obscured), 4 (heavily obscured) or 5 (totally obscured)?");
				int answer = Utils.getValidIntInputInRange(1, 5);
				mapSquares.add(answer);
			}
		}
	}

	private void printEncounterClass() {
		Utils.print("Create a class in com.jimmie.encounters called " + encounterName + "Encounter.");
		Utils.print("Paste everything between the stars to that class:");
		Utils.print("*******************************************************************************");
		
		
		Utils.print("package com.jimmie.encounters;");
		Utils.print("");
		Utils.print("import java.util.ArrayList;");
		Utils.print("import java.util.HashMap;");
		Utils.print("import java.util.List;");
		Utils.print("import org.springframework.beans.factory.annotation.Autowired;");
		Utils.print("import com.jimmie.domain.IlluminationType;");
		Utils.print("import com.jimmie.domain.Position;");
		Utils.print("import com.jimmie.domain.TurnTaker;");
		Utils.print("import com.jimmie.domain.creatures.Creature;");
		Utils.print("import com.jimmie.domain.creatures.DndCharacter;");
		Utils.print("import com.jimmie.domain.creatures.PlayerCharacter;");
		Utils.print("import com.jimmie.domain.creatures.monsters.Monster;");
		Utils.print("import com.jimmie.domain.map.Door;");
		Utils.print("import com.jimmie.domain.map.LocationType;");
		Utils.print("import com.jimmie.domain.map.Map;");
		Utils.print("import com.jimmie.domain.map.MapLocation;");
		Utils.print("import com.jimmie.domain.map.Orientation;");
		Utils.print("import com.jimmie.util.Utils;");
		Utils.print("");
		Utils.print("public class " + encounterName + "Encounter extends Encounter {");
		for (MonsterInfo monsterInfo : monsters) {
			for (int i = 0; i < monsterInfo.getCount(); i++) {
				Utils.print("	@Autowired");
				// Should it have a number after it?
				if (monsterInfo.getCount() > 1) {
					Utils.print("	private " + monsterInfo.getMonsterClass() + " " + monsterInfo.getDisplayName() + (i + 1) + ";");
				} else {
					Utils.print("	private " + monsterInfo.getMonsterClass() + " " + monsterInfo.getDisplayName() + ";");
				}
			}
		}
		Utils.print("");
		Utils.print("	@Autowired");
		Utils.print("	private DndCharacter gamal;");
		Utils.print("	@Autowired");
		Utils.print("	private DndCharacter percian;");
		Utils.print("	@Autowired");
		Utils.print("	private DndCharacter keothi;");
		Utils.print("	@Autowired");
		Utils.print("	private DndCharacter travok;");
		Utils.print("	@Autowired");
		Utils.print("	private DndCharacter hazel;");
		Utils.print("");
		Utils.print("	@Override");
		Utils.print("	protected void makeEncounterInitiativeChanges() {");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	@Override");
		Utils.print("	public void init() {");
		Utils.print("		gamal.setCurrentPosition(new Position (-1,-1));");
		Utils.print("		percian.setCurrentPosition(new Position (-1,-1));");
		Utils.print("		keothi.setCurrentPosition(new Position (-1,-1));");
		Utils.print("		travok.setCurrentPosition(new Position (-1,-1));");
		Utils.print("		hazel.setCurrentPosition(new Position (-1,-1));");
		Utils.print("");
		Utils.print("		// Set up the monsters");
		Utils.print("		monsters = new ArrayList<Monster>();		");
		for (MonsterInfo monsterInfo : monsters) {
			for (int i = 0; i < monsterInfo.getCount(); i++) {
				// Should it have a number after it?
				if (monsterInfo.getCount() > 1) {
					Utils.print("		monsters.add(" + monsterInfo.getDisplayName() + (i + 1) + ");");
				} else {
					Utils.print("		monsters.add(" + monsterInfo.getDisplayName() + ");");
				}
			}
		}
		Utils.print("");
		Utils.print("		characters = new ArrayList<DndCharacter>();");
		Utils.print("		characters.add(gamal);");
		Utils.print("		characters.add(percian);");
		Utils.print("		characters.add(keothi);");
		Utils.print("		characters.add(travok);");
		Utils.print("		characters.add(hazel);");
		Utils.print("		");
		Utils.print("		map = new Map();");
		Utils.print("		map.setIllumination(IlluminationType." + illumination + ");");
		Utils.print("		map.setWidth(" + width + ");");
		Utils.print("		map.setHeight(" + height + ");");
		Utils.print("		");
		Utils.print("		//Position position, LocationType locationType, boolean difficultTerrain, SkillCheck skillCheckToEnter, int extraMovementCostToEnter, int height");
		int i = 0;
		for (int col = 1; col <= width; col++) {
			for (int row = 1; row <= height; row++) {
				String locationType = null;
				switch (mapSquares.get(i)) {
				case 1 :
					locationType = "OPEN";
					break;
				case 2 :
					locationType = "OBSTACLE";
					break;
				case 3 :
					locationType = "LIGHTLY_OBSCURED";
					break;
				case 4 :
					locationType = "HEAVILY_OBSCURED";
					break;
				case 5 :
					locationType = "TOTALLY_OBSCURED";
					break;
				}
				Utils.print("		map.addLocation(new MapLocation(new Position(" + col + "," + row + "), LocationType." + locationType + ", false, null, 0, 0));");
				
				i++;
			}
		}

		Utils.print("add doors manually");
		Utils.print("	}");
		
		Utils.print("		");
		Utils.print("	@Override");
		Utils.print("	public void setup() {");
		Utils.print("Setup character locations manually");
		Utils.print("	}");
		
		Utils.print("");
		Utils.print("	@Override");
		Utils.print("	public boolean isActive(TurnTaker nextParticipant) {");
		Utils.print("	}");

		// Getters and Setters
		for (MonsterInfo monsterInfo : monsters) {
			for (i = 0; i < monsterInfo.getCount(); i++) {
				// Should it have a number after it?
				if (monsterInfo.getCount() > 1) {
					Utils.print("	public " + monsterInfo.getMonsterClass() + " get" + monsterInfo.getDisplayName().toUpperCase() + (i + 1) + "() {");
					Utils.print("		return " + monsterInfo.getDisplayName() + (i + 1) + ";");
					Utils.print("	}");
					Utils.print("");
					Utils.print("	public void set" + monsterInfo.getDisplayName().toUpperCase() + (i + 1) + "(" + monsterInfo.getMonsterClass() + " monster) {");
					Utils.print("		this." + monsterInfo.getDisplayName() + (i + 1) + " = monster;");
					Utils.print("	}");
					Utils.print("");					
				} else {
					Utils.print("	public " + monsterInfo.getMonsterClass() + " get" + monsterInfo.getDisplayName().toUpperCase() + "() {");
					Utils.print("		return " + monsterInfo.getDisplayName() + ";");
					Utils.print("	}");
					Utils.print("");
					Utils.print("	public void set" + monsterInfo.getDisplayName().toUpperCase() + "(" + monsterInfo.getMonsterClass() + " monster) {");
					Utils.print("		this." + monsterInfo.getDisplayName() + (i + 1) + " = monster;");
					Utils.print("	}");
					Utils.print("");					
				}
			}
		}
		Utils.print("	public DndCharacter getGamal() {");
		Utils.print("		return gamal;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public void setGamal(DndCharacter gamal) {");
		Utils.print("		this.gamal = gamal;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public DndCharacter getPercian() {");
		Utils.print("		return percian;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public void setPercian(DndCharacter percian) {");
		Utils.print("		this.percian = percian;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public DndCharacter getKeothi() {");
		Utils.print("		return keothi;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public void setKeothi(DndCharacter keothi) {");
		Utils.print("		this.keothi = keothi;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public DndCharacter getTravok() {");
		Utils.print("		return travok;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public void setTravok(DndCharacter travok) {");
		Utils.print("		this.travok = travok;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public DndCharacter getHazel() {");
		Utils.print("		return hazel;");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	public void setHazel(DndCharacter hazel) {");
		Utils.print("		this.hazel = hazel;");
		Utils.print("	}");
		
		Utils.print("}");

	
	
		Utils.print("*******************************************************************************");
	
	}

	private void printConfigClass() {
		Utils.print("Create a class in com.jimmie called " + encounterName + "EncounterConfig.");
		Utils.print("Paste everything between the stars to that class:");
		Utils.print("*******************************************************************************");
		
		Utils.print("package com.jimmie;");
		Utils.print("");
		Utils.print("import org.springframework.context.annotation.Bean;");
		Utils.print("import org.springframework.context.annotation.Configuration;");
		Utils.print("import org.springframework.context.annotation.EnableAspectJAutoProxy;");
		Utils.print("import com.jimmie.domain.Position;");
		Utils.print("import com.jimmie.domain.creatures.DndCharacter;");
		Utils.print("import com.jimmie.encounters." + encounterName + "Encounter;");
		Utils.print("import com.jimmie.encounters.Encounter;");
		Utils.print("import com.jimmie.gui.BattleCardPanel;");
		Utils.print("import com.jimmie.gui.BattlefieldPanel;");
		Utils.print("import com.jimmie.gui.ConsolePanel;");
		Utils.print("import com.jimmie.gui.DungeonGUI;");
		Utils.print("import com.jimmie.gui.PartyPanel;");
		Utils.print("import com.jimmie.util.Utils;");
		Utils.print("");
		Utils.print("@Configuration");
		Utils.print("@EnableAspectJAutoProxy(proxyTargetClass=true)");
		Utils.print("public class " + encounterName + "EncounterConfig {");
		Utils.print("	@Bean");
		Utils.print("	public DungeonGUI dungeonGUI() {");
		Utils.print("		return new DungeonGUI();");
		Utils.print("	}");
		Utils.print("	@Bean");
		Utils.print("	public PartyPanel partyPanel() {");
		Utils.print("		return new PartyPanel();");
		Utils.print("	}");
		Utils.print("	@Bean");
		Utils.print("	public ConsolePanel consolePanel() {");
		Utils.print("		return new ConsolePanel();");
		Utils.print("	}");
		Utils.print("	@Bean");
		Utils.print("	public BattlefieldPanel battlefieldPanel() {");
		Utils.print("		return new BattlefieldPanel();");
		Utils.print("	}");
		Utils.print("	@Bean");
		Utils.print("	public BattleCardPanel battleCardPanel() {");
		Utils.print("		return new BattleCardPanel();");
		Utils.print("	}");
		Utils.print("	@Bean");
		Utils.print("	public String battlefieldImageFilePath() {");
		Utils.print("		return \"c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\" + encounterName + ".JPG\";");
		Utils.print("	}");
		Utils.print("	@Bean");
		Utils.print("	public Encounter encounter() {");
		Utils.print("		" + encounterName + "Encounter encounter = new " + encounterName + "Encounter();");
		Utils.print("		return encounter;");
		Utils.print("	}");
		Utils.print("");
		for (MonsterInfo monsterInfo : monsters) {
			for (int i = 0; i < monsterInfo.getCount(); i++) {
				Utils.print("	@Bean");
				// Should it have a number after it?
				if (monsterInfo.getCount() > 1) {
					Utils.print("	public " + monsterInfo.getMonsterClass() + " " + monsterInfo.getDisplayName() + (i + 1) + "() {");
					Utils.print("		" + monsterInfo.getMonsterClass() + " monster = new " + monsterInfo.getMonsterClass() + "();");
					Utils.print("		monster.setName(\"" + monsterInfo.getLongName() + " " + (i + 1) + "\");");
					Utils.print("		monster.setDisplayName(\"" + monsterInfo.getDisplayName().toUpperCase() + (i + 1) + "\");");
					Utils.print("		monster.setCurrentPosition(new Position(,));");
					Utils.print("		return monster;");
					Utils.print("	}");
					Utils.print("");
				} else {
					Utils.print("	public " + monsterInfo.getMonsterClass() + " " + monsterInfo.getDisplayName() + "() {");
					Utils.print("		" + monsterInfo.getMonsterClass() + " monster = new " + monsterInfo.getMonsterClass() + "();");
					Utils.print("		monster.setName(\"" + monsterInfo.getLongName() + "\");");
					Utils.print("		monster.setDisplayName(\"" + monsterInfo.getDisplayName().toUpperCase() + "\");");
					Utils.print("		monster.setCurrentPosition(new Position(,));");
					Utils.print("		return monster;");
					Utils.print("	}");
					Utils.print("");
				}
			}
		}

		
		Utils.print("	@Bean");
		Utils.print("	public DndCharacter percian() {");
		Utils.print("		return Utils.loadCharacter(\"Percian\");");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	@Bean");
		Utils.print("	public DndCharacter gamal() {");
		Utils.print("		return Utils.loadCharacter(\"Gamal\");");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	@Bean");
		Utils.print("	public DndCharacter keothi() {");
		Utils.print("		return Utils.loadCharacter(\"Keothi\");");
		Utils.print("	}");
		Utils.print("	");
		Utils.print("	@Bean");
		Utils.print("	public DndCharacter travok() {");
		Utils.print("		return Utils.loadCharacter(\"Travok\");");
		Utils.print("	}");
		Utils.print("");
		Utils.print("	@Bean");
		Utils.print("	public DndCharacter hazel() {");
		Utils.print("		return Utils.loadCharacter(\"Hazel\");");
		Utils.print("	}");
		Utils.print("}");
		
		
		Utils.print("*******************************************************************************");
	
		
	}

}
