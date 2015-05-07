package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import project.backend.ModuleSet;

public class CtrlLoadModules extends CtrlPanel {
	
	final ListBox libListOfGPS = new ListBox();
	final ModuleSet modset;
	
	/**
	 * Default constructor for CtrlLoadModules
	 */
	public CtrlLoadModules(final ModuleSet modset) {
		
		this.modset = modset;
		super.getHeaderLabel().setText("Load GPS Data");
		
		
		FlowPanel mainPanel = new FlowPanel();
		
		mainPanel.getElement().getStyle().setPaddingLeft(10.0, Unit.PX);
		mainPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
		mainPanel.getElement().getStyle().setPaddingRight(10.0, Unit.PX);
		
		FlexTable mainTable = new FlexTable();
		
		FlowPanel editPanel = new FlowPanel();
		editPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
	    editPanel.add(new Label("Lists of Modules to Load:"));
	    
	    libListOfGPS.setWidth("100%");
	    libListOfGPS.setVisibleItemCount(16);
	    
	    Button btnLoad = new Button("Load Module Set");
	    FlowPanel buttonPanel = new FlowPanel();
	    buttonPanel.getElement().getStyle().setPaddingTop(10.0, Unit.PX);
	    btnLoad.getElement().getStyle().setWidth(100.0, Unit.PCT);
	    
		//(Cancel) Button Handler
		btnLoad.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				try {
				modset.clearAll();
				modset.getStorage().updateModSet(libListOfGPS.getSelectedIndex());
				
				Timer t = new Timer() {
					public void run() {
						modset.getGui().updateCanvasArea();
					}
				};
				
				t.schedule(1000);
				
				}
				catch (Exception ex) {
					//Window.alert(ex.getMessage());
				}
			}
		});
	    
	    for(int i = 1; i < modset.getStorage().getModsetCounter() + 1; i++) {
	    	libListOfGPS.addItem("Test Case " + i);
	    }
	    
	    editPanel.add(libListOfGPS);
	    buttonPanel.add(btnLoad);
	    
	    FlowPanel emergencyPanel = new FlowPanel();
	    emergencyPanel.getElement().getStyle().setPaddingTop(50.0, Unit.PX);
	    emergencyPanel.add(new Label("Load Other Test:"));
	    final TextBox txtOther = new TextBox();
	    FlowPanel loadOtherButtonPanel = new FlowPanel();
	    loadOtherButtonPanel.getElement().getStyle().setPaddingTop(10.0, Unit.PX);
	    final Button btnLoadOther = new Button("Load Other");
	    
	    
	    btnLoadOther.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				try {
				modset.clearAll();
				modset.getStorage().updateModSet(Integer.parseInt(txtOther.getText())-1);
				
				Timer t = new Timer() {
					public void run() {
						modset.getGui().updateCanvasArea();
					}
				};
				
				t.schedule(1000);
				
				}
				catch (Exception ex) {
					//Window.alert(ex.getMessage());
				}
			}
		});
		
		emergencyPanel.add(txtOther);
		loadOtherButtonPanel.add(btnLoadOther);
		emergencyPanel.add(loadOtherButtonPanel);
	    
	    mainPanel.add(editPanel);
	    mainPanel.add(buttonPanel);
	    mainPanel.add(emergencyPanel);
		super.getPanel().add(mainPanel);

	}
	
	
	/**
	 * Override method for setup display.
	 */
	public void setupDisplay() {
		libListOfGPS.clear();
		
	    for(int i = 1; i < modset.getStorage().getModsetCounter() + 1; i++) {
	    	libListOfGPS.addItem("Test Case " + i);
	    }
	}

}
