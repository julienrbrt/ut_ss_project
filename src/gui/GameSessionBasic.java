package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GameSessionBasic extends JPanel {
	
	private static final long serialVersionUID = 1194727121599067942L;
	private JTextField txtUsername;
	
	String username = "Change Me";

	/**
	 * Create the panel.
	 */
	public GameSessionBasic(JFrame frameGS, JPanel panel) {
		
		JLabel lblRingzz = new JLabel("RINGZZ");
		lblRingzz.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("Start");
		
		txtUsername = new JTextField();
		txtUsername.setText(username);
		txtUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				username = txtUsername.getText();
				// Add button only if username change
				if (username.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Spaces are not allowed in username.");
				}
				btnNewButton.addActionListener(new ALGSStartGame(frameGS, panel, username));
			} }
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
