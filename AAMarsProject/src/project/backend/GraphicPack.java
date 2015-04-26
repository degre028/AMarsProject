package project.backend;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This class initiates all graphics so that they load in the background.
 * @author Rob
 *
 */
public class GraphicPack {

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
	ImageElement blank;
	
	/**
	 * Constructor for the GraphicPack should initiate all graphics for 
	 */
	public GraphicPack() {
		
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
		final Image bla = new Image("resources/modpics/blank.jpg");
		
		mars = makeImage(mar);
		sanitation = makeImage(san);
		airlock = makeImage(air);
		terrain = makeImage(ter);
		canteen = makeImage(can);
		control = makeImage(con);
		dormitory = makeImage(dor);
		food = makeImage(foo);
		gymnasium = makeImage(gym);
		medical = makeImage(med);
		plain = makeImage(pla);
		power = makeImage(pow);
		blank = makeImage(bla);
		
	}
	
	/**
	 * This method creates an image and ensures its added to the root panel.
	 */
	private ImageElement makeImage(Image img) {
		final ImageElement image = ImageElement.as(img.getElement());
		
		
		img.addLoadHandler(new LoadHandler() {
			public void onLoad(LoadEvent event) { // fired by RootPanel.get().add
					//Window.alert("loading");
			}
		}); 

	    RootPanel.get().add(img);
		img.setVisible(false);
	    return image;
	}
	
	//Below are all the getters for the images.
	/**
	 * Getter for the images.
	 * @param image String representation for the image.
	 */
	public ImageElement getImageElement(String imgname) {
		
		if(imgname.equals("Plain")) { return plain; }
		else if (imgname.equals("Dormitory")) { return dormitory; }
		else if (imgname.equals("Sanitation")) { return sanitation; }
		else if (imgname.equals("Food & Water")) { return food; }
		else if (imgname.equals("Gym & Relaxation")) { return gymnasium; }
		else if (imgname.equals("Canteeen")) { return canteen; }
		else if (imgname.equals("Power")) { return power; }
		else if (imgname.equals("Control")) { return control; }
		else if (imgname.equals("Airlock")) { return airlock; }
		else if (imgname.equals("Medical")) { return medical; }
		else if (imgname.equals("mars")) { return mars;}
		else return blank;

	}
}
