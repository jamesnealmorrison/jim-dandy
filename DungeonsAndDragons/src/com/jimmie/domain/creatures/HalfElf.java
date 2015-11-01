package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class HalfElf extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		Utils.print("What is your character's name? From the book it suggests to use elf or human names:");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 5\" - 6' 2\" (65\" - 74\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 130 - 190 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("As a Half-Elf you get +2 to Constitution and Charisma.");
		pc.setConstitution(pc.getConstitution() + 2);
		pc.setCharisma(pc.getCharisma() + 2);
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Elven");
		
		Utils.print("NOTE: I have not yet coded the choice of another language.");
		
		Utils.print("As a Half-Elf, you get +2 Diplomacy and Insight.");
		Skill diplomacy = pc.getSkill(SkillType.DIPLOMACY);
		diplomacy.setMisc(diplomacy.getMisc()+2);
		
		Skill insight = pc.getSkill(SkillType.INSIGHT);
		insight.setMisc(insight.getMisc()+2);

		// TODO: Dilettante, Group Diplomacy.
		Utils.print("NOTE: I have not yet coded Dilettante or Group Diplomacy.");
	}

}
