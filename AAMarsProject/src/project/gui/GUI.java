package project.gui;

import project.backend.Configuration;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
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
	 * Constructor for GUI, takes in the default configuration and assigns it, should instantiate the GUI classes
	 * and build the UI displayed in the onModuleLoad method (main method).  It does this with a series of
	 * private method calls.
	 */
	public GUI(Configuration passedConfig) {
		liveConfig = passedConfig;
		
		//Code for GUI here.
//	      VerticalPanel panel = new VerticalPanel();
//	      panel.add(new TextBox());
//	      
//	      panel.add(new CheckBox());
//	      panel.add(new TextBox());
	      
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
	      //Add flex table.
	      panel.add(topFlexTable());
	      //Add button rows.
	      panel.add(bottomButtonArea());
	      
	      
	      return panel;
	}
	
	/**
	 * This method builds the top part of the main area.  It adds the canvas area and the control area
	 * to a 2x1 flex table and returns it.
	 * @return ftable A flex table containing the canvas area and the control area.
	 */
	private FlexTable topFlexTable() {
		FlexTable fTable = new FlexTable();
		
		DecoratorPanel canvasPanel = new DecoratorPanel();
		canvasPanel.setHeight("400px");
		canvasPanel.setWidth("500px");
		canvasPanel.getElement().getStyle().setBackgroundColor("#333333");
		
		fTable.setWidget(0,0,canvasPanel);
		
		return fTable;
	}
	
	/**
	 * This method builds the bottom part of the main area.  It contains the buttons.
	 * @return bArea A flow panel containing the bottom area.
	 */
	private FlowPanel bottomButtonArea() {
		FlowPanel bArea = new FlowPanel();
		bArea.add(new Button("Button Area"));
		
		return bArea;
	}
	
}
