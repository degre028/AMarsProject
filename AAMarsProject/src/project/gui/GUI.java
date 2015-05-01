package project.gui;

import project.backend.MarsConfiguration;
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
	private FlexTable fTableRight;
	private FlexTable fTableLeft;
	private String user;
	private ButtonArea buttonArea;
	CnvsPanel curCanvas;
	CtrlPanel defaultControl;
	private TimerPanel timerPanel;
	
	
	
	/**
	 * Constructor for GUI, takes in the default configuration and assigns it, should instantiate the GUI classes
	 * and build the UI displayed in the onModuleLoad method (main method).  It does this with a series of
	 * private method calls.
	 */
	public GUI(ModuleSet modset, String username) {
		moduleSet = modset;
		fTableRight = new FlexTable();
		user = username;
		fTableLeft = new FlexTable();
		timerPanel = new TimerPanel(modset);

	      
	      // All composites must call initWidget() in their constructors.
	      initWidget(makeMainPanel());
	      
	}
	
	

	/**
	 * This method creates the main panel.  The main panel is a 2x1 panel containing a 1x2 flex table in 
	 * the top div and a flow layout of buttons in the bottom div.
	 * @return panel A 2x1 Vertical panel.
	 */
	private FlowPanel makeMainPanel() {
	    //VerticalPanel panel = new VerticalPanel();
		FlowPanel panel = new FlowPanel();
		
		FlowPanel controlPanel = new FlowPanel();
		controlPanel.getElement().getStyle().setBackgroundColor("#000066");
		controlPanel.setHeight("500px");
		controlPanel.setWidth("224px");
		defaultControl = new CtrlAddModule(false,moduleSet);
		
		fTableLeft.setWidget(0,0, buildCanvasArea("1136px","568px"));
		fTableRight.setWidget(0,1, buildControlArea("224px","718px",new CtrlWelcome(user, moduleSet)));
		fTableLeft.setWidget(1,0, buildButtonArea("1436px","120px"));
		fTableRight.setWidget(1,1, buildStatusArea("224px","120px"));
		
	    
		fTable = new FlexTable();
		fTable.setWidget(0,0,fTableLeft);
		fTable.setWidget(0,1,fTableRight);
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
		
		buttonArea = new ButtonArea(moduleSet);
		
		FlexTable ftable = new FlexTable();
		
		ftable.setWidget(0, 0, buttonArea);
		ftable.setWidget(0, 1, timerPanel);
		//Add the button area
		bArea.add(ftable);
		
		
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
		canvasPanel.getElement().getStyle().setHeight(718, Unit.PX);
		canvasPanel.getElement().getStyle().setWidth(1436, Unit.PX);

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
		//statusPanel.getElement().getStyle().setPadding(5.0, Unit.PX);
		statusPanel.setHeight(height);
		statusPanel.setWidth(width);
		statusPanel.getElement().getStyle().setBackgroundColor("#DDDDDD");
		//statusPanel.getElement().getStyle().setVerticalAlign(Style.ALIGN_MIDDLE);
		

		Label headerLabel = new Label("User: " + user);
		//headerLabel.getElement().getStyle().setPaddingBottom(10.0, Unit.PX);
		headerLabel.getElement().getStyle().setFontSize(1.2, Unit.EM);
		
		FlowPanel imagePanel = new FlowPanel();
		Image nasa = new Image("resources/images/NASAlogo.png");
		nasa.getElement().getStyle().setWidth(40, Unit.PCT);
		nasa.getElement().setAttribute("height","auto");;
		Image esa = new Image("resources/images/esa_logo.gif");
		esa.getElement().getStyle().setWidth(50, Unit.PCT);
		esa.getElement().setAttribute("height","auto");;
		//esa.getElement().getStyle().setPaddingBottom(5.0, Unit.PCT);
		
		imagePanel.add(nasa);
		imagePanel.add(esa);
		
		imagePanel.getElement().getStyle().setPaddingLeft(7.0, Unit.PCT);
		
		statusPanel.add(headerLabel);
		statusPanel.add(imagePanel);
		
		
	    
		return statusPanel;
	
	}
	
	/**
	 * Method used to change the control area in response to a button press.
	 * @param control
	 */
	public void updateControlArea(CtrlPanel control) {
		control.setupDisplay();
		fTableRight.setWidget(0,1, buildControlArea("224px","718px",control));
	}
	
	public void updateCanvasArea() {
		curCanvas.refreshDisplay();
	}
	
	public CnvsPanel getCurCanvas() {
		return curCanvas;
	}
	
	public void setUsername(String username) {
		user = "aa";
	}
	
	public CtrlPanel getDefaultControl() {
		return defaultControl;
	}
	
	public ButtonArea getButtonArea() {
		return buttonArea;
	}
	
	public TimerPanel getTimerPanel() {
		return timerPanel;
	}
}