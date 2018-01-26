package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

/**
 * This class for lauching the correct game (Online/Offline) depending on the parameters.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class GameSessionBasic extends JPanel {
	
	private static final long serialVersionUID = 1194727121599067942L;
	private JTextField txtUsername;
	
	String username = "Player1";

	/**
	 * Create the panel.
	 */
	public GameSessionBasic(JFrame frameGS, JPanel aiSelect, JPanel serverSetting, JCheckBox onlineStatus) {
		
		JLabel lblRingzz = new JLabel("RINGZZ");
		lblRingzz.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("Start");
		
		txtUsername = new JTextField();
		txtUsername.setText(username);
		txtUsername.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				username = txtUsername.getText();
				for (ActionListener al : btnNewButton.getActionListeners()) {
					btnNewButton.removeActionListener(al);
				}
			}
			
			public void focusLost(FocusEvent e) {
				username = txtUsername.getText();
				if (username.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Spaces are not allowed in username.");
				}
				// Check if Offline mode on
				if (onlineStatus.isSelected()) {
					// Add button only if username change
					btnNewButton.addActionListener(new ALGSStartGame(frameGS, aiSelect, username));
				} else {
					btnNewButton.addActionListener(new ALGSStartOnlineGame(frameGS, serverSetting, username));
				}
			}	
		}
		);
		txtUsername.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblRingzz)
					.addGap(99)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(99)
					.addComponent(btnNewButton))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblRingzz))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(btnNewButton))
		);
		setLayout(groupLayout);

	}
}
