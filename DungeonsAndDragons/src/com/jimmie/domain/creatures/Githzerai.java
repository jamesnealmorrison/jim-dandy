package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Githzerai extends Race {

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
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Dak, Durth, Ferzth, Greth, Hurm, Kalla, Murg, Nurm, Shrakk");
		Utils.print("Female Names: Adaka, Adeya, Ella, Ezhelya, Immilzin, Izera, Uweya");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 6' 0\" - 6' 5\" (72\" - 77\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 160 - 190 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("As a Githzerai you get +2 to Wisdom.");
		pc.setWisdom(pc.getWisdom() + 2);

		Utils.print("As a Githzerai, you get to choose to add +2 to Dexterity or Intelligence.");
		Utils.print("1. Dexterity");
		Utils.print("2. Intelligence");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (1 == choice) {
			pc.setDexterity(pc.getDexterity()+2);
		} else {
			pc.setIntelligence(pc.getIntelligence()+2);
		}
	
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Deep Speech");
		
		Utils.print("As a Githzerai, you get +2 Acrobatics and Athletics");
		Skill acrobatics = pc.getSkill(SkillType.ACROBATICS);
		acrobatics.setMisc(acrobatics.getMisc()+2);
		
		Skill athletics = pc.getSkill(SkillType.ATHLETICS);
		athletics.setMisc(athletics.getMisc()+2);
		
		Utils.print("Adding +2 racial bonus to initiative checks.");
		pc.setInitiativeMisc(pc.getInitiativeMisc() + 2);
		
		// TODO: Defended Mind, Shifting Fortunes, Iron Mind.
		Utils.print("NOTE: I have not yet coded Defended Mind, Shifting Fortunes, Iron Mind.");

		
		
	}

}
