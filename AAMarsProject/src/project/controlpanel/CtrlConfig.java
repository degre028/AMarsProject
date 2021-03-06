package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;
import project.simulation.ConfigMaker;

/**
 * This class is the representation of the control panel configurations.
 * 
 * @author Rob
 *
 */
public class CtrlConfig extends CtrlPanel {
	
	final ListBox libListOfConfigs = new ListBox();
	final ModuleSet modset;
	
	public CtrlConfig(ModuleSet modSet) {
		this.modset = modSet;
		super.getHeaderLabel().setText("Configurations");
		
		final Button btnDuplicate = new Button("Duplicate");
	    final Button btnLoad = new Button("Load Active");
	    final Button btnDelete = new Button("Delete");
	    final Button btnGenerate = new Button("Generate");
		
		FlowPanel mainPanel = new FlowPanel();
		mainPanel.getElement().getStyle().setPaddingLeft(10.0, Unit.PX);
		mainPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
		mainPanel.getElement().getStyle().setPaddingRight(10.0, Unit.PX);
		
		FlexTable nameTable = new FlexTable();
		Label lbActiveConfig = new Label("Active Config: ");
		lbActiveConfig.getElement().getStyle().setFontSize(1.2, Unit.EM);
		Label lbNameOfActiveConfig = new Label("Live Config");
		
		nameTable.setWidget(0, 0, lbActiveConfig);
		nameTable.setWidget(1, 0, lbNameOfActiveConfig);
		
		
		FlowPanel editPanel = new FlowPanel();
		editPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
	    editPanel.add(new Label("Configuration Listing"));
		libListOfConfigs.setWidth("100%");
	    
	    libListOfConfigs.setVisibleItemCount(16);
	    editPanel.add(libListOfConfigs);
	    
	    libListOfConfigs.addChangeHandler( new ChangeHandler() {
			public void onChange(ChangeEvent ev) {
				if(libListOfConfigs.getSelectedIndex() > 0) {
					btnDelete.setEnabled(true);
					try {
					modset.getGui().getCurCanvas().drawPreviewConfig(modset.getConfig(libListOfConfigs.getSelectedIndex()));
					} catch (Exception ex) {
						Window.alert(ex.getMessage());
					}
				} else {
					modset.getGui().getCurCanvas().refreshDisplay();
					btnDelete.setEnabled(false);
				}
			}
		});
	    
	    FlowPanel buttonPanel = new FlowPanel();
	    buttonPanel.getElement().getStyle().setPaddingTop(10.0, Unit.PX);
	    
	    btnDuplicate.getElement().getStyle().setWidth(50.0, Unit.PCT);
	    btnLoad.getElement().getStyle().setWidth(50.0, Unit.PCT);
	    btnDelete.getElement().getStyle().setWidth(50.0, Unit.PCT);
	    btnGenerate.getElement().getStyle().setWidth(50.0, Unit.PCT);


	    
		
		mainPanel.add(nameTable);
		mainPanel.add(editPanel);
		buttonPanel.add(btnLoad);
		buttonPanel.add(btnDelete);
//		buttonPanel.add(btnDuplicate);
//		buttonPanel.add(btnGenerate);
		mainPanel.add(buttonPanel);
		btnDelete.setEnabled(false);
		
		FlowPanel generatePanel = new FlowPanel();
		generatePanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
		
		generatePanel.add(new Label("Generate a Config"));
		FlexTable genTable = new FlexTable();
		final RadioButton radioMin = new RadioButton("1", "Minimum Configuration");
		final RadioButton radioFull = new RadioButton("1", "Full Configuration");
		radioFull.setEnabled(false);
		radioMin.setValue(true);
		
		
		btnGenerate.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				if(radioMin.getValue()) {
					MarsConfiguration mc = new MarsConfiguration(modset);
					ConfigMaker cm = new ConfigMaker(modset);
					mc = cm.genMinimumConfig(modset);
					mc.setName("Minimum Configuration 1");
					modset.newConfig(mc);
					
					setupDisplay();
				}
					 
				}
		});
		
		btnDelete.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				modset.removeConfig(libListOfConfigs.getSelectedIndex() -1);
				setupDisplay();
			}
				
		});
		
		
		genTable.setWidget(0, 0, radioMin);
		genTable.setWidget(1, 0, radioFull);
		genTable.setWidget(2, 0, btnGenerate);
	
		
		
		
		generatePanel.add(genTable);
		mainPanel.add(generatePanel);
		super.getPanel().add(mainPanel);
		
		setupDisplay();
		
	}
	
	/**
	 * This method overrides the parent class method and is fired by
	 * changes in configurations or modules.
	 */
	public void setupDisplay() {
		libListOfConfigs.clear();
		libListOfConfigs.addItem("Live Config");
		for (int i = 1; i < modset.getConfigNumber(); i++) {
			libListOfConfigs.addItem(modset.getConfig(i).getName() + "  (" + modset.getConfig(i).getQuality() +
					"/100)");
		}
		
		
	}

}
