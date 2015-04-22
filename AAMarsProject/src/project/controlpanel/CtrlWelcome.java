package project.controlpanel;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Image;

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
	public CtrlWelcome() {
		super.getHeaderLabel().setText("Welcome to Mars");
		
		Image nasa = new Image("resources/images/marsPlanet.png");
		nasa.getElement().getStyle().setPaddingTop(10, Unit.PCT);
		nasa.getElement().getStyle().setWidth(90, Unit.PCT);;
		nasa.getElement().getStyle().setDisplay(Display.BLOCK);
		
		super.getPanel().add(nasa);
	}
	
	/**
	 * Override method must be done.
	 */
	public void setupDisplay() {
		
	}
}
