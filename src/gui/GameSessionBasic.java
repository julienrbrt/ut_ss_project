package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GameSessionBasic extends JPanel {
	
	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 1194727121599067942L;
	private JTextField txtUsername;

	/**
	 * Create the panel.
	 */
	public GameSessionBasic() {
		
		JLabel lblRingzz = new JLabel("RINGZZ");
		lblRingzz.setFont(new Font("Dialog", Font.BOLD, 20));
		
		txtUsername = new JTextField();
		txtUsername.setText("Player1");
		txtUsername.setColumns(10);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
