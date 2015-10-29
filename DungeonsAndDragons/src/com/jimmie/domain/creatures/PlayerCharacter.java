package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.List;

import com.jimmie.domain.EpicDestiny;
import com.jimmie.domain.Feat;
import com.jimmie.domain.MagicItem;
import com.jimmie.domain.ParagonPath;
import com.jimmie.domain.classes.DndClass;

public class PlayerCharacter extends Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParagonPath paragonPath;
	private EpicDestiny epicDestiny;
	private int experiencePoints;
	private String playerName;
	private List<Feat> feats;
	private List<MagicItem> magicItems;

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
}
