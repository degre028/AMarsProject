package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import project.backend.MarsConfiguration;
import project.backend.MarsModule;
import project.backend.ModuleSet;
import project.simulation.ConfigMaker;
import project.simulation.ConfigStats;

/**
 * This class is the GUI representation of the route
 * control area.  It is responsible for guiding
 * the rover to each module.
 * @author Rob
 *
 */
public class CtrlRoute extends CtrlPanel {

	final private ModuleSet modset;
	private MarsConfiguration config = null;
	final Label lbNameOfActiveConfig;
	
	final ListBox libModules = new ListBox();
	
	/**
	 * The constructor outlines the control panel.
	 * @param modset
	 */
	public CtrlRoute(ModuleSet modset) {
		this.modset = modset;
		
		if(modset.getActiveConfig() > -1) {
			config = modset.getConfig(modset.getActiveConfig());
		}
		
		super.getHeaderLabel().setText("Route");
		FlowPanel mainPanel = new FlowPanel();
		FlexTable mainTable = new FlexTable();
		
		mainPanel.getElement().getStyle().setPaddingLeft(10.0, Unit.PX);
		mainPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
		mainPanel.getElement().getStyle().setPaddingRight(10.0, Unit.PX);
		
		FlexTable nameTable = new FlexTable();
		Label lbActiveConfig = new Label("Active Config: ");
		lbActiveConfig.getElement().getStyle().setFontSize(1.2, Unit.EM);
		lbNameOfActiveConfig = new Label("Live Config");
		
		nameTable.setWidget(0,0,lbActiveConfig);
		nameTable.setWidget(1, 0, lbNameOfActiveConfig);
		

		FlowPanel editPanel = new FlowPanel();
		editPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
	    editPanel.add(new Label("Module Movements"));
	    libModules.setWidth("100%");
	    libModules.setVisibleItemCount(16);
	    editPanel.add(libModules);
		
//		mainTable.setWidget(0, 0, nameTable);
//		mainTable.setWidget(1, 0, editPanel);
		mainPanel.add(nameTable);
		mainPanel.add(editPanel);
		super.getPanel().add(mainPanel);
	}
	
	/**
	 * This method is called when the button is clicked
	 * and updates the display to reflect the changes in the
	 * modset.
	 */
	public void setupDisplay() {
		libModules.clear();
		
		if(modset.getActiveConfig() > -1) {
			config = modset.getConfig(modset.getActiveConfig());
			lbNameOfActiveConfig.setText(config.getName());
			
			for (int i = 0; i < modset.getCount("all"); i++) {
		    	MarsModule mod = modset.getModule(i);
				
				StringBuilder strbuild = new StringBuilder();
		    	strbuild.append("(");
		    	strbuild.append(config.getXCoord(i));
		    	strbuild.append(",");
		    	strbuild.append(config.getYCoord(i));
		    	strbuild.append(") - ");
		    	strbuild.append(mod.getType());
		    	
		    	ConfigStats stats = new ConfigStats(modset, config);
		    	
		    	
		    	strbuild.append(", ");
		    	strbuild.append(config.getMoves(i) + " moves");
		    	
		    	libModules.addItem(strbuild.toString());
			}
		}
		else {
			lbNameOfActiveConfig.setText("Live Config");
		}
		
		
	}
	
}
