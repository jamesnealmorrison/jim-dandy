package com.jimmie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BattlefieldCreator extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer SQUARE_SIZE = 200;
	public static final double SHRINK_PERCENT = 0.25;
	public static final Integer MAX_SIZE = 40;
	static JFrame frame = null;
	JPanel topPanel = null;	
	private PartSelectionPanel partSelectionPanel;
	private BattlefieldViewerPanel battlefieldViewerPanel;
	private EditorPanel editorPanel;	
	private JScrollPane partSelectionScrollPane;
	private JScrollPane battlefieldViewerScrollPane;
	private static Image battlefieldImage;
	private static Image wildernessBackgroundImage;
	private static Image selectedImage;
	private static File wildernessBackgroundImageSrc;
	private static BattlefieldCreator newBattlefieldCreator;
	private static File outputFile;
	
	
	public void init() {
		partSelectionPanel = new PartSelectionPanel();
		battlefieldViewerPanel = new BattlefieldViewerPanel();
		editorPanel = new EditorPanel();
		editorPanel.setBattlefieldCreator(this);
		
		topPanel = new JPanel();
		LayoutManager borderLayout = new BorderLayout();
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(1890,975);
		topPanel.setPreferredSize(preferredSize);
		topPanel.setLayout(borderLayout);
		partSelectionPanel.init();
		battlefieldViewerPanel.init();
		editorPanel.init();
		
		battlefieldViewerScrollPane = new JScrollPane();
		Dimension battlefieldViewerScrollPaneDimension = new Dimension(400,400);
		battlefieldViewerScrollPane.setPreferredSize(battlefieldViewerScrollPaneDimension);
		battlefieldViewerScrollPane.createHorizontalScrollBar();
		battlefieldViewerScrollPane.createVerticalScrollBar();
		battlefieldViewerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		battlefieldViewerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		battlefieldViewerScrollPane.add(battlefieldViewerPanel);
		battlefieldViewerScrollPane.setViewportView(battlefieldViewerPanel);
		
		partSelectionScrollPane = new JScrollPane();
		Dimension partSelectionScrollPaneDimension = new Dimension(1890,400);
		partSelectionScrollPane.setPreferredSize(partSelectionScrollPaneDimension);
		partSelectionScrollPane.createHorizontalScrollBar();
		partSelectionScrollPane.createVerticalScrollBar();
		partSelectionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		partSelectionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		partSelectionScrollPane.add(partSelectionPanel);
		partSelectionScrollPane.setViewportView(partSelectionPanel);
		
		topPanel.add(editorPanel, BorderLayout.EAST);
		topPanel.add(battlefieldViewerScrollPane, BorderLayout.CENTER);
		topPanel.add(partSelectionScrollPane, BorderLayout.SOUTH);
		add(topPanel);
		
	}
	
	public static void main(String[] args) {
		frame = new JFrame("Dungeon Creator");
		wildernessBackgroundImageSrc  = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\TheWilderness\\BackgroundTile.jpg");
		try {
			wildernessBackgroundImage = ImageIO.read(wildernessBackgroundImageSrc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BattlefieldCreator.setImageSize(MAX_SIZE, MAX_SIZE);
		newBattlefieldCreator = new BattlefieldCreator();
		newBattlefieldCreator.init();
		newBattlefieldCreator.setOpaque(true);
		
		frame.setContentPane(newBattlefieldCreator);

		
		
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		}
				);

		//newBattlefieldCreator.run();
	}
	
	private static void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void refreshAll()
	{
		if (newBattlefieldCreator != null) {
			newBattlefieldCreator.refresh();
		}
	}


	private void refresh() {
		if (battlefieldViewerPanel != null) {
			battlefieldViewerPanel.repaint();
		}
		if (editorPanel != null) {
			editorPanel.refreshAll();
		}
	}

	public static Image getBattlefieldImage() {
		return battlefieldImage;
	}

	public static void setBattlefieldImage(Image image) {
		BattlefieldCreator.battlefieldImage = image;
	}

	public static Image getWildernessBackgroundImage() {
		return wildernessBackgroundImage;
	}

	public static void setWildernessBackgroundImage(Image wildernessBackgroundImage) {
		BattlefieldCreator.wildernessBackgroundImage = wildernessBackgroundImage;
	}

	public static File getWildernessBackgroundImageSrc() {
		return wildernessBackgroundImageSrc;
	}

	public static void setWildernessBackgroundImageSrc(File wildernessBackgroundImageSrc) {
		BattlefieldCreator.wildernessBackgroundImageSrc = wildernessBackgroundImageSrc;
	}

	public static void setImageSize(Integer width, Integer height) {
		if (width > MAX_SIZE) {
			width = MAX_SIZE;
		}
		if (height > MAX_SIZE) {
			height = MAX_SIZE;
		}
		
		battlefieldImage = new BufferedImage(width*SQUARE_SIZE, height*SQUARE_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = battlefieldImage.getGraphics();
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				graphics.drawImage(wildernessBackgroundImage, (row*SQUARE_SIZE), (col*SQUARE_SIZE), null);
			}
		}
		
		if (newBattlefieldCreator != null) {
			newBattlefieldCreator.invalidate();
			newBattlefieldCreator.getBattlefieldViewerPanel().repaint();
		}
	}


	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public PartSelectionPanel getPartSelectionPanel() {
		return partSelectionPanel;
	}

	public void setPartSelectionPanel(PartSelectionPanel partSelectionPanel) {
		this.partSelectionPanel = partSelectionPanel;
	}

	public BattlefieldViewerPanel getBattlefieldViewerPanel() {
		return battlefieldViewerPanel;
	}

	public void setBattlefieldViewerPanel(BattlefieldViewerPanel battlefieldViewerPanel) {
		this.battlefieldViewerPanel = battlefieldViewerPanel;
	}

	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	public void setEditorPanel(EditorPanel editorPanel) {
		this.editorPanel = editorPanel;
	}

	public JScrollPane getPartSelectionScrollPane() {
		return partSelectionScrollPane;
	}

	public void setPartSelectionScrollPane(JScrollPane partSelectionScrollPane) {
		this.partSelectionScrollPane = partSelectionScrollPane;
	}

	public JScrollPane getBattlefieldViewerScrollPane() {
		return battlefieldViewerScrollPane;
	}

	public void setBattlefieldViewerScrollPane(JScrollPane battlefieldViewerScrollPane) {
		this.battlefieldViewerScrollPane = battlefieldViewerScrollPane;
	}

	public static BattlefieldCreator getNewBattlefieldCreator() {
		return newBattlefieldCreator;
	}

	public static void setNewBattlefieldCreator(BattlefieldCreator newBattlefieldCreator) {
		BattlefieldCreator.newBattlefieldCreator = newBattlefieldCreator;
	}

	public static Image shrinkImage(Image image) {
		if (image != null) {
			int newWidth = (int) (image.getWidth(null)*SHRINK_PERCENT);
			int newHeight = (int) (image.getHeight(null)*SHRINK_PERCENT);
			Image newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(image, 0, 0, newWidth, newHeight, null);
			return newImage;
		}
		return null;
	}

	public static void setSelectedImage(Image image) {
		selectedImage = image;
		
	}
	public static Image getSelectedImage() {
		return selectedImage;
	}

	public static void rotateSelectedImage() {
		// Which is bigger, width or height?
		int maxSize = 0;
		boolean taller = false;
		int smallerLength = 0;
		if (selectedImage.getWidth(null) > selectedImage.getHeight(null)) {
			maxSize = selectedImage.getWidth(null);
			taller = false;
			smallerLength = selectedImage.getHeight(null);
		} else {
			maxSize = selectedImage.getHeight(null);
			taller = true;
			smallerLength = selectedImage.getWidth(null);
		}
		
		Image tempSquareImage = new BufferedImage(maxSize, maxSize, BufferedImage.TYPE_INT_RGB);
		Graphics tempSquareG = tempSquareImage.getGraphics();
		tempSquareG.drawImage(selectedImage, (maxSize/2)-(selectedImage.getWidth(null)/2), (maxSize/2)-(selectedImage.getHeight(null)/2), null);
		
		Image tempSquareRotatedImage = new BufferedImage(maxSize, maxSize, BufferedImage.TYPE_INT_RGB);
		Graphics tempSquareRotatedG = tempSquareRotatedImage.getGraphics();
		if (Graphics2D.class.isAssignableFrom(tempSquareRotatedG.getClass())) {
			Graphics2D tempSquareRotatedG2D = (Graphics2D) tempSquareRotatedG;
			tempSquareRotatedG2D.translate(maxSize/2, maxSize/2);
			tempSquareRotatedG2D.rotate(Math.toRadians(90));
			tempSquareRotatedG2D.translate(-(maxSize/2), -(maxSize/2));
			tempSquareRotatedG2D.drawImage(tempSquareImage,0,0,null);
		}
		
		selectedImage = new BufferedImage(selectedImage.getHeight(null),selectedImage.getWidth(null),BufferedImage.TYPE_INT_RGB);
		
		Graphics g = selectedImage.getGraphics();
		if (Graphics2D.class.isAssignableFrom(g.getClass())) {
			Graphics2D g2d = (Graphics2D) g;
			
			if (taller == true) {
				g2d.translate(0,-((selectedImage.getWidth(null)/2)-(smallerLength/2)));
			} else {
				g2d.translate(-((selectedImage.getHeight(null)/2)-(smallerLength/2)),0);
			}
			
			g2d.drawImage(tempSquareRotatedImage,0,0,null);
		}
		
		refreshAll();
	}

	public static void saveImage(String filename) {
		outputFile  = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\"+filename);
		try {
			ImageIO.write((BufferedImage) battlefieldImage, "jpg", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
