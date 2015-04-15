package project.gui;

import project.backend.Passwd;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Login extends Composite {
	
	public Login(final Passwd passwd){
		//Creating mainPanel and setting dimensions
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		mainPanel.getElement().getStyle().setBackgroundColor("#FFFF00");
		mainPanel.setHeight("768px");
		mainPanel.setWidth("1024px");
		
		//Creating the Inner Panel
		FlowPanel innerPanel = new FlowPanel();
		FlexTable userpassPanel = new FlexTable(); 
		userpassPanel.getElement().getStyle().setPadding(10.0, Unit.PX);


		
		//Creating Elements adding padding
		Label logLabel = new Label("Login Menu");
		logLabel.getElement().getStyle().setFontSize(2.9, Unit.EM);
		logLabel.getElement().getStyle().setPadding(10.0, Unit.PX);
		
		final Label userLabel = new Label("Username");
		userLabel.getElement().getStyle().setFontSize(1.9, Unit.EM);
		userLabel.getElement().getStyle().setPadding(8.0, Unit.PX);
		
		final TextBox userTest = new TextBox();
		userTest.setVisibleLength(23);
		
		Label passLabel = new Label("Password");
		passLabel.getElement().getStyle().setFontSize(1.9, Unit.EM);
		passLabel.getElement().getStyle().setPadding(8.0, Unit.PX);
		final PasswordTextBox passTest = new PasswordTextBox();
		
		Button loginButton = new Button("Login", new ClickHandler(){
			public void onClick(ClickEvent event){
				String passCheck = passTest.getText();
				String userCheck = userTest.getText();
				if(passwd.passChecker(userCheck, passCheck)){
					userLabel.setText("Rob");
				}
				//System.out.println("Hello World");
			}
		});
		
		loginButton.setSize("100px", "50px");
	    
		//CheckBox showTest = new CheckBox("Show Password?");

	    //Showing Password Event
	  	    
	    
		//Adding to innerPanel
		innerPanel.add(logLabel);
		userpassPanel.setWidget(0, 0, userLabel);
		userpassPanel.setWidget(0, 1, userTest);	
		userpassPanel.setWidget(1, 0, passLabel);
		userpassPanel.setWidget(1, 1, passTest);
		//mainPanel.add(showTest);
		
		mainPanel.add(innerPanel);
		innerPanel.add(userpassPanel);
		innerPanel.add(loginButton);

		
		// All composites must call initWidget() in their constructors.
	    initWidget(mainPanel);
	}
	
}
