package de.schaf.space;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.ResizableFrame;

public class LoginFrame extends ResizableFrame {

	private final EditField userField;
	private final EditField passField;
	private final Button loginButton;
	
	public LoginFrame() {
		setTitle("Login");

		userField = new EditField();
		userField.setTheme("usernamefield");
		
		passField = new EditField();
		passField.setTheme("passwordfield");
		
		loginButton = new Button("Login");
		
		DialogLayout DLayout = new DialogLayout();
		
		DialogLayout.Group HLogin = DLayout.createParallelGroup(userField,passField,loginButton);
		DialogLayout.Group VLogin = DLayout.createSequentialGroup(userField,passField,loginButton);
		
		DLayout.setHorizontalGroup(HLogin);
		DLayout.setVerticalGroup(VLogin);
		
		this.setMinSize(100, 0);
		this.setSize(200,00);
		
		this.setResizableAxis(ResizableAxis.HORIZONTAL);
		
		add(DLayout);
	}

}
