package project.canvaspanel;

import java.awt.Graphics2D;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;








import com.google.web.bindery.autobean.vm.Configuration;

import project.backend.GraphicPack;
import project.backend.MarsConfiguration;
import project.backend.MarsModule;
import project.backend.ModuleSet;

/**
 * Represents the map of modules for the canvas panel.  Should
 * keep track of some view options and then display using
 * GWT draw methods a layout of the current module configuration.
 * @author Will
 *
 */
public class CnvsMap extends CnvsPanel{
	Canvas canvas = Canvas.createIfSupported();
	final double SPACER = 28.72; 
	ImageElement sanitation;
	ImageElement airlock;
	ImageElement terrain;
	ImageElement canteen;
	ImageElement dormitory;
	ImageElement food;
	ImageElement gymnasium;
	ImageElement medical;
	ImageElement plain;
	ImageElement power;
	ImageElement control;
	ImageElement mars;
	ImageElement rover;
	ImageElement blank;
	final Context2d context1 = canvas.getContext2d();
	final ModuleSet modset;
	boolean isSideBySide = false;
	
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
		
		GraphicPack graphics = modset.getGraphics();

		//canvas.setWidth("800px");
		this.modset = modset;
		canvas.setPixelSize(2872,1436);
		canvas.setCoordinateSpaceHeight(1436);
		canvas.setCoordinateSpaceWidth(2872);
		
//		canvas.setHeight("568px"); //800x568
		//Context2d context = canvas.getContext2d();
//		context.beginPath();
//		context.moveTo(10,10);
//		canvas.setHeight("100%");
//		canvas.getParent().getOffsetHeight();
		//canvas.getElement().getStyle().setBackgroundColor("#FF8106");
		
		

		
		
		final Image mar = new Image("resources/images/Mars Surface.jpg");
		final Image san = new Image("resources/modpics/Sanitation.jpg");
		final Image air = new Image("resources/modpics/Airlock.jpg");
		final Image ter = new Image("resources/modpics/Bad Terrain.jpg");
		final Image can = new Image("resources/modpics/Canteen.jpg");
		final Image con = new Image("resources/modpics/Control.jpg");
		final Image dor = new Image("resources/modpics/Dormitory.jpg");
		final Image foo = new Image("resources/modpics/Food.jpg");
		final Image gym = new Image("resources/modpics/Gym.jpg");
		final Image med = new Image("resources/modpics/Medical.jpg");
		final Image pla = new Image("resources/modpics/Plain.jpg");
		final Image pow = new Image("resources/modpics/Power.jpg");
		final Image rov = new Image("resources/modpics/Rover.jpg");
		
		mars = graphics.getImageElement("mars");

		sanitation = graphics.getImageElement("Sanitation");
		airlock =graphics.getImageElement("Airlock");
		terrain = graphics.getImageElement("Terrain");
		canteen = graphics.getImageElement("Canteen");
		control = graphics.getImageElement("Control");
		dormitory = graphics.getImageElement("Dormitory");
		food = graphics.getImageElement("Food & Water");
		gymnasium = graphics.getImageElement("Gym & Relaxation");
		medical = graphics.getImageElement("Medical");
		plain = graphics.getImageElement("Plain");
		power = graphics.getImageElement("Power");
		rover = graphics.getImageElement("Rover");
		blank = graphics.getImageElement("Blank");
		
		//context1.drawImage(mars, 1, 1, 2872, 1436);
		context1.clearRect(0, 0, 100 * SPACER, 50 * SPACER);
		
		
		super.getPanel().add(canvas);
		
	}
	
	/**
	 * Method called by the GUI to refresh the current display
	 * of the map.  (called after a module is added, etc.).
	 */
	@Override
	public void refreshDisplay() {
		isSideBySide = false;
		context1.drawImage(mars, 1, 1, 2872, 1436);


        CssColor damagedArea = CssColor.make("rgba(" + 0 + ", " + 0 + "," + 0 + ", " + 0.5 + ")");
        context1.setFillStyle(damagedArea);
		
		context1.fillRect(40*SPACER, 0*SPACER, 10*SPACER, 11*SPACER);
		


		context1.setStrokeStyle(CssColor.make(2,0,0));
		context1.setLineWidth(1.0);
		for(int i=0; i<100; i++) {
			Context2d context = canvas.getContext2d();
			context1.beginPath();
			context1.moveTo(i*SPACER,0);

			context1.lineTo(i*SPACER,1436);
			context1.stroke(); 
			context1.closePath();
		}
		for(int i=0; i<50; i++) {
			Context2d context = canvas.getContext2d();
			context1.beginPath();
			context1.moveTo(0,i*SPACER);

			context1.lineTo(2872,i*SPACER);
			context1.stroke(); 
			context1.closePath();

			}
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			MarsModule drawMod = modset.getModule(i);
			int xcoor= drawMod.getX()-1;
			int ycoor= drawMod.getY()-1;
			context1.drawImage(getImage(modset.getModule(i).getType()), xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
			
			if (drawMod.getCondition().equals("Damaged")) {
		        CssColor damagedMod = CssColor.make("rgba(" + 255 + ", " + 0 + "," + 0 + ", " + 0.4 + ")");
		        context1.setFillStyle(damagedMod);
				context1.fillRect(xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
			}
			
			if (drawMod.getCondition().equals("Uncertain")) {
		        CssColor damagedMod = CssColor.make("rgba(" + 255 + ", " + 255 + "," + 0 + ", " + 0.4 + ")");
		        context1.setFillStyle(damagedMod);
				context1.fillRect(xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
			}
			
			
		}
		
		//Display active config
		if(modset.getActiveConfig() > -1) {
			MarsConfiguration config = modset.getConfig(modset.getActiveConfig());
			for(int i = 0; i < modset.getCount("all"); i++) {
				if(config.getIsUsed(i)) {
					MarsModule drawMod = modset.getModule(i);
					int xcoor= config.getXCoord(i)-1;
					int ycoor= config.getYCoord(i)-1;
					context1.drawImage(getImage(modset.getModule(i).getType()), xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
					
			        CssColor damagedMod = CssColor.make("rgba(" + 0 + ", " + 0 + "," + 255 + ", " + 0.4 + ")");
			        context1.setFillStyle(damagedMod);
					context1.fillRect(xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
				}
				else {
					MarsModule drawMod = modset.getModule(i);
					int xcoor= config.getXCoord(i)-1;
					int ycoor= config.getYCoord(i)-1;
					context1.drawImage(getImage(modset.getModule(i).getType()), xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
					
			        CssColor damagedMod = CssColor.make("rgba(" + 0 + ", " + 128 + "," + 0 + ", " + 0.5 + ")");
			        context1.setFillStyle(damagedMod);
					context1.fillRect(xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
				}
					
		}
			
			
			
			
		}
	}
	

	/**
	 * This method draws the preview config "on top of" the live config.
	 */
	public void drawPreviewConfig(MarsConfiguration configu) {

		try { 
		refreshDisplay();
		if(!isSideBySide) {
	        CssColor backdrop = CssColor.make("rgba(" + 0 + ", " + 0 + "," + 0 + ", " + 0.5 + ")");
	        context1.setFillStyle(backdrop);
			isSideBySide = true;
		}
        context1.fillRect(0, 0, 2872, 1436);
        
		for(int i = 0; i < modset.getCount("all"); i++) {
			if(configu.getIsUsed(i)) {
				MarsModule drawMod = modset.getModule(i);
				int xcoor= configu.getXCoord(i)-1;
				int ycoor= configu.getYCoord(i)-1;
				context1.drawImage(getImage(modset.getModule(i).getType()), xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
			}
			
//			if (drawMod.getCondition().equals("Damaged")) {
//		        CssColor damagedMod = CssColor.make("rgba(" + 255 + ", " + 0 + "," + 0 + ", " + 0.4 + ")");
//		        context1.setFillStyle(damagedMod);
//				context1.fillRect(xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
//			}
//			
//			if (drawMod.getCondition().equals("Uncertain")) {
//		        CssColor damagedMod = CssColor.make("rgba(" + 255 + ", " + 255 + "," + 0 + ", " + 0.4 + ")");
//		        context1.setFillStyle(damagedMod);
//				context1.fillRect(xcoor*SPACER, (49 - ycoor)*SPACER, SPACER, SPACER);
//			}
			
			
		}
		} catch (Exception e) {
			//Window.alert(e.getMessage());
		}
	}
	
	
	private ImageElement getImage(String type) {
		if(type.equals("Plain")) { return plain; }
		else if (type.equals("Dormitory")) { return dormitory; }
		else if (type.equals("Sanitation")) { return sanitation; }
		else if (type.equals("Food & Water")) { return food; }
		else if (type.equals("Gym & Relaxation")) { return gymnasium; }
		else if (type.equals("Canteen")) { return canteen; }
		else if (type.equals("Power")) { return power; }
		else if (type.equals("Control")) { return control; }
		else if (type.equals("Airlock")) { return airlock; }
		else if (type.equals("Medical")) { return medical; }
		else return blank;
		
	}
	
	/**
	 * This method draws a line connecting a module in the modset to a
	 * its representation in the config.
	 * @param index The module index in the modset.
	 */
	public void drawArrow(int index) {
		refreshDisplay();
		MarsConfiguration config = modset.getConfig(modset.getActiveConfig());
		
		int xStart = modset.getModule(index).getX();
		int yStart = modset.getModule(index).getY();
		int xEnd = config.getXCoord(index);
		int yEnd = config.getYCoord(index);
		
//		Window.alert("" + xStart + "\n" + xEnd + "\n" +yStart +"\n" + yEnd);
		
		Context2d context = canvas.getContext2d();
		context.setStrokeStyle(CssColor.make(255,0,0));

		//Eucledian Geometry
//		context.beginPath();
//		context.setStrokeStyle(CssColor.make(255,0,0));
//		context.setLineWidth(5.0);
//		context.moveTo((xStart-0.5) * SPACER, (50-yStart+0.5) * SPACER);
//	
//		context.lineTo((xEnd-0.5) * SPACER, (50-yEnd+0.5)*SPACER);
//		context.stroke(); 
//		context.closePath();
		
		//Taxi Cab Gemoetry
		context.beginPath();
		context.setStrokeStyle(CssColor.make(0,255,255));
		context.setLineWidth(5.0);
		context.moveTo((xStart-0.5) * SPACER, (50-yStart+0.5) * SPACER);
		context.lineTo((xEnd-0.5) * SPACER, (50-yStart+0.5) * SPACER);
		context.lineTo((xEnd-0.5) * SPACER, (50-yEnd+0.5)*SPACER);
		context.stroke(); 
		context.closePath();
	}
	
	/**
	 * Method draws the rover and an arrow from its current position to the
	 * next module to be moved.
	 * @param xRover x coordinate position of the rover.
	 * @param yRover y coordinate position of the rover.
	 * @param index index of the module to navigate to.
	 */
	public void drawRoverToModule(int xRover, int yRover, int index) {
		refreshDisplay();
		
		//Adjust for display
		xRover -= 1;
		yRover -= 1;
		
		//Draw Rover
		context1.drawImage(rover, xRover*SPACER, (49 - yRover)*SPACER, SPACER, SPACER);
		
		//Draw Line
		MarsConfiguration config = modset.getConfig(modset.getActiveConfig());
		//int xEnd = config.getXCoord(index);
		//int yEnd = config.getYCoord(index);
		
		int xEnd = modset.getModule(index).getX();
		int yEnd = modset.getModule(index).getY();
		
		Context2d context = canvas.getContext2d();
		context.beginPath();
		context.setStrokeStyle(CssColor.make(255,0,255));
		context.setLineWidth(5.0);
		context.moveTo((xRover+0.5)*SPACER, (49 - yRover + 0.5)*SPACER);
		context.lineTo((xEnd-0.5) * SPACER, (49 - yRover + 0.5)*SPACER);
		context.lineTo((xEnd-0.5) * SPACER, (50-yEnd+0.5)*SPACER);
		context.stroke(); 
		context.closePath();
		
	}
	
}
