package project.gui;

import java.util.LinkedList;
import project.gui.TimerPanel;

import project.backend.ModuleSet;
import project.controlpanel.*;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * The button area class is a GUI type class that generates the bottom area of the main
 * screen.
 * @author Milan
 *
 */
public class ButtonArea extends Composite{

	LinkedList<Button> buttonList = new LinkedList<Button>();
	
	/**
	 * The constructor simply generates the GUI by adding buttons.
	 */
	public ButtonArea(ModuleSet modset) {
		FlowPanel panel = new FlowPanel();
		TimerPanel timer = new TimerPanel(System.currentTimeMillis());
		FlexTable ftable = new FlexTable();
		
		
		ftable.setWidget(0,0, makeButtons(modset));
		ftable.setWidget(0, 1, timer);
		
		panel.add(ftable);
		
		disableAllButtons(); //Disabled in constructor to enable in welcome.
		
        // All composites must call initWidget() in their constructors.
        initWidget(panel);
	}
	
	/**
	 * This field makes the buttons and returns them in a table to be added to the
	 * button area.
	 */
	private FlexTable makeButtons(ModuleSet modset) {
		FlexTable fTable = new FlexTable();
		
		//Generate Buttons
		buttonList.add(new MarsButton(true, "Add Module", new CtrlAddModule(true,modset),modset));
		buttonList.add(new MarsButton(true, "Edit Module", new CtrlAddModule(false,modset),modset));
		buttonList.add(new MarsButton(true, "Weather", new CtrlWeather(modset),modset));
		buttonList.add(new MarsButton(true, "Load Data", new CtrlLoadModules(modset), modset));
		buttonList.add(new MarsButton(true, "Configuration", new CtrlConfig(modset),modset));
		buttonList.add(new MarsButton(false, "Test Cases", new CtrlWelcome("BLANK", modset),modset));
		
		//Generate a regular button for logout.
		Button adamlogout = new Button("Logout");
		adamlogout.setWidth("120px");
		
		adamlogout.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				Window.Location.reload();

			}
		});
		
		buttonList.add(adamlogout);
		
		
	
		
		//Add buttons to table.
		for (int i = 0; i < buttonList.size(); i++) {
			fTable.setWidget(i%3, i/3, buttonList.get(i));
		}
		
		return fTable;
	}
	
	/**
	 * This method disables all Mars buttons in the button area.
	 * Note that master enabled is not required here.
	 */
	public void disableAllButtons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setEnabled(false);
			if (buttonList.get(i).getText().equals("Logout")) {
				buttonList.get(i).setEnabled(true);
			}
		}
		
	}
	
	/**
	 * This method disables all Mars buttons in the button area.
	 * Note that master enabled will override this method.
	 */
	public void enableAllButtons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setEnabled(true);
		}
	}
}
