package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import project.backend.ModuleSet;

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
					Window.alert("Changed");
					try {
					modset.getGui().getCurCanvas().drawPreviewConfig(modset.getConfig(libListOfConfigs.getSelectedIndex()-1));
					} catch (Exception ex) {
						Window.alert(ex.getMessage());
					}
				}
			}
		});
	    
	    FlowPanel buttonPanel = new FlowPanel();
	    buttonPanel.getElement().getStyle().setPaddingTop(10.0, Unit.PX);
	    Button btnDuplicate = new Button("Duplicate");
	    Button btnLoad = new Button("Load Active");
	    Button btnDelete = new Button("Delete");
	    Button btnGenerate = new Button("Generate");
	    btnDuplicate.getElement().getStyle().setWidth(50.0, Unit.PCT);
	    btnLoad.getElement().getStyle().setWidth(50.0, Unit.PCT);
	    btnDelete.getElement().getStyle().setWidth(50.0, Unit.PCT);
	    btnGenerate.getElement().getStyle().setWidth(50.0, Unit.PCT);

		
		mainPanel.add(nameTable);
		mainPanel.add(editPanel);
		buttonPanel.add(btnLoad);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnDuplicate);
		buttonPanel.add(btnGenerate);
		mainPanel.add(buttonPanel);
		
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
		for (int i = 0; i < modset.getConfigNumber(); i++) {
			libListOfConfigs.addItem("--Config " + i);
		}
		
		
	}

}
