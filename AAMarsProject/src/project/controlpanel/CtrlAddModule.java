package project.controlpanel;

/**
 * Control panel subclass for the Add Module control panel.
 * @author Rob
 *
 */
public class CtrlAddModule extends CtrlPanel{
	
	public CtrlAddModule(boolean isNew) {
		if(isNew) super.getHeaderLabel().setText("Add Module");
		else super.getHeaderLabel().setText("Edit Module");
	}

}
