package project.gui;

import java.util.LinkedList;

import project.backend.ModuleSet;
import project.controlpanel.*;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * The button area class is a GUI type class that generates the bottom area of the main
 * screen.
 * @author Milan
 *
 */
public class ButtonArea extends Composite{

	/**
	 * The constructor simply generates the GUI by adding buttons.
	 */
	public ButtonArea(ModuleSet modset) {
		FlowPanel panel = new FlowPanel();

		panel.add(makeButtons(modset));
		
        // All composites must call initWidget() in their constructors.
        initWidget(panel);
	}
	
	/**
	 * This field makes the buttons and returns them in a table to be added to the
	 * button area.
	 */
	private FlexTable makeButtons(ModuleSet modset) {
		FlexTable fTable = new FlexTable();
		
		//Generate Buttons
		LinkedList<MarsButton> buttonList = new LinkedList<MarsButton>();
		buttonList.add(new MarsButton(true, "Add Module", new CtrlAddModule(true,modset),modset));
		buttonList.add(new MarsButton(true, "Edit Module", new CtrlAddModule(false,modset),modset));
		//buttonList.add(new MarsButton(true, "View Histogram", new CtrlPanel(),gui));
		
		//Add buttons to table.
		for (int i = 0; i < buttonList.size(); i++) {
			fTable.setWidget(i, 0, buttonList.get(i));
		}
		
		return fTable;
	}
	
}
