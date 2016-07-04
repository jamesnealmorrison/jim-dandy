package com.jimmie.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BattlefieldSizeWidget extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel sizeLabel;
	private JTextField width;
	private JTextField height;
	private JButton applyButton;
	private EditorButtonsPanel editorPanel;

	public void setEditorPanel(EditorButtonsPanel editorPanel) {
		this.editorPanel = editorPanel;
	}

	public EditorButtonsPanel getEditorPanel() {
		return editorPanel;
	}

	public void init() {
		sizeLabel = new JLabel("W x H:");
		width = new JTextField();
		height = new JTextField();
		applyButton = new JButton("Apply");
		LayoutManager gridLayout = new GridLayout(1,4);
		setLayout(gridLayout);
		add(sizeLabel);
		add(width);
		add(height);
		add(applyButton);
		
		applyButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == applyButton) {
			if (editorPanel != null) {
				BattlefieldCreator.setImageSize(Integer.decode(width.getText()), Integer.decode(height.getText()));
			}
		}
	}


}
