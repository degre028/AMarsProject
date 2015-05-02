package project.canvaspanel;

import project.backend.MarsConfiguration;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;


/**
 * Parent class for all the Canvas area panels.  Should not be
 * instantiated.
 * @author Rob
 *
 */
public class CnvsPanel extends ScrollPanel {

	private FlowPanel mainPanel = new FlowPanel();
	
	/**
	 * Constructor for CnvsPanel.  Set any master layout decisions for 
	 * the canvas panels here.
	 */
	public CnvsPanel() {
		mainPanel.getElement().getStyle().setWidth(100.0, Unit.PX);
		mainPanel.setWidth("100px");
		mainPanel.setHeight("100px");
		mainPanel.getElement().getStyle().setHeight(100.0, Unit.PX);
		
		//All composites must call initWidget(panel);
		this.setWidth("100%");
		this.setHeight("100%");
		this.add(mainPanel);
	}
	
	public FlowPanel getPanel() {
		return mainPanel;
	}
	
	/**
	 * Method should be overridden.  It's purpose is to provide the GUI
	 * with a way to refresh the current canvas panel from any method.
	 */
	public void refreshDisplay() {
		
	}
	
	/**
	 * Override method to due with superimposing.
	 * 
	 */
	public void drawPreviewConfig(MarsConfiguration configu) {
		Window.alert("Working");
	}
	
}
