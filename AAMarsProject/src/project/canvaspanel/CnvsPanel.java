package project.canvaspanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;


/**
 * Parent class for all the Canvas area panels.  Should not be
 * instantiated.
 * @author Rob
 *
 */
public class CnvsPanel extends Composite {

	private FlowPanel mainPanel = new FlowPanel();
	
	/**
	 * Constructor for CnvsPanel.  Set any master layout decisions for 
	 * the canvas panels here.
	 */
	public CnvsPanel() {
	
		//All composites must call initWidget(panel);
		this.initWidget(mainPanel);
	}
	
	/**
	 * Method should be overridden.  It's purpose is to provide the GUI
	 * with a way to refresh the current canvas panel from any method.
	 */
	public void refreshDisplay() {
		
	}
	
}
