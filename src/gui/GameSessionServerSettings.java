package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class GameSessionServerSettings extends JPanel {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 8364504525735027082L;
	private JTextField ipField;
	private JTextField portField;

	/**
	 * Create the panel.
	 */
	public GameSessionServerSettings() {
		setLayout(null);
		
		JLabel lblServerSettings = new JLabel("Server Settings:");
		lblServerSettings.setBounds(10, 12, 121, 23);
		add(lblServerSettings);
		
		ipField = new JTextField();
		ipField.setBounds(102, 47, 232, 19);
		add(ipField);
		ipField.setColumns(10);
		
		portField = new JTextField();
		portField.setColumns(10);
		portField.setBounds(102, 78, 232, 19);
		add(portField);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 49, 70, 15);
		add(lblIp);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 80, 70, 15);
		add(lblPort);
		
		JLabel lblOponnentSettings = new JLabel("Opponents Settings:");
		lblOponnentSettings.setBounds(10, 109, 154, 23);
		add(lblOponnentSettings);
		
		JRadioButton rovo = new JRadioButton("1v1");
		rovo.setBounds(10, 149, 55, 23);
		add(rovo);
		
		JRadioButton rovovo = new JRadioButton("1v1v1");
		rovovo.setBounds(69, 149, 70, 23);
		add(rovovo);
		
		JRadioButton rovovovo = new JRadioButton("1v1v1v1");
		rovovovo.setBounds(143, 149, 149, 23);
		add(rovovovo);

	}
}
