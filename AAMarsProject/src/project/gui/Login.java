package project.gui;

import project.backend.ModuleSet;
import project.backend.Passwd;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * 
 * @author Rob
 *
 */
public class Login extends Composite {
	final SoundController soundController = new SoundController();
	private Sound sound;
	/**
	 * 
	 * @param passwd
	 * @param homePanel
	 */
	public Login(final Passwd passwd, final FlowPanel homePanel, final ModuleSet modset){
	
		
		//Creating mainPanel and setting dimensions
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		mainPanel.getElement().getStyle().setBackgroundColor("#FFFF00");
		mainPanel.setHeight("768px");
		mainPanel.setWidth("1024px");
		
		//Creating the Inner Panel
		FlowPanel innerPanel = new FlowPanel();
		innerPanel.getElement().getStyle().setPadding(350, Unit.PX);

		FlexTable userpassPanel = new FlexTable(); 
		userpassPanel.getElement().getStyle().setPadding(10.0, Unit.PX);

		//Popup When incorrect login
        final PopupPanel popup = new PopupPanel();
        popup.setVisible(false);
        final Label loginFail = new Label("Login Failed");
		popup.getElement().getStyle().setBackgroundColor("#FF0000");
        loginFail.getElement().getStyle().setPadding(12, Unit.PX);
        loginFail.getElement().getStyle().setFontSize(2, Unit.EM);

		//loginFail.setPixelSize(80, 100);
		
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
				//countInner++;
				String passCheck = passTest.getText();
				String userCheck = userTest.getText();
				if(passwd.passChecker(userCheck, passCheck)){
					homePanel.remove(0);
					homePanel.add(modset.getGui());
					sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_WAV_ADPCM,
					        "resources/audio/Log_in.wav");
					    sound.play();
				} else {
					//countInner = countClick + countInner;
					loginFail.setText("Login Attempt Failed ");
			        popup.setVisible(true);
				}
			}
		});

		
		

		loginButton.getElement().getStyle().setPadding(8.0, Unit.PX);
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
		
		//PopUp Panel Adding
        popup.add(loginFail);

		
		mainPanel.add(innerPanel);
		innerPanel.add(userpassPanel);
		innerPanel.add(loginButton);
		innerPanel.add(popup);

	
		// All composites must call initWidget() in their constructors.
	    initWidget(mainPanel);
	}
	
}
