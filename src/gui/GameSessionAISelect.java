package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import game.player.Color;
import game.player.ComputerPlayer;
import game.player.Player;
import game.player.RandomStrategy;
import game.player.SmartStrategy;

//Observer - Observable
import java.util.Observable;
import java.util.Observer;

public class GameSessionAISelect extends JPanel {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 6469334982209609065L;

	/**
	 * Define buttons.
	 */
	JLabel lblAiSettings;
	JLabel lblRandomAi;
	JRadioButton oneR;
	JRadioButton twoR;
	JRadioButton threeR;
	JLabel lblSmartAi;
	JRadioButton oneS;
	JRadioButton twoS;
	JRadioButton threeS;
	
	// Player handling
	Player[] aiPlayers = new Player[3];
	Player[] cleanAiPlayers;
	Color color = Color.NONEE;	//initiated to make static calls
	
	/**
	 * Create the panel.
	 */
	public GameSessionAISelect() {
		setLayout(null);
		
		lblAiSettings = new JLabel("AI Settings: ");
		lblAiSettings.setBounds(10, 12, 121, 23);
		add(lblAiSettings);
		
		lblRandomAi = new JLabel("Random AI:");
		lblRandomAi.setBounds(10, 49, 91, 19);
		add(lblRandomAi);
		
		oneR = new JRadioButton("1");
		oneR.setBounds(115, 47, 33, 23);
		add(oneR);
		oneR.addActionListener(new AI());
		
		twoR = new JRadioButton("2");
		twoR.setBounds(152, 47, 33, 23);
		add(twoR);
		twoR.addActionListener(new AI());
		
		threeR = new JRadioButton("3");
		threeR.setBounds(189, 47, 33, 23);
		add(threeR);
		threeR.addActionListener(new AI());
		
		lblSmartAi = new JLabel("Smart AI:");
		lblSmartAi.setBounds(10, 80, 72, 21);
		add(lblSmartAi);
				
		oneS = new JRadioButton("1");
		oneS.setBounds(115, 78, 33, 23);
		add(oneS);
		oneS.addActionListener(new AI());

		twoS = new JRadioButton("2");
		twoS.setBounds(152, 78, 33, 23);
		add(twoS);
		twoS.addActionListener(new AI());
		
		threeS = new JRadioButton("3");
		threeS.setBounds(189, 79, 33, 23);
		add(threeS);
		threeS.addActionListener(new AI());
	}
	
	// AI handler - Observable / Observer
	public class AI extends Observable implements ActionListener, Runnable {
		
		Player player2;
		Player player3;
		Player player4;
		
		public void actionPerformed(ActionEvent e) {
			
			// Manage max Random AI
			if (e.getSource() == oneR) {
				twoR.setSelected(false);
				threeR.setSelected(false);
				threeS.setSelected(false);
						
			} else if (e.getSource() == twoR) {
				oneR.setSelected(false);
				threeR.setSelected(false);
				twoS.setSelected(false);
				threeS.setSelected(false);
			} else if (e.getSource() == threeR) {
				oneR.setSelected(false);
				twoR.setSelected(false);
				oneS.setSelected(false);
				twoS.setSelected(false);
				threeS.setSelected(false);
			}
			
			// Manage max Smart AI
			if (e.getSource() == oneS) {
				twoS.setSelected(false);
				threeS.setSelected(false);
				threeR.setSelected(false);
			} else if (e.getSource() == twoS) {
				oneS.setSelected(false);
				threeS.setSelected(false);
				twoR.setSelected(false);
				threeR.setSelected(false);
			} else if (e.getSource() == threeS) {
				oneS.setSelected(false);
				twoS.setSelected(false);
				oneR.setSelected(false);
				twoR.setSelected(false);
				threeR.setSelected(false);
			}
			
			// Game 2 Players
			if (oneR.isSelected()) {
				player2 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE,
						Color.GREEN);
			} else if (oneS.isSelected()) {
				player2 = new ComputerPlayer(new SmartStrategy(), Color.BLUEE,
						Color.GREEN);
			} else if (oneR.isSelected() && oneS.isSelected()) { // Game 3 Players
				player2 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE, Color.YELLO);
				player3 = new ComputerPlayer(new SmartStrategy(), Color.GREEN, Color.YELLO);
			} else if (twoR.isSelected()) {
				player2 = new ComputerPlayer(new RandomStrategy(),  Color.BLUEE, Color.YELLO);
				player3 = new ComputerPlayer(new RandomStrategy(), Color.GREEN, Color.YELLO);
			} else if (twoS.isSelected()) {
				player2 = new ComputerPlayer(new SmartStrategy(), Color.BLUEE, Color.YELLO);
				player3 = new ComputerPlayer(new SmartStrategy(), Color.GREEN, Color.YELLO);
			} else if (twoR.isSelected() && oneS.isSelected()) { // Game 4 Players
				player2 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE);
				player3 = new ComputerPlayer(new RandomStrategy(), Color.GREEN);
				player4 = new ComputerPlayer(new SmartStrategy(), Color.YELLO);
			} else if (twoS.isSelected() && oneR.isSelected()) {
				player2 = new ComputerPlayer(new SmartStrategy(), Color.BLUEE);
				player3 = new ComputerPlayer(new SmartStrategy(), Color.GREEN);
				player4 = new ComputerPlayer(new RandomStrategy(), Color.YELLO);
			} else if (threeR.isSelected()) {
				player2 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE);
				player3 = new ComputerPlayer(new RandomStrategy(), Color.GREEN);
				player4 = new ComputerPlayer(new RandomStrategy(), Color.YELLO);
			} else if (threeS.isSelected()) {
				player2 = new ComputerPlayer(new SmartStrategy(), Color.BLUEE);
				player3 = new ComputerPlayer(new SmartStrategy(), Color.GREEN);
				player4 = new ComputerPlayer(new SmartStrategy(), Color.YELLO);
			} else { // Not selected
	        	JOptionPane.showMessageDialog(null,
	        			"A minimum of two players is required to play offline.");
			}
			
			aiPlayers[0] = player2;
			aiPlayers[1] = player3;
			aiPlayers[2] = player4;
			
			int count = 0;
			
			for (int x = 0; x < aiPlayers.length; x++) {
				if (aiPlayers[x] != null) {
					count++;
				}
			}
			
			cleanAiPlayers = new Player[count];
			count = 0;
			
			for (int x = 0; x < aiPlayers.length; x++) {
				if (aiPlayers[x] != null) {
					cleanAiPlayers[count] = aiPlayers[x];
					count++;
				}
			}
			
		}
		
	    public void run() {
			setChanged();
            notifyObservers(cleanAiPlayers);
	    }

	}

}
