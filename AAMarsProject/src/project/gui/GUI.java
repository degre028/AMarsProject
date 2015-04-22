package project.gui;

import project.backend.Configuration;
import project.backend.ModuleSet;
import project.canvaspanel.CnvsMap;
import project.canvaspanel.CnvsPanel;
import project.controlpanel.CtrlAddModule;
import project.controlpanel.CtrlPanel;
import project.controlpanel.CtrlWeather;
import project.controlpanel.CtrlWelcome;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
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
	private ModuleSet moduleSet;
	/**
	 * Global variable for the Flex Table that stores the four main
	 * quandrents of the GUI.
	 */
	private FlexTable fTable;
	private String user;
	CnvsPanel curCanvas;
	CtrlPanel defaultControl;
	
	
	
	/**
	 * Constructor for GUI, takes in the default configuration and assigns it, should instantiate the GUI classes
	 * and build the UI displayed in the onModuleLoad method (main method).  It does this with a series of
	 * private method calls.
	 */
	public GUI(ModuleSet modset, String username) {
		moduleSet = modset;
		fTable = new FlexTable();
		user = username;

	      
	      // All composites must call initWidget() in their constructors.
	      initWidget(makeMainPanel());
	      
	      //this.updateControlArea(defaultControl);
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
		defaultControl = new CtrlWelcome();
		
		fTable.setWidget(0,0, buildCanvasArea("800px","568px"));
		fTable.setWidget(0,1, buildControlArea("224px","568px",defaultControl));
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
		bArea.getElement().getStyle().setBackgroundColor("#DDDDDD");
		
		
		//Add the button area
		bArea.add(new ButtonArea(moduleSet));
		
		return bArea;
	}
	
	
	/**
	 * This method builds the canvas area and passes it back to the topFlexTable() method.
	 * @param width Width of the canvas panel.  This can be tweaked in the calling method.
	 * @param height Height of the canvas panel.  This can be tweaked in the calling method.
	 * @return FlowPAnel representation of the canvas.
	 */
	private FlowPanel buildCanvasArea(String width, String height) {
		FlowPanel canvasPanel = new FlowPanel();
		canvasPanel.getElement().getStyle().setHeight(568, Unit.PX);
		canvasPanel.getElement().getStyle().setWidth(800, Unit.PX);
		//canvasPanel.setHeight(height);
		//canvasPanel.setWidth(width);
		canvasPanel.getElement().getStyle().setBackgroundColor("#EEEEEE");

		//Setup Default Canvas
		curCanvas = new CnvsMap(moduleSet);
		
		canvasPanel.add(curCanvas);
		
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
		controlPanel.getElement().getStyle().setBackgroundColor("#EEEEEE");
		
		
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
		statusPanel.getElement().getStyle().setBackgroundColor("#DDDDDD");
		//statusPanel.getElement().getStyle().setVerticalAlign(Style.ALIGN_MIDDLE);
		

		Label headerLabel = new Label("Welcome, " + user);
		headerLabel.getElement().getStyle().setPaddingBottom(10.0, Unit.PCT);
		headerLabel.getElement().getStyle().setFontSize(1.2, Unit.EM);
		
		
		Image nasa = new Image("resources/images/NASAlogo.png");
		nasa.getElement().getStyle().setWidth(50, Unit.PCT);
		nasa.getElement().getStyle().setHeight(50, Unit.PCT);
		Image esa = new Image("resources/images/esa_logo.gif");
		esa.getElement().getStyle().setWidth(50, Unit.PCT);
		esa.getElement().getStyle().setHeight(40, Unit.PCT);
		esa.getElement().getStyle().setPaddingBottom(10.0, Unit.PCT);
		
		
		Button adamlogout = new Button("Logout");
		adamlogout.getElement().getStyle().setWidth(100, Unit.PCT);

		
		adamlogout.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				Window.Location.reload();

			}
		});
		
		statusPanel.add(headerLabel);
		statusPanel.add(nasa);
		statusPanel.add(esa);
		statusPanel.add(adamlogout);
		
	    
		return statusPanel;
	
	}
	
	/**
	 * Method used to change the control area in response to a button press.
	 * @param control
	 */
	public void updateControlArea(CtrlPanel control) {
		control.setupDisplay();
		fTable.setWidget(0,1, buildControlArea("224px","568px",control));
	}
	
	
	public void setUsername(String username) {
		user = "aa";
	}
	
}