package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.thirdparty.javascript.jscomp.CssRenamingMap.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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
	
	/**
	 * Constructor for CtrlPanel.  Set some of the header and positioning here.
	 */
	public CtrlPanel() {
		VerticalPanel panel = new VerticalPanel();
	
		headerLabel = new Label("DEFAULT CONTROL");
		headerLabel.getElement().getStyle().setFontSize(1.8, Unit.EM);
		headerLabel.getElement().getStyle().setBackgroundColor("#555555");
		headerLabel.getElement().getStyle().setWidth(100, Unit.PCT);
		headerLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.add(headerLabel);

		this.initWidget(panel);
	}
	
	public Label getHeaderLabel() {
		return headerLabel;
	}

	
	
}
