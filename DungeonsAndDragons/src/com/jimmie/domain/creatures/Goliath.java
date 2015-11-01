package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Goliath extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STONES_ENDURANCE = "Stone's Endurance";

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAfterHurtEffects(Creature creature) {
		// TODO Auto-generated method stub
		
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
		
		Utils.print("As a Goliath you get +2 to Strength and Constitution.");
		pc.setConstitution(pc.getConstitution() + 2);
		pc.setStrength(pc.getStrength() + 2);
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
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
		
		Utils.print("As a Goliath, you get a +1 Will.  Adding it to the misc 1.");
		pc.setWillMisc1(pc.getWillMisc1()+1);
		
		// TODO: Powerful Athlete, Stone's Endurance.
		Utils.print("NOTE: I have not yet coded Powerful Athlete, Stone's Endurance.");
		
	}
}
