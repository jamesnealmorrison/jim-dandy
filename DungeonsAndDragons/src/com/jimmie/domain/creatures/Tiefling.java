package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Tiefling extends Race {

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
		Utils.print("Male Names: Akmenos, Amnon, Barakas, Damakos, Ekemon, Iados, Kairon, Leucis, Melech, Morthos, Pelaios, Skamos, Therai");
		Utils.print("Female Names: Akta, Bryseis, Damaia, Ea, Kallista, Lerissa, Makaria, Nemeia, Orianna, Phelaia, Rieta");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 6\" - 6' 2\" (66\" - 74\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 140 - 230 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("As a Tiefling you get +2 to Intelligence and Charisma.");
		pc.setIntelligence(pc.getIntelligence() + 2);
		pc.setCharisma(pc.getCharisma() + 2);
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		// TODO: Language choice
		Utils.print("NOTE: I have not yet coded the choice of another language.");
		
		Utils.print("As a Teifling, you get +2 Bluff and Stealth.");
		Skill bluff = pc.getSkill(SkillType.BLUFF);
		bluff.setMisc(bluff.getMisc()+2);
		
		Skill stealth = pc.getSkill(SkillType.STEALTH);
		stealth.setMisc(stealth.getMisc()+2);
		
		// TODO: Bloodhunt, Fire Resistance, Infernal Wrath.
		Utils.print("NOTE: I have not yet coded Bloodhunt, Fire Resistance, Infernal Wrath.");
	}

}
