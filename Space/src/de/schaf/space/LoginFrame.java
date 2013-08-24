package de.schaf.space;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import TWLSlick.BasicTWLGameState;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.ResizableFrame;
import de.schaf.space.net.client.ClientNetworkController;
import de.schaf.space.net.packet.*;

public class LoginFrame extends ResizableFrame {

	private final EditField userField;
	private final EditField passField;
	private final Button loginButton;
	
	private final Label infoLabel;
	
	private final BasicTWLGameState parentState;
	
	public LoginFrame(BasicTWLGameState State) {
		parentState = State;
		
		setTitle("Login");

		userField = new EditField();
		userField.setTheme("usernamefield");
		
		passField = new EditField();
		passField.setTheme("passwordfield");
		
		loginButton = new Button("Login");
		
		infoLabel = new Label(" ");
		infoLabel.setTheme("infolabel");
		
		loginButton.addCallback(new Runnable() { public void run() { doLogin(userField.getText(),passField.getText()); } });
		
		
		
		DialogLayout DLayout = new DialogLayout();
		
		DialogLayout.Group HLogin = DLayout.createParallelGroup(userField,passField,loginButton,infoLabel);
		DialogLayout.Group VLogin = DLayout.createSequentialGroup(userField,passField,loginButton,infoLabel);
		
		DLayout.setHorizontalGroup(HLogin);
		DLayout.setVerticalGroup(VLogin);
		
		this.setMinSize(100, 0);
		this.setSize(200,00);
		
		this.setResizableAxis(ResizableAxis.HORIZONTAL);
		
		add(DLayout);
		
		
		Game.ClientNetworkController.setListener(new Listener() {
			public void connected (Connection connection) {
				infoLabel.setText("Connected");
			}
			
			public void disconnected (Connection connection) {
				infoLabel.setText("Disconnected");
			}
			
			public void received (Connection connection, Object object) {
				if (object instanceof PacketServerLoginSuccessfull) {
					infoLabel.setText("Login successfull!");
				}
				if (object instanceof PacketServerLoginFailed) {
					PacketServerLoginFailed P = (PacketServerLoginFailed)object;
					infoLabel.setText("Login failed: "+P.Reason);
				}
			}
		});
		
		
		
	}
	
	public synchronized void doLogin(String Username, String Password) {
		if (Username.trim().isEmpty() || Password.trim().isEmpty()) {
			infoLabel.setText("Username/Password is empty!");
			return;
		}
		
		infoLabel.setAutoSize(false);
		
		//loginButton.setEnabled(false);
		infoLabel.setText("Connecting...");

		Game.ClientNetworkController.login(Username,Password);

		
	}

}
