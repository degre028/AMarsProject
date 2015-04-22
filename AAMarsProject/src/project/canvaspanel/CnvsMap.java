package project.canvaspanel;

import java.awt.Graphics2D;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;




import project.backend.ModuleSet;

/**
 * Represents the map of modules for the canvas panel.  Should
 * keep track of some view options and then display using
 * GWT draw methods a layout of the current module configuration.
 * @author Will
 *
 */
public class CnvsMap extends CnvsPanel{
	
	/**
	 * Constructor for the canvas map panel area.
	 * @param modset
	 */
	public CnvsMap(final ModuleSet modset) { //Request width and height
//		VerticalPanel pane = new VerticalPanel();
//		Label lab = new Label("demo");
//		lab.getElement().getStyle().setWidth(100.0, Unit.PCT);
//		lab.getElement().getStyle().setColor("#FFFFFF");
//		
//		pane.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		pane.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
//		pane.getElement().getStyle().setBackgroundColor("#000000");
//		pane.getElement().getStyle().setWidth(100.0,Unit.PCT);
//		pane.getElement().getStyle().setHeight(100.0,Unit.PCT);
//		pane.add(lab);
		
		Canvas canvas = Canvas.createIfSupported();
		//canvas.setWidth("800px");
		canvas.setPixelSize(800,568);
		canvas.setCoordinateSpaceHeight(568);
		canvas.setCoordinateSpaceWidth(800);
		
//		canvas.setHeight("568px"); //800x568
		//Context2d context = canvas.getContext2d();
//		context.beginPath();
//		context.moveTo(10,10);
//		canvas.setHeight("100%");
//		canvas.getParent().getOffsetHeight();
		canvas.getElement().getStyle().setBackgroundColor("#FF8106");
		
		
		final Context2d context1 = canvas.getContext2d();
		
		final Image img = new Image("resources/images/wunder.png");
		final ImageElement nasa = ImageElement.as(img.getElement());
		
		
		img.addLoadHandler(new LoadHandler() {
			public void onLoad(LoadEvent event) { // fired by RootPanel.get().add

			}
		}); 
		
		context1.drawImage(nasa, 10, 10, 50, 50);
		//context1.drawImage(nasa, 100, 10, 50, 50);


		
		for(int i=0; i<100; i++) {
			Context2d context = canvas.getContext2d();
			context.beginPath();
			context.moveTo(i*8,0);
//			if(i%2==0){
//				context.setLineWidth(2);}
			//else{ context.setLineWidth(1);}
			//context.setFillStyle("000000");
			context.lineTo(i*8,500);
			context.stroke(); 
			context.closePath();
		}
		for(int i=0; i<72; i++) {
			Context2d context = canvas.getContext2d();
			context.beginPath();
			context.moveTo(0,i*8);
//			if(i%2==0){
//				context.setLineWidth(2);}
			//else{ context.setLineWidth(1);}
			//context.setLineWidth(.5);
			//context.setFillStyle("000000");
			context.lineTo(500,i*8);
			context.stroke(); 
			context.closePath();

			}		
		
		super.getPanel().add(canvas);
		img.setVisible(false);
	    super.getPanel().add(img);
	}
	
	/**
	 * Method called by the GUI to refresh the current display
	 * of the map.  (called after a module is added, etc.).
	 */
	@Override
	public void refreshDisplay() {
		
	}

}
