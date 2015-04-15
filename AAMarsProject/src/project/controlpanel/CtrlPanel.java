package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.thirdparty.javascript.jscomp.CssRenamingMap.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Parent class for the control panel layouts defined in the project.controlpanel package.
 * Class should not be instantiated.
 * @author Rob
 *
 */
public class CtrlPanel extends Composite{
	private Label headerLabel;
	private FlowPanel mainPanel = new FlowPanel();
	
	/**
	 * Constructor for CtrlPanel.  Set some of the header and positioning here.
	 */
	public CtrlPanel() {
	
		headerLabel = new Label("DEFAULT CONTROL");
		headerLabel.getElement().getStyle().setFontSize(1.8, Unit.EM);
		headerLabel.getElement().getStyle().setWidth(100, Unit.PCT);
		headerLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		mainPanel.add(headerLabel);
//		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

		this.initWidget(mainPanel);
	}
	
	/**
	 * This method returns a label for easy editing of the title.
	 * @return headerLabel For changing the header among the subclasses.
	 */
	public Label getHeaderLabel() {
		return headerLabel;
	}

	/**
	 * This method returns the main panel for adding of widgets. 
	 * @return mainPanel
	 */
	public FlowPanel getPanel() {
		return mainPanel;
	}
	
}
