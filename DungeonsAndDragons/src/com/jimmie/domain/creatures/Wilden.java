package com.jimmie.domain.creatures;

import com.jimmie.domain.NaturesAspect;
import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.powers.PursuitOfTheHunter;
import com.jimmie.powers.VoyageOfTheAncients;
import com.jimmie.powers.WrathOfTheDestroyer;
import com.jimmie.util.Utils;

public class Wilden extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NaturesAspect naturesAspect;

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
		Utils.print("During an extended rest, this Wilden gets to choose which aspect to manifest.");
		Utils.print("1. Aspect of the Ancients");
		Utils.print("2. Aspect of the Destroyer");
		Utils.print("3. Aspect of the Hunter");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 3);
		switch (choice) {
		case 1 : 
			naturesAspect = NaturesAspect.ASPECT_OF_THE_ANCIENTS;
			break;
		case 2 :
			naturesAspect = NaturesAspect.ASPECT_OF_THE_DESTROYER;
			break;
		case 3 :
			naturesAspect = NaturesAspect.ASPECT_OF_THE_HUNTER;
			break;
		}
	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Banmarden, Fiddenmar, Durmindin, Kettenbar, Midnorten, Rodmennar, Vennerzad");
		Utils.print("Female Names: Dannamai, Ennimbel, Kalkennash, Nementah, Shallahai, Tellorda, Zazenna");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 6\" - 5' 9\" (66\" - 69\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 140 - 170 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Elven");
		
		Utils.print("As a Wilden, you get +2 Nature and Stealth");
		Skill nature = pc.getSkill(SkillType.NATURE);
		nature.setMisc(nature.getMisc()+2);
		
		Skill stealth = pc.getSkill(SkillType.STEALTH);
		stealth.setMisc(stealth.getMisc()+2);
		
		Utils.print("As a Wilden, you get to choose to add +1 to Fortitude, Reflex, or Will.");
		Utils.print("1. Fortitude");
		Utils.print("2. Reflex");
		Utils.print("3. Will");
		int choice = Utils.getValidIntInputInRange(1, 3);
		if (1 == choice) {
			setFortitudeBonus(getFortitudeBonus()+1);
		} else if (2 == choice) {
			setReflexBonus(getReflexBonus()+1);
		} else if (3 == choice) {
			setWillBonus(getWillBonus()+1);
		}
		
		pc.addPower(new VoyageOfTheAncients());
		pc.addPower(new WrathOfTheDestroyer());
		pc.addPower(new PursuitOfTheHunter());

		pc.setOrigin(Origin.FEY);
		
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Wilden you get +2 to Wisdom.");
		setWisdomBonus(getWisdomBonus()+2);

		Utils.print("As a Wilden, you get to choose to add +2 to Constitution or Dexterity.");
		Utils.print("1. Constitution");
		Utils.print("2. Dexterity");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (1 == choice) {
			setConstitutionBonus(getConstitutionBonus()+2);
		} else {
			setDexterityBonus(getDexterityBonus()+2);
		}
	}

	public NaturesAspect getNaturesAspect() {
		return naturesAspect;
	}

}
