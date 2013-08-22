package de.schaf.space;

import TWLSlick.BasicTWLGameState;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.ResizableFrame;
import de.schaf.space.net.client.NetworkController;
import de.schaf.space.net.client.NetworkErrorListener;

public class LoginFrame extends ResizableFrame implements NetworkErrorListener {

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
	}
	
	public synchronized void doLogin(String Username, String Password) {
		if (Username.trim().isEmpty() || Password.trim().isEmpty()) {
			infoLabel.setText("Username/Password is empty!");
			return;
		}
		
		infoLabel.setAutoSize(false);
		
		//loginButton.setEnabled(false);
		infoLabel.setText("Connecting...");

		
		NetworkController networkController = NetworkController.getInstance();
		
		networkController.setErrorListener(this);
		
		networkController.createUser(Username, Password);
		
		networkController.connect();
		
	}

	@Override
	public void connectionError(String info) {
		infoLabel.setText(info);
		loginButton.setEnabled(true);
	}

}
