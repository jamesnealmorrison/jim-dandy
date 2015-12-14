package com.jimmie.domain.creatures;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.powers.FuriousAssault;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class HalfOrc extends Race {

	@Override
	public void initializeForEncounter() {
		usedFuriousAssault = false;
		usedHalfOrcResilience = false;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean usedFuriousAssault;
	private boolean usedHalfOrcResilience;

	public boolean isUsedHalfOrcResilience() {
		return usedHalfOrcResilience;
	}

	public void setUsedHalfOrcResilience(boolean usedHalfOrcResilience) {
		this.usedHalfOrcResilience = usedHalfOrcResilience;
	}

	@Override
	public int getRacialDamageBonus() {
		/* Can they use furious assault? */
		if (!usedFuriousAssault) {
			/* Do they want to? */
			Utils.print("You have the ability to use the Half Orc free Encounter power Furious Assault to add 1d8 damage. Do you want to?");
			String choice = Utils.getYesOrNoInput();
			if ("Y".equalsIgnoreCase(choice)) {
				usedFuriousAssault = true;
				Dice dice = new Dice(DiceType.EIGHT_SIDED);
				return dice.roll();
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Brug, Dorn, Druuk, Gnarsh, Grumbar, Hogar, Karash, Korgul, Krusk, Lubash, Mord, Ohr, Rendar, Sark, Scrag, Tanglar, Tarak, Thar, Ugarth, Yurk");
		Utils.print("Female Names: Augh, Bree, Ekk, Gaaki, Grai, Grigri, Gynk, Huru, Lagazi, Murook, Nogu, Ootah, Puyet, Tawar, Tomph, Ubada, Vanchu");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 9\" - 6' 4\" (69\" - 76\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 155 - 225 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Giant");
		
		Utils.print("As a Half-Orc, you get +2 Endurance and Intimidate");
		Skill intimidate = pc.getSkill(SkillType.INTIMIDATE);
		intimidate.setMisc(intimidate.getMisc()+2);
		
		Skill endurance = pc.getSkill(SkillType.ENDURANCE);
		endurance.setMisc(endurance.getMisc()+2);
		
		pc.addPower(new FuriousAssault());
		
		// TODO: Half-Orc Resilience, Swift Charge.
		Utils.print("NOTE: I have not yet coded Half-Orc Resilience, Swift Charge.");
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Half-Orc you get +2 to Strength and Dexterity.");
		setStrengthBonus(getStrengthBonus()+2);
		setDexterityBonus(getDexterityBonus()+2);
	}
}
