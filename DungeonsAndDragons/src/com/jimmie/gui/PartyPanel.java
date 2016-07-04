package com.jimmie.gui;

//import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;
import com.jimmie.domain.creatures.Creature;

@Component
public class PartyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Creature> party;

	public PartyPanel() {
	}
	
	public void init(List<Creature> creatures) {
		party = creatures;
		Dimension dimension = new Dimension(CreaturePanel.WIDTH,(party.size()*(CreaturePanel.HEIGHT+5)));
		this.setPreferredSize(dimension);
//		LayoutManager gridBagLayout = new GridBagLayout();
//		this.setLayout(gridBagLayout );		

		for (Creature creature : party) {
			CreaturePanel creaturePanel = new CreaturePanel(creature);
			add(creaturePanel);			
		}		
	}
	
	public PartyPanel(List<Creature> creatures) {
		party = creatures;
		Dimension dimension = new Dimension(CreaturePanel.WIDTH,(party.size()*(CreaturePanel.HEIGHT+5)));
		this.setPreferredSize(dimension);
//		LayoutManager gridBagLayout = new GridBagLayout();
//		this.setLayout(gridBagLayout );

		for (Creature creature : party) {
			CreaturePanel creaturePanel = new CreaturePanel(creature);
			add(creaturePanel);			
		}
	}

	public void refreshAll() {
		for (java.awt.Component component : getComponents()) {
			if (CreaturePanel.class.isAssignableFrom(component.getClass())) {
				((CreaturePanel) component).updateData();
				component.repaint();
			}
		}
	}
}
