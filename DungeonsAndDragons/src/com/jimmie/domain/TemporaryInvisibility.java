package com.jimmie.domain;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.Creature;

public class TemporaryInvisibility extends TemporaryEffect {
	List<Creature> targets;
	
	public List<Creature> getTargets() {
		return targets;
	}

	public void setTargets(List<Creature> targets) {
		this.targets = targets;
	}

	public void addTarget(Creature target) {
		if (targets == null) {
			targets = new ArrayList<Creature>();
		}
		/* Add a creature that you are invisible to. */
		targets.add(target);
	}
}
