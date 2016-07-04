package com.jimmie.rituals;

import java.io.Serializable;
import java.util.List;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.Time;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.Price;

public abstract class Ritual implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String getName();
	public abstract int getLevel();
	public abstract RitualCategory getCategory();
	public abstract Time getTime();
	public abstract Time getDurationTime();
	public ComponentCost getComponentCost() {
		if (getKeySkill() == SkillType.ARCANA) {
			return ComponentCost.ALCHEMICAL_REAGENTS;
		} else if (getKeySkill() == SkillType.HEAL) {
			return ComponentCost.MYSTIC_SALVES;
		} else if (getKeySkill() == SkillType.NATURE) {
			return ComponentCost.RARE_HERBS;
		} else if (getKeySkill() == SkillType.RELIGION) {
			return ComponentCost.SANCTIFIED_INCENSE;
		} else {
			return ComponentCost.RESIDUUM;
		}
	}
	public abstract SkillType getKeySkill();
	public abstract Price getMarketPrice();
	public abstract Price getComponentCostPrice();
	public abstract void cast(PlayerCharacter user);
	public abstract boolean meetsPrerequisitesToChooseRitual(PlayerCharacter user);
	public abstract boolean meetsRequirementsToCastRitual(PlayerCharacter user);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean canBeSelected(PlayerCharacter pc) {
		// First check if they are a high enough level.
		if (pc.getLevel() < getLevel()) {
			return false;
		}
		
		if (!meetsPrerequisitesToChooseRitual(pc)) {
			return false;
		}

		if ((getClassesThatCanCastRitual() == null) && (getRacesThatCanCastRitual() == null)) {
			return true;
		}
		else {
			if (getClassesThatCanCastRitual() != null) {
				for (Class dndClass : getClassesThatCanCastRitual()) {
					if (dndClass.isAssignableFrom(pc.getDndClass().getClass())) {
						return true;
					}
				}
			}
			if (getRacesThatCanCastRitual() != null) {
				for (Class race : getRacesThatCanCastRitual()) {
					if (race.isAssignableFrom(pc.getRace().getClass())) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public abstract List<Class> getClassesThatCanCastRitual();
	
	@SuppressWarnings("rawtypes")
	public abstract List<Class> getRacesThatCanCastRitual();
}
