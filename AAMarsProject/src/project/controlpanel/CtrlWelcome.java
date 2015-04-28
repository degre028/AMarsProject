package project.controlpanel;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

import project.backend.ModuleSet;

/**
 * This class is the introduction control panel area
 * @author Rob
 *
 */
public class CtrlWelcome extends CtrlPanel {

	/**
	 * Constructor for CtrlWelcome
	 * Lays out the CtrlWelcome
	 */
	public CtrlWelcome(String username, final ModuleSet modset) {
		super.getHeaderLabel().setText("Welcome to Mars,");
		
		FlexTable table = new FlexTable();
		
		table.getElement().getStyle().setWidth(100, Unit.PCT);
		
		Label lbUserName = new Label(Character.toUpperCase(username.charAt(0)) + username.substring(1));
		lbUserName.getElement().getStyle().setWidth(100, Unit.PCT);
		lbUserName.getElement().getStyle().setFontSize(2.0, Unit.EM);
		lbUserName.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		
		Image nasa = new Image("resources/images/marsPlanet.png");
		nasa.getElement().getStyle().setPaddingTop(10, Unit.PCT);
		nasa.getElement().getStyle().setPaddingLeft(10, Unit.PCT);
		nasa.getElement().getStyle().setPaddingRight(10, Unit.PCT);
		nasa.getElement().getStyle().setWidth(80, Unit.PCT);
		nasa.getElement().getStyle().setDisplay(Display.BLOCK);
		
		Label lbLoaded = new Label();
		
		if (modset.getCount("all") > 0) {
			lbLoaded.setText("Your previous session have been loaded.");
		}
		else {
			lbLoaded.setText("There was no previous session on this computer");
		}
		
		
		lbLoaded.getElement().getStyle().setWidth(90.0, Unit.PCT);
		lbLoaded.getElement().getStyle().setPadding(5.0, Unit.PCT);
		lbLoaded.getElement().getStyle().setPaddingBottom(10.0, Unit.PCT);
		lbLoaded.getElement().getStyle().setFontSize(1.5, Unit.EM);
		lbLoaded.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		
		Button startButton = new Button("Start!");
		startButton.getElement().getStyle().setWidth(50.0, Unit.PCT);
		startButton.getElement().getStyle().setMarginLeft(25.0, Unit.PCT);
		startButton.getElement().getStyle().setMarginRight(25.0, Unit.PCT);
		
		//Set the buttons click event to display control panel.
		startButton.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				try {
				modset.getGui().updateCanvasArea();
				modset.getGui().updateControlArea(modset.getGui().getDefaultControl());
				modset.getGui().getButtonArea().enableAllButtons();
				} catch (Exception e) {
					Window.alert(e.getMessage());
				}
			}
		});
		
		
		table.setWidget(0,0,lbUserName);
		table.setWidget(1,0,nasa);
		table.setWidget(2,0,lbLoaded);
		table.setWidget(3, 0, startButton);
		super.getPanel().add(table);
		
		
	}
	
	/**
	 * Override method must be done.
	 */
	public void setupDisplay() {
		
	}
}
