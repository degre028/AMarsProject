package project.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import project.controlpanel.*;


/**
 * The mars button class extends the standard GWT button class and allows additional
 * functionality, principly a "master enable" switch.
 * @author Milan
 *
 */
public class MarsButton extends Button{
	/**
	 * This flag is set in the constructor and forces a button to stay disabled if off.
	 */
	private boolean masterEnabled;
	
	/**
	 * Each button holds a corresponding control panel.
	 */
	private final CtrlPanel control;
	
	/**
	 * The constructor sets the masterEnabled flag.
	 * @param masterEnabled boolean representation of the master enable.
	 * @param text String to set the button text to.
	 */
	public MarsButton(boolean masterEnabled, String text, final CtrlPanel controlP, final GUI gui) {
		this.masterEnabled = masterEnabled;
		this.control = controlP;
		
		super.setEnabled(masterEnabled);
		
		super.setText(text);
		
		super.setWidth("120px");
		
		//Set the buttons click event to display control panel.
		super.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				control.setupDisplay();
				gui.updateControlArea(control);

			}
		});
	}
	
	/**
	 * Overrides the setEnabled method of the button class to account for master enable.
	 */
	@Override
	public void setEnabled(boolean newEnable) {
		if(masterEnabled) {
			super.setEnabled(newEnable);
		}
	}
}
