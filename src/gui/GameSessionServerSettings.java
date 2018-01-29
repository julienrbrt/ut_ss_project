package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.*;

/**
 * This class for graphically let the user connect to a distant server and play the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class GameSessionServerSettings extends JPanel {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 8364504525735027082L;
	
	private JTextField ipField;
	private JTextField portField;
	JRadioButton rovo;
	JRadioButton rovovo;
	JRadioButton rovovovo;
	int gameWith = -1;
	
	String ipSet;
    String portSet;    
	int port = -1;
    InetAddress addr = null;   
	
	/**
	 * Create the panel.
	 */
	public GameSessionServerSettings() {
		setLayout(null);
		
		JLabel lblServerSettings = new JLabel("Server Settings:");
		lblServerSettings.setBounds(10, 12, 121, 23);
		add(lblServerSettings);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 49, 70, 15);
		add(lblIp);
		
		ipField = new JTextField();
		ipField.setBounds(102, 47, 232, 19);
		add(ipField);
		ipField.setColumns(10);
		ipField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				ipSet = ipField.getText();
			}
			
			public void focusLost(FocusEvent e) {
				ipSet = ipField.getText();
			    
				/**
			     *  check the IP-address
			     */
				if (ipSet.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Error: Spaces not allowed.");
				}
		        try {
		            addr = InetAddress.getByName(ipSet);
		        } catch (UnknownHostException uhe) {
		        	JOptionPane.showMessageDialog(null, "Error: host " + ipSet + " unknown");
		        }
			}	
		});
				
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 80, 70, 15);
		add(lblPort);
        
		portField = new JTextField();
		portField.setColumns(10);
		portField.setBounds(102, 78, 232, 19);
		add(portField);
		portField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				portSet = portField.getText();
			}
			
			public void focusLost(FocusEvent e) {
				portSet = portField.getText();
				
		        /**
		         *  parse the port
		         */
		        try {
		            port = Integer.parseInt(portSet);
		        } catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Error: A port should be an integer.");
		        }
			}
		});
		
		// Opponents settings buttons
		JLabel lblOponnentSettings = new JLabel("Opponents Settings:");
		lblOponnentSettings.setBounds(10, 109, 154, 23);
		add(lblOponnentSettings);
		
		rovo = new JRadioButton("1v1");
		rovo.setBounds(10, 149, 55, 23);
		add(rovo);
		rovo.addActionListener(new GameMode());
		
		rovovo = new JRadioButton("1v1v1");
		rovovo.setBounds(69, 149, 70, 23);
		add(rovovo);
		rovovo.addActionListener(new GameMode());
		
		rovovovo = new JRadioButton("1v1v1v1");
		rovovovo.setBounds(143, 149, 149, 23);
		add(rovovovo);
		rovovovo.addActionListener(new GameMode());

	}
	
	/**
	 * Class managing the user parameters to play offline
	 * (Versus who).
	 *
	 */
	public class GameMode implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
		
			// One selected max
			if (e.getSource() == rovo) {
				rovovo.setSelected(false);
				rovovovo.setSelected(false);
				gameWith = 2;
			} else if (e.getSource() == rovovo) {
				rovo.setSelected(false);
				rovovovo.setSelected(false);
				gameWith = 3;
			} else if (e.getSource() == rovovovo) {
				rovo.setSelected(false);
				rovovo.setSelected(false);
				gameWith = 4;
			}
		}
		
	}
	/**
	 * Getter of the information of the UI for Server Settings.
	 * @return settings of the user (port, game opponents and IP)
	 */
	public Object[] getServerSettings() {
		Object[] settings = {addr, port, gameWith};
		return settings;
	}
	
}
