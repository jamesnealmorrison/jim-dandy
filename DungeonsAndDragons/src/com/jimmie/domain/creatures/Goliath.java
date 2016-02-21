package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.powers.StonesEndurance;
import com.jimmie.util.Utils;

public class Goliath extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getRacialDamageBonus() {
		return 0;
	}

	@Override
	public void initializeForEncounter() {
	}

	@Override
	public void initializeForNewDay() {
	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Aukan, Eglath, Gauthak, Ilikan, Kavaki, Keothi, Lo-Kag, Maveith, Meavoi, Thotham, Vimak");
		Utils.print("Female Names: Gai-Al, Kuori, Manneo, Nalla, Orilo, Paavu, Pehani, Thalai, Uthal, Vaunea");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 7' 2\" - 7' 8\" (86\" - 92\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 280 - 340 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");
		
		Utils.print("Choose Dwarven or Giant language");
		Utils.print("1. Dwarven");
		Utils.print("2. Giant");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			pc.addLanguage("Dwarven");
		} else {
			pc.addLanguage("Giant");
		}
		
		Utils.print("As a Goliath, you get +2 Athletics and Nature");
		Skill athletics = pc.getSkill(SkillType.ATHLETICS);
		athletics.setMisc(athletics.getMisc()+2);
		
		Skill nature = pc.getSkill(SkillType.NATURE);
		nature.setMisc(nature.getMisc()+2);
		
		Utils.print("As a Goliath, you get a +1 Will.");
		setWillBonus(getWillBonus()+1);
		
		pc.addPower(new StonesEndurance());
		
		// TODO: Powerful Athlete.
		Utils.print("NOTE: I have not yet coded Powerful Athlete.");
		
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Goliath you get +2 to Strength and Constitution.");
		setStrengthBonus(getStrengthBonus()+2);
		setConstitutionBonus(getConstitutionBonus()+2);
	}

	@Override
	public String getRaceFeaturesText1() {
		return "Mountain's Tenacity: I have a +1 bonus to Will.";
	}

	@Override
	public String getRaceFeaturesText2() {
		return "Powerful Athlete: Roll twice for Athletics";
	}

	@Override
	public String getRaceFeaturesText3() {
		return "checks to jump or climb. Use either roll.";
	}

	@Override
	public String getRaceFeaturesText4() {
		return "Stone's Endurance Power: Resist 5 to all";
	}

	@Override
	public String getRaceFeaturesText5() {
		return "damage until the end of my next turn.";
	}
}
