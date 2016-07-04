package com.jimmie.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilenameWidget extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel filenameLabel;
	private JTextField filename;
	public void init() {
		filenameLabel = new JLabel("Filename:");
		filename = new JTextField("JimsBattlefield");
		LayoutManager gridLayout = new GridLayout(1,2);
		setLayout(gridLayout);
		add(filenameLabel);
		add(filename);
	}
	
	public String getFilename() {
		return filename.getText();
	}

}
