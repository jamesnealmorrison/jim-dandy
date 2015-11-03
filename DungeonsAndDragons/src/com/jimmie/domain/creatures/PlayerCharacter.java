package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.List;

import com.jimmie.domain.EpicDestiny;
import com.jimmie.domain.Feat;
import com.jimmie.domain.MagicItem;
import com.jimmie.domain.ParagonPath;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Ranger;

public class PlayerCharacter extends Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParagonPath paragonPath;
	private EpicDestiny epicDestiny;
	private int experiencePoints;
	private String playerName;
	private List<Feat> feats;
	private List<MagicItem> magicItems;
	public int getHitPointsPerLevelGained() {
		return hitPointsPerLevelGained;
	}

	public void setHitPointsPerLevelGained(int hitPointsPerLevelGained) {
		this.hitPointsPerLevelGained = hitPointsPerLevelGained;
	}

	private PowerSource powerSource;
	private int hitPointsPerLevelGained = 0;

	public PowerSource getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(PowerSource powerSource) {
		this.powerSource = powerSource;
	}

	public PlayerCharacter(Race r, DndClass c) {
		race = r;
		dndClass = c;
	}

	public ParagonPath getParagonPath() {
		return paragonPath;
	}
	public void setParagonPath(ParagonPath paragonPath) {
		this.paragonPath = paragonPath;
	}
	public EpicDestiny getEpicDestiny() {
		return epicDestiny;
	}
	public void setEpicDestiny(EpicDestiny epicDestiny) {
		this.epicDestiny = epicDestiny;
	}
	public int getExperiencePoints() {
		return experiencePoints;
	}
	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public List<Feat> getFeats() {
		return feats;
	}
	public void setFeats(List<Feat> feats) {
		this.feats = feats;
	}
	public List<MagicItem> getMagicItems() {
		return magicItems;
	}
	public void setMagicItems(List<MagicItem> magicItems) {
		this.magicItems = magicItems;
	}

	/*
	 * Use this method to learn a new feat.
	 */
	public void selectFeat() {
//		List<String> choices = new ArrayList<String>();
		/*
		 * TODO: Put in code to check if we already know that feat or not, and if it can be selected multiple times.
		 */
		
		/* Some races get to choose human feats. */
// JIM!!!! DON'T FORGET THAT HALF ELVES CAN CHOOSE HUMAN FEATS!!!
//		if ((Human.class.isInstance(race)) || (HalfElf.class.isInstance(race))) {
//			choices.add("ActionSurge");
//		}
//		if ((getDexterity() >= 15) && (Ranger.class.isInstance(dndClass))) {
//		}
	}
}
