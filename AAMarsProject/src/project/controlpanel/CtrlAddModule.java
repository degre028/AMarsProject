package project.controlpanel;

import project.backend.MarsModule;
import project.backend.ModuleSet;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.xml.internal.ws.api.server.Module;

/**
 * Control panel subclass for the Add Module control panel.
 * @author Rob
 *
 */
public class CtrlAddModule extends CtrlPanel{
	
	final ListBox listModules;
	final ModuleSet modules;
	Button btnSubmit;
	Button btnCancel;
	boolean isNew;
	final TextBox tbX;
	final TextBox tbY;
	final TextBox tbID;
	final TextBox tbCond;
	final TextBox tbOri;
	final TextBox tbType;
	
	final ListBox ltOri;
	final ListBox ltCond;
	
	
	/**
	 * Constructor builds an Add or Edit module panel.
	 * @param isNew if true, the "Add Module" button was clicked.  If false, the "Edit Module"
	 * button was clicked.
	 */
	public CtrlAddModule(boolean isNew, final ModuleSet modules) {
		
		this.isNew = isNew;
		
		if(isNew) super.getHeaderLabel().setText("Add Module");
		else super.getHeaderLabel().setText("Edit Module");
		
		
		FlowPanel mainPanel = new FlowPanel();
		FlexTable coordTable = new FlexTable();
		
		//Edit ListBox
		listModules = new ListBox();
		this.modules = modules;
		
		//Buttons
		btnSubmit = new Button("Save");
		btnCancel = new Button("Delete");
		
		//Panel display modifications
//		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		mainPanel.getElement().getStyle().setWidth(95, Unit.PCT);
		mainPanel.getElement().getStyle().setHeight(90, Unit.PCT);
		mainPanel.getElement().getStyle().setPadding(4.0, Unit.PX);
//		mainPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
		
		
		Label lbX = new Label("X Coord.:");
		labelMaker(lbX);
		Label lbY = new Label("Y Coord.:");
		labelMaker(lbY);
		Label lbID = new Label("ID:");
		labelMaker(lbY);
		Label lbOri = new Label("Orientation:");
		labelMaker(lbOri);
		Label lbCond = new Label("Condition:");
		labelMaker(lbCond);
		Label lbType = new Label("Type:");
		labelMaker(lbType);
		
		tbX = new TextBox();
		textBoxMaker(tbX,1);
		tbY = new TextBox();
		textBoxMaker(tbY,1);
		tbID = new TextBox();
		textBoxMaker(tbID,1);
		tbOri = new TextBox();
		textBoxMaker(tbOri,1);
		tbCond = new TextBox();
		textBoxMaker(tbCond,1);
		tbType = new TextBox();
		textBoxMaker(tbType,1);
		tbType.setText("");
		tbType.setEnabled(false);
		
		//Combo Boxes
		ltOri = new ListBox();
		ltCond = new ListBox();
		
		ltOri.addItem("0 Turns");
		ltOri.addItem("1 Turns");
		ltOri.addItem("2 Turns");
		
		ltCond.addItem("Good");
		ltCond.addItem("Damaged");
		ltCond.addItem("Broken");
		
		btnSubmit.getElement().getStyle().setWidth(50, Unit.PCT);
		btnCancel.getElement().getStyle().setWidth(50, Unit.PCT);
		
		coordTable.getColumnFormatter().setWidth(0, "50%");
		coordTable.getColumnFormatter().setWidth(1, "50%");
		coordTable.getElement().getStyle().setPaddingTop(10.0, Unit.PX);
		coordTable.getElement().getStyle().setPaddingBottom(10.0, Unit.PX);
		
		coordTable.setWidget(0, 0, lbX);
		coordTable.setWidget(0, 1, tbX);
		coordTable.setWidget(1, 0, lbY);
		coordTable.setWidget(1, 1, tbY);
		coordTable.setWidget(2, 0, lbID);
		coordTable.setWidget(2, 1, tbID);
		coordTable.setWidget(3, 0, lbOri);
		coordTable.setWidget(3, 1, ltOri);
		coordTable.setWidget(4, 0, lbCond);
		coordTable.setWidget(4, 1, ltCond);
		coordTable.setWidget(5, 0, lbType);
		coordTable.setWidget(5, 1, tbType);
		coordTable.setWidget(6, 0, lbCond);
		coordTable.setWidget(6, 1, tbCond);
		
		
		//Button Handlers
		//Code button handler
		if(isNew) {
		btnSubmit.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				try {
					MarsModule newMod = new MarsModule(Integer.parseInt(tbX.getText()),
							Integer.parseInt(tbY.getText()),
							Integer.parseInt(tbID.getText()),
							ltCond.getValue(ltCond.getSelectedIndex()),
							ltOri.getSelectedIndex());
					modules.addModule(newMod);
					listBoxUpdater(listModules,modules);
				}
				catch (NumberFormatException e) {
					Window.alert("Invalid Input!");
				}

			}
		});
		}
		else {
			btnSubmit.addClickHandler( new ClickHandler() {
				public void onClick(ClickEvent ev) {
					modules.removeModule(listModules.getSelectedIndex());
					
					MarsModule newMod = new MarsModule(Integer.parseInt(tbX.getText()),
							Integer.parseInt(tbY.getText()),
							Integer.parseInt(tbID.getText()),
							ltCond.getValue(ltCond.getSelectedIndex()),
							ltOri.getSelectedIndex());
					modules.addModule(newMod);
					listBoxUpdater(listModules,modules);
					btnSubmit.setEnabled(false);
				}
			});
		}
		
		
		btnCancel.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				modules.removeModule(listModules.getSelectedIndex());
				listBoxUpdater(listModules,modules);
				btnCancel.setEnabled(false);
			}
		});
		
		
		
		//ListBox Handler
		listModules.addChangeHandler( new ChangeHandler() {
			public void onChange(ChangeEvent ev) {
				if(listModules.getSelectedIndex() > -1) {
					btnSubmit.setEnabled(true);
					btnCancel.setEnabled(true);
					populateTextBoxes();
				}
			}
		});
		
		//IDBox Handler
		tbID.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	MarsModule checkModule = new MarsModule();
		    	checkModule.setID(Integer.parseInt(tbID.getText()));
		    	tbType.setText(checkModule.getType());

		    }
		});
			
		
		
		mainPanel.add(coordTable);
		mainPanel.add(btnSubmit);
		mainPanel.add(btnCancel);
		mainPanel.add(editArea(modules, listModules));
	
		if(isNew) {
			listModules.setEnabled(false);
			btnSubmit.setEnabled(true);
			btnSubmit.setText("Add");
			btnCancel.setEnabled(false);
		}
		
		super.getPanel().add(mainPanel);
		
	}
	
	private FlowPanel editArea(ModuleSet modules, ListBox lb) {
		FlowPanel editPanel = new FlowPanel();
		editPanel.getElement().getStyle().setPaddingTop(20.0, Unit.PX);
		
	    
	    listBoxUpdater(lb, modules);
	    
	    lb.setWidth("100%");
	    
	    lb.setVisibleItemCount(8);
	    editPanel.add(lb);
	    
	    return editPanel;
	}
	
	/**
	 * Edits the labels as a group for easy changing.
	 */
	private void labelMaker(Label lb) {
//		lb.setSize("200px", "20px");
		lb.getElement().getStyle().setWidth(100, Unit.PCT);
	}
	
	/**
	 * Edits the text boxes as a group for easy changing.
	 */
	private void textBoxMaker(TextBox tb, int scale) {
		tb.setVisibleLength(scale * 8);
		tb.getElement().getStyle().setWidth(90, Unit.PCT);
	}

	
	private void listBoxUpdater(ListBox lb, ModuleSet modules) {
		lb.clear();
		
	    for(int i = 0; i < modules.getCount("all"); i++) {
	    	StringBuilder strbuild = new StringBuilder();
	    	strbuild.append("(");
	    	strbuild.append(modules.getModule(i).getX());
	    	strbuild.append(",");
	    	strbuild.append(modules.getModule(i).getY());
	    	strbuild.append(") - ");
	    	strbuild.append(modules.getModule(i).getType());
	    	
	    	lb.addItem(strbuild.toString());
	    	
	    }
	}
	
	
	@Override
	public void setupDisplay() {
		listBoxUpdater(listModules,modules);
		if(!isNew) {
			btnSubmit.setEnabled(false);
			btnCancel.setEnabled(false);
		}
	}
	
	private void populateTextBoxes() {
		MarsModule tempMod = modules.getModule(listModules.getSelectedIndex());
		tbX.setText(tempMod.getX().toString());
		tbY.setText(tempMod.getY().toString());
		tbID.setText(tempMod.getID().toString());
		tbCond.setText(tempMod.getCondition());
		tbOri.setText(tempMod.getOrientation().toString());
		tbType.setText(tempMod.getType());
		
		ltOri.setSelectedIndex(tempMod.getOrientation());
		
		if(tempMod.getCondition().equals("Good")) ltCond.setSelectedIndex(0);
		if(tempMod.getCondition().equals("Damaged")) ltCond.setSelectedIndex(1);
		if(tempMod.getCondition().equals("Broken")) ltCond.setSelectedIndex(2);
	}
}
