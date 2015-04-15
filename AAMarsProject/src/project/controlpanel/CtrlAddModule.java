package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Control panel subclass for the Add Module control panel.
 * @author Rob
 *
 */
public class CtrlAddModule extends CtrlPanel{
	
	/**
	 * Constructor builds an Add or Edit module panel.
	 * @param isNew if true, the "Add Module" button was clicked.  If false, the "Edit Module"
	 * button was clicked.
	 */
	public CtrlAddModule(boolean isNew) {
		
		
		
		if(isNew) super.getHeaderLabel().setText("Add Module");
		else super.getHeaderLabel().setText("Edit Module");
		
		
		FlowPanel mainPanel = new FlowPanel();
		FlexTable coordTable = new FlexTable();
		
		//Buttons
		Button btnSubmit = new Button("Submit");
		Button btnCancel = new Button("Cancel");
		
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
		
		TextBox tbX = new TextBox();
		textBoxMaker(tbX,1);
		TextBox tbY = new TextBox();
		textBoxMaker(tbY,1);
		TextBox tbID = new TextBox();
		textBoxMaker(tbID,1);
		TextBox tbOri = new TextBox();
		textBoxMaker(tbOri,1);
		TextBox tbCond = new TextBox();
		textBoxMaker(tbCond,1);
		
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
		coordTable.setWidget(3, 1, tbOri);
		coordTable.setWidget(4, 0, lbCond);
		coordTable.setWidget(4, 1, tbCond);
//		coordTable.setWidget(5, 0, btnSubmit);
//		coordTable.setWidget(5, 1, btnCancel);
		
		
		mainPanel.add(coordTable);
		mainPanel.add(btnSubmit);
		mainPanel.add(btnCancel);
	

			
		super.getPanel().add(mainPanel);
		
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

}
