package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.powers.FadeAway;
import com.jimmie.util.Utils;

public class Gnome extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean usedFadeAway;

	public boolean isUsedFadeAway() {
		return usedFadeAway;
	}

	public void setUsedFadeAway(boolean usedFadeAway) {
		this.usedFadeAway = usedFadeAway;
	}

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
		usedFadeAway = false;
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Alston, Alvyn, Brocc, Eldon, Frug, Kellen, Ku, Nim, Orryn, Pock, Sindri, Warryn, Wrenn");
		Utils.print("Female Names: Breena, Carlin, Donella, Ella, Lilli, Lorilla, Nissa, Nyx, Oda, Orla, Roswyn, Tana, Zanna");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 3' 4\" - 3' 8\" (40\" - 48\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 50 - 75 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Small.");
		pc.setSize(Size.SMALL);
		
		Utils.print("Setting speed to 5.");
		pc.setBaseSpeed(5);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Elven");
		
		Utils.print("As a Gnome, you get +2 Arcana and Stealth");
		Skill arcana = pc.getSkill(SkillType.ARCANA);
		arcana.setMisc(arcana.getMisc()+2);
		
		Skill stealth = pc.getSkill(SkillType.STEALTH);
		stealth.setMisc(stealth.getMisc()+2);
		
		pc.addPower(new FadeAway());
		
		// TODO: Fey Origin, Master Trickster, Reactive Stealth, Trickster's Cunning.
		Utils.print("NOTE: I have not yet coded Fey Origin, Master Trickster, Reactive Stealth, Trickster's Cunning.");
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Gnome you get +2 to Charisma and Intelligence.");
		setCharismaBonus(getCharismaBonus()+2);
		setIntelligenceBonus(getIntelligenceBonus()+2);
	}

}
