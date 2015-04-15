package project.gui;

import project.backend.Configuration;
import project.controlpanel.CtrlAddModule;
import project.controlpanel.CtrlPanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Large, primary class for the GUI aspects of the lab.  
 * Each GUI should contain and build all visual elements of the
 * MARS simulator.
 * @author Milan
 */
public class GUI extends Composite {
	/**
	 * Global variable for the live Configuration.  
	 * Modules and such are referred to by public methods in liveConfig.
	 */
	private Configuration liveConfig;
	/**
	 * Global variable for the Flex Table that stores the four main
	 * quandrents of the GUI.
	 */
	private FlexTable fTable;
	
	
	/**
	 * Constructor for GUI, takes in the default configuration and assigns it, should instantiate the GUI classes
	 * and build the UI displayed in the onModuleLoad method (main method).  It does this with a series of
	 * private method calls.
	 */
	public GUI(Configuration passedConfig) {
		liveConfig = passedConfig;
		fTable = new FlexTable();
		

	      
	      // All composites must call initWidget() in their constructors.
	      initWidget(makeMainPanel());
	}
	
	/**
	 * This method creates the main panel.  The main panel is a 2x1 panel containing a 1x2 flex table in 
	 * the top div and a flow layout of buttons in the bottom div.
	 * @return panel A 2x1 Vertical panel.
	 */
	private VerticalPanel makeMainPanel() {
	    VerticalPanel panel = new VerticalPanel();
		

		
		FlowPanel controlPanel = new FlowPanel();
		controlPanel.getElement().getStyle().setBackgroundColor("#000066");
		controlPanel.setHeight("500px");
		controlPanel.setWidth("224px");
		
		fTable.setWidget(0,0, buildCanvasArea("800px","568px"));
		fTable.setWidget(0,1, buildControlArea("224px","568px",new CtrlPanel()));
		fTable.setWidget(1,0, buildButtonArea("800px","200px"));
		fTable.setWidget(1,1, buildStatusArea("214px","190px"));
	      
	    panel.add(fTable);  
		
	    return panel;
	}
	

	
	/**
	 * This method builds the bottom part of the main area.  It contains the buttons.
	 * @return bArea A flow panel containing the bottom area.
	 */
	private FlowPanel buildButtonArea(String width, String height) {
		FlowPanel bArea = new FlowPanel();
		bArea.setHeight(height);
		bArea.setWidth(width);
		bArea.getElement().getStyle().setBackgroundColor("#77FF77");
		
		//Dummy Code for area.
		//Label dumbLabel = new Label("Button Area");
		//bArea.add(dumbLabel);
		
		//Add the button area
		bArea.add(new ButtonArea(this));
		
		return bArea;
	}
	
	
	/**
	 * This method builds the canvas area and passes it back to the topFlexTable() method.
	 * @param width Width of the canvas panel.  This can be tweaked in the calling method.
	 * @param hieght Height of the canvas panel.  This can be tweaked in the calling method.
	 * @return FlowPAnel representation of the canvas.
	 */
	private FlowPanel buildCanvasArea(String width, String height) {
		FlowPanel canvasPanel = new FlowPanel();
		canvasPanel.setHeight(height);
		canvasPanel.setWidth(width);
		canvasPanel.getElement().getStyle().setBackgroundColor("#FF7777");
		
		//Dummy Code for area.
		Label dumbLabel = new Label("Canvas Area");
		canvasPanel.add(dumbLabel);
		
		return canvasPanel;
	
	}
	
	
	/**
	 * This method builds the control area and passes it back to the topFlexTable() method.
	 * @param width Width of the control panel.  This can be tweaked in the calling method.
	 * @param hieght Height of the control panel.  This can be tweaked in the calling method.
	 * @return FlowPAnel representation of the control panel area.
	 */
	private CtrlPanel buildControlArea(String width, String height, CtrlPanel controlPanel) {
		controlPanel.setHeight(height);
		controlPanel.setWidth(width);
		controlPanel.getElement().getStyle().setBackgroundColor("#7777FF");
		
		
		
		return controlPanel;
	
	}
	
	/**
	 * This method builds the status area and passes it back to the topFlexTable() method.
	 * @param width Width of the status panel.  This can be tweaked in the calling method.
	 * @param hieght Height of the status panel.  This can be tweaked in the calling method.
	 * @return FlowPanel representation of the control panel area.
	 */
	private FlowPanel buildStatusArea(String width, String height) {
		FlowPanel statusPanel = new FlowPanel();
		statusPanel.getElement().getStyle().setPadding(5.0, Unit.PX);
		statusPanel.setHeight(height);
		statusPanel.setWidth(width);
		statusPanel.getElement().getStyle().setBackgroundColor("#FFFF77");
		
		//Dummy Code for area.
		Label dumbLabel = new Label("Status Area");
		statusPanel.add(dumbLabel);
		
	    // Make a new list box, adding a few items to it.
	    ListBox lb = new ListBox();
	    lb.addItem("Plain = 40");
	    lb.addItem("Dormitory = 20");
	    lb.addItem("etc.");
	    
	    
	    
	    lb.setWidth("100%");
	    
	    lb.setVisibleItemCount(8);
		
	    statusPanel.add(lb);
	    
		return statusPanel;
	
	}
	
	/**
	 * Method used to change the control area in response to a button press.
	 * @param control
	 */
	public void updateControlArea(CtrlPanel control) {
		fTable.setWidget(0,1, buildControlArea("224px","568px",control));
	}
	
	
}