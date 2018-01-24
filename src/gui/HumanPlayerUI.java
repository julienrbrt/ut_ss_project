package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

import game.*;
import game.player.*;
import game.player.Color;
import gui.*;

public class HumanPlayerUI extends Player {
	
	private Color firstColor;
	private Color secondColor;
	private final int playerNumber;
		
	// GUI handling
	private final JFrame frameHP = new JFrame();
	ColorUI colorUI;
	Container c = frameHP.getContentPane();
	JButton[] rings;
	Image ringsImage;
	JButton hint;
	JCheckBox hintOnOff;
	
	// Game handling
	private boolean showSBase = true;
	private final int maxRings = 4; 
	private int xPlace;
	private int yPlace;
	private int base;
	private Color color;
	private int ringSize;
	private boolean valid = false;
	
	// Constructor for one color
	public HumanPlayerUI(String name, Color firstColor, int playerNumber) {
		super(name, firstColor, playerNumber);
		this.firstColor = firstColor;
		this.playerNumber = playerNumber;
	}
	
	// Constructor for two colors
	public HumanPlayerUI(String name, Color firstColor, Color secondColor, int playerNumber) {
		super(name, firstColor, secondColor, playerNumber);
		this.firstColor = firstColor;
		this.secondColor = secondColor;
		this.playerNumber = playerNumber;
	}
	
	public void init() {
				
		if (secondColor != null) {
			c.setLayout(new GridLayout(0, 2));
			rings = new JButton[(maxRings * 2) + 3];
		} else {
			c.setLayout(new GridLayout(0, 1));
			rings = new JButton[maxRings + 2];
		}
		
		int sBasePos = rings.length - 1;
		
		// First rings color generation
		for (int x = 0; x < maxRings; x++) {
			rings[x] = new JButton();
			rings[x].setPreferredSize(new Dimension(128, 128));
			rings[x].setContentAreaFilled(false);
			rings[x].setMargin(new Insets(0, 0, 0, 0));
			rings[x].setBackground(java.awt.Color.WHITE);
			rings[x].setOpaque(true);
			colorUI = new ColorUI(firstColor, false, x);
			ringsImage = colorUI.getColorUI();
			rings[x].setIcon(new ImageIcon(ringsImage));
			rings[x].addActionListener(new PlaceRing(firstColor, x));
			c.add(rings[x]);
		}
		
		// Adding of base firstColor
		rings[maxRings + 1] = new JButton();
		rings[maxRings + 1].setPreferredSize(new Dimension(128, 128));
		rings[maxRings + 1].setContentAreaFilled(false);
		rings[maxRings + 1].setMargin(new Insets(0, 0, 0, 0));
		rings[maxRings + 1].setBackground(java.awt.Color.WHITE);
		rings[maxRings + 1].setOpaque(true);
		colorUI = new ColorUI(firstColor, true, 0);
		ringsImage = colorUI.getColorUI();
		rings[maxRings + 1].setIcon(new ImageIcon(ringsImage));
		rings[maxRings + 1].addActionListener(new PlaceRing(firstColor, maxRings + 1));
		c.add(rings[maxRings + 1]);
		
		// Second color generation
		if (secondColor != null) {
			int size = 0;
			for (int x = maxRings + 1; x < (maxRings * 2) + 1; x++) {
				rings[x] = new JButton();
				rings[x].setPreferredSize(new Dimension(128, 128));
				rings[x].setContentAreaFilled(false);
				rings[x].setMargin(new Insets(0, 0, 0, 0));
				rings[x].setBackground(java.awt.Color.WHITE);
				rings[x].setOpaque(true);
				colorUI = new ColorUI(secondColor, false, size);
				ringsImage = colorUI.getColorUI();
				rings[x].setIcon(new ImageIcon(ringsImage));
				rings[x].addActionListener(new PlaceRing(secondColor, size));
				c.add(rings[x]);
				size++;
			}
			
			// Adding of base secondColor
			rings[(maxRings * 2) + 1] = new JButton();
			rings[(maxRings * 2) + 1].setPreferredSize(new Dimension(128, 128));
			rings[(maxRings * 2) + 1].setContentAreaFilled(false);
			rings[(maxRings * 2) + 1].setMargin(new Insets(0, 0, 0, 0));
			rings[(maxRings * 2) + 1].setBackground(java.awt.Color.WHITE);
			rings[(maxRings * 2) + 1].setOpaque(true);
			colorUI = new ColorUI(secondColor, true, 0);
			ringsImage = colorUI.getColorUI();
			rings[(maxRings * 2) + 1].setIcon(new ImageIcon(ringsImage));
			rings[(maxRings * 2) + 1].addActionListener(new PlaceRing(secondColor, (maxRings * 2) + 1));
			c.add(rings[(maxRings * 2) + 1]);
		}
		
		// Adding Default place
		if (showSBase) {
			rings[sBasePos] = new JButton();
			rings[sBasePos].setPreferredSize(new Dimension(128, 128));
			rings[sBasePos].setContentAreaFilled(false);
			rings[sBasePos].setMargin(new Insets(0, 0, 0, 0));
			rings[sBasePos].setBackground(java.awt.Color.WHITE);
			rings[sBasePos].setOpaque(true);
			colorUI = new ColorUI(null, true, 0);
			ringsImage = colorUI.getColorUI();
			rings[sBasePos].setIcon(new ImageIcon(ringsImage));
			rings[sBasePos].addActionListener(new PlaceRing(Color.SBASE, 0));
			c.add(rings[sBasePos]);
		}
		
	}
	
	public class PlaceRing implements ActionListener {

		int[] choosen;
		Color ringColor;
		int ringSize;
		
		public PlaceRing(Color color, int size) {
			this.ringColor = color;
			this.ringSize = size;
		}
		
		public void actionPerformed(ActionEvent ev) {
			System.out.println(ringSize + "" + ringColor);
		}
		
	}
	
	public int[] determineBase(Board board) {
		
    	JOptionPane.showMessageDialog(null, "Place the starting base.");
		
		init();
		frameHP.pack();
		frameHP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameHP.setSize(350, 800);
		frameHP.setTitle("Ringzz - Placement");
		frameHP.setResizable(false);
		frameHP.setVisible(true);
		
        while (!valid) {
        	// TODO if too slow do automatic move
        	
        }
        
        // Reset valid and never show starting base again
        valid = false;
        showSBase = false;
        
  	    // Close frame
  	    frameHP.dispose();
        
  	    int[] choice = {xPlace, yPlace};
  	    return choice;
	} 
	
	public Object[] determineMove(Board board, int colorAmount) {
	
		init();
		frameHP.pack();
		frameHP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameHP.setSize(350, 800);
		frameHP.setTitle("Ringzz - Placement");
		frameHP.setResizable(false);
		frameHP.setVisible(true);
		
        while (!valid) {
        	// TODO if too slow do automatic move
        }
        
        // Reset valid
        valid = false;
        
  	    // Close frame
  	    frameHP.dispose();
        
        Object[] choice = {xPlace, yPlace, base, ringSize, color};
        return choice;   
	}
	
	public static void main(String[] arg0) {
		HumanPlayerUI test = new HumanPlayerUI("test", Color.YELLO, Color.GREEN, 2);
		test.determineBase(null);
	}
	
}