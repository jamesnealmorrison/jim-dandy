package com.jimmie.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EditorButtonsPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton rotateButton;
	BattlefieldSizeWidget sizeWidget;
	JButton undoButton;
	FilenameWidget filenameWidget;
	JButton wildernessBackgroundButton;
	JButton dungeonBackgroundButton;
	JButton cityBackgroundButton;
	JButton saveButton;

	private EditorPanel editorPanel;

	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	public void setEditorPanel(EditorPanel editorPanel) {
		this.editorPanel = editorPanel;
	}

	public void init() {
		rotateButton = new JButton("Rotate");
		rotateButton.addActionListener(this);
		sizeWidget = new BattlefieldSizeWidget();
		undoButton = new JButton("Undo");
		filenameWidget = new FilenameWidget();
		wildernessBackgroundButton = new JButton("Wilderness Background");
		dungeonBackgroundButton = new JButton("Dungeon Background");
		cityBackgroundButton = new JButton("City Background");
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		sizeWidget.init();
		filenameWidget.init();
		
		LayoutManager gridLayout = new GridLayout(8,1);
		Dimension dimension = new Dimension(500,180);
		setPreferredSize(dimension);
		setLayout(gridLayout);

		sizeWidget.setEditorPanel(this);
		
		add(rotateButton);
		add(sizeWidget);
		add(undoButton);
		add(wildernessBackgroundButton);
		add(dungeonBackgroundButton);
		add(cityBackgroundButton);
		add(filenameWidget);
		add(saveButton);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == rotateButton) {
			BattlefieldCreator.rotateSelectedImage();
		} else if (action.getSource() == saveButton) {
			BattlefieldCreator.saveImage(filenameWidget.getFilename());
		}
	}

}
